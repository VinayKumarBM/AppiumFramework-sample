package com.appium.test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.appium.utils.Config;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestBaseNativeApp {
	protected AndroidDriver<MobileElement>  driver;
	private static File appDir = new File("src/test/resources/App");
	
	@BeforeMethod
	public void launchNativeApplication() throws MalformedURLException
	{
		File app = new File(appDir, Config.getProperties("android.app"));
		System.out.println("App Path: "+app.getAbsolutePath());
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Config.getProperties("android.device"));
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		capabilities.setCapability("chromedriverExecutable", System.getProperty("user.dir")+"/driver/chromedriver.exe");
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Successfully launched the application in the AVD!!");
	}
	
	@AfterMethod
	public void quitNativeApplication() {
		driver.quit();
		System.out.println("Successfully quit the application in the AVD!!");
	}
	
}
