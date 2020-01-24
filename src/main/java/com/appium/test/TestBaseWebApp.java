package com.appium.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.appium.utils.Config;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestBaseWebApp {
	protected AndroidDriver<AndroidElement>  driver;
	
	@BeforeMethod
	public void launchNativeApplication() throws MalformedURLException
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Config.getProperties("device"));
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,  Config.getProperties("browser"));
		capabilities.setCapability(MobileCapabilityType.BROWSER_VERSION,  "69.0.3497");
		capabilities.setCapability("chromedriverExecutable", System.getProperty("user.dir")+"/driver/chromedriver.exe");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");		
		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);			
		driver.get( Config.getProperties("url"));
		System.out.println("Successfully launched the application on the browser!!");
	}
	
	@AfterMethod
	public void quitNativeApplication() {
		driver.quit();
		System.out.println("Successfully quit the browser!!");
	}
}
