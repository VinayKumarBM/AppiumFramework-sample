package com.appium.base.test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.appium.utils.Config;
import com.appium.utils.ScreenshotUtil;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public abstract class TestBase {
	private final Logger LOG = LoggerFactory.getLogger(TestBase.class);
	private final File appDir = new File(Config.getProperties("app.location"));
	private final File driverDir = new File(Config.getProperties("chrome.driver.location"));
	private AppiumDriverLocalService service;

	protected IOSDriver<IOSElement> launchIOSApplication(String appType) {
		String device = System.getProperty("deviceName");
		if(device==null) {
			device = Config.getProperties("iOS.device");
		}
		LOG.info(device);
		IOSDriver<IOSElement> driver = null;
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Config.getProperties("iOS.version"));		
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);		

		if(appType.equalsIgnoreCase("NATIVE")) {
			File app = new File(appDir, Config.getProperties("iOS.app"));
			capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());	
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Config.getProperties("iOS.platform"));
		} else if(appType.equalsIgnoreCase("WEB")){
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, Config.getProperties("iOS.browser"));
		}
		try {
			driver = new IOSDriver<IOSElement>(new URL(Config.getProperties("appium.server.url")),capabilities);
			driver.manage().timeouts().implicitlyWait(Config.getIntegerProperty("implicitWait"), TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			LOG.info("IOS App launch failed: "+e.getMessage());
		}		
		LOG.info("IOS App launched sucessfully!!");
		return driver;
	}

	protected AndroidDriver<AndroidElement> launchAndroidApplication(String appType) {		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Config.getProperties("android.device"));
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
		capabilities.setCapability(MobileCapabilityType.TAKES_SCREENSHOT,true);
		if(appType.equalsIgnoreCase("NATIVE")) {
			File app = new File(appDir, Config.getProperties("android.app"));
			capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Config.getProperties("android.platform"));
		} else if(appType.equalsIgnoreCase("WEB")){
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, Config.getProperties("android.browser"));
			capabilities.setCapability(MobileCapabilityType.BROWSER_VERSION,  Config.getProperties("chrome.browser.version"));
		} else if(appType.equalsIgnoreCase("APP")){
			capabilities.setCapability("appPackage", Config.getProperties("android.app.packageName"));
			capabilities.setCapability("appActivity",  Config.getProperties("android.app.activityName"));
		}
		//capabilities.setCapability("chromedriverExecutable", driverDir.getAbsolutePath());

		AndroidDriver<AndroidElement> driver = null;
		try {
			driver = new AndroidDriver<AndroidElement>(new URL(Config.getProperties("appium.server.url")), capabilities);
			driver.manage().timeouts().implicitlyWait(Config.getIntegerProperty("implicitWait"), TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			LOG.info("IOS App launch failed: "+e.getMessage());
		}		
		LOG.info("Android App launched successfully!!");
		return driver;
	}

	//@BeforeSuite
	public void startAppiumServer() {
		if(!isAppiumServerRunning(4723)) {
			//service = new AppiumServiceBuilder().usingPort(4723).build();
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
	}

	//@AfterSuite
	public void stopAppiumServer() {
		service.stop();
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

	protected void quit(WebDriver driver, final ITestResult result) throws IOException {
		try {
			if(result.getStatus()==ITestResult.FAILURE) {
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