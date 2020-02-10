package com.appium.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.appium.config.AppType;
import com.appium.config.Config;
import com.appium.config.OS;
import com.appium.utils.AppOperations;
import com.appium.utils.ScreenshotUtil;
import com.appium.utils.WaitUtil;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public abstract class TestBase {
	private final Logger LOG = LoggerFactory.getLogger(TestBase.class);
	private final File APP_DIR = new File(Config.getProperties("app.location"));
	private final File DRIVER_DIR = new File(Config.getProperties("chrome.driver.location"));
	private final String API_DEMOS_APP = "ApiDemosTest";
	private AppiumDriverLocalService service;
	protected AppiumDriver<MobileElement> driver;

	private AppiumDriver<MobileElement> launchIOSApplication(String appType) {
		String device = System.getProperty("deviceName");
		if(device==null) {
			device = Config.getProperties("iOS.device");
		}
		LOG.info(device);
		AppiumDriver<MobileElement> driver = null;
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Config.getProperties("iOS.version"));		
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);		

		if(appType.contains(AppType.IOS_APP.getValue())) {
			File app = new File(APP_DIR, Config.getProperties("iOS.app"));
			capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());	
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Config.getProperties("iOS.platform"));
		} else if(appType.contains(AppType.IOS_WEB.getValue())){
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, Config.getProperties("iOS.browser"));
		}
		try {
			driver = new IOSDriver<MobileElement>(new URL(Config.getProperties("appium.server.url")),capabilities);
			driver.manage().timeouts().implicitlyWait(Config.getIntegerProperty("implicitWait"), TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			LOG.info("IOS App launch failed: "+e.getMessage());
		}		
		LOG.info("IOS App launched sucessfully!!");
		return driver;
	}

	private AppiumDriver<MobileElement> launchAndroidApplication(String appType) {		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Config.getProperties("android.device"));
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
		if(appType.contains(AppType.ANDROID_APP.getValue())) {
			String andoroidApp = Config.getProperties("android.app");
			if (appType.contains(API_DEMOS_APP.toLowerCase())) {
				andoroidApp = Config.getProperties("android.app.apidemos");
			}
			File app = new File(APP_DIR, andoroidApp);
			capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Config.getProperties("android.platform"));
		} else if(appType.contains(AppType.ANDROID_WEB.getValue())){
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, Config.getProperties("android.browser"));
			capabilities.setCapability(MobileCapabilityType.BROWSER_VERSION,  Config.getProperties("chrome.browser.version"));
		} else if(appType.contains(AppType.ANDROID_INSTALLED_APP.getValue())){
			capabilities.setCapability("appPackage", Config.getProperties("android.app.packageName"));
			capabilities.setCapability("appActivity",  Config.getProperties("android.app.activityName"));
		}
		//capabilities.setCapability("chromedriverExecutable", driverDir.getAbsolutePath());

		AppiumDriver<MobileElement> driver = null;
		try {
			driver = new AndroidDriver<MobileElement>(new URL(Config.getProperties("appium.server.url")), capabilities);
			driver.manage().timeouts().implicitlyWait(Config.getIntegerProperty("implicitWait"), TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			LOG.info("IOS App launch failed: "+e.getMessage());
		}		
		LOG.info("Android App launched successfully!!");
		return driver;
	}

	@BeforeSuite
	public void startAppiumServer() throws IOException {
		if(!isAppiumServerRunning(4723)) {
			//service = new AppiumServiceBuilder().usingPort(4723).build();
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
			LOG.info("Appium Server started.");
		}
		startEmulator();
	}

	@AfterSuite (alwaysRun = true)
	public void stopAppiumServer() throws IOException {		
		stopEmulator();
		service.stop();
		LOG.info("Appium server stopped.");
	}

	private void stopEmulator() throws IOException {
		String emulatorPath = String.format(Config.getProperties("emulator.stop.script"),Config.getProperties("appium.run.os").toLowerCase());
		LOG.info("Stopping Emulator...");
		executeScript(emulatorPath);
		LOG.info("Emulator stopped.");
	}

	private void executeScript(String emulatorPath) throws IOException {
		Runtime.getRuntime().exec(new File(Config.getProperties(emulatorPath)).getAbsolutePath());
		WaitUtil.pause(30);
	}
	
	private void startEmulator() throws IOException {
		String emulatorPath = String.format(Config.getProperties("emulator.start.script"),Config.getProperties("appium.run.os").toLowerCase());
		LOG.info("Starting Emulator...");
		executeScript(emulatorPath);
		LOG.info("Emulator started.");
	}
	
	private boolean isAppiumServerRunning(int port) {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			// If control comes here then that means server is running
			return true;
		}
		return false;
	}

	@BeforeMethod
	public void launchMobileApplication(Method method) {
		String className = method.getDeclaringClass().getName().toLowerCase();
		if(className.contains(OS.ANDROID.toString().toLowerCase())) {
			driver = launchAndroidApplication(className);
			if(className.contains(AppType.ANDROID_WEB.getValue())){
				driver.get(Config.getProperties("android.web.url"));
			}
		} else if (className.contains(OS.IOS.toString().toLowerCase())) {
			driver = launchIOSApplication(className);
			if(className.contains(AppType.IOS_WEB.getValue())){
				driver.get(Config.getProperties("iOS.web.url"));
			}
		}
	}

	@AfterMethod (alwaysRun = true)
	public void quit(final ITestResult result) throws IOException {
		try {
			if(result.getStatus()==ITestResult.FAILURE) {
				AppOperations.switchToNativeApp(driver);
				ScreenshotUtil.takeScreenshot(driver, result.getName());
			}
		}catch (Exception e) {
			LOG.info("Exception occured while taking screenshot: "+e.getMessage());
		} finally {
			driver.quit();
		}		
		LOG.info("App is Closed");
	}
}