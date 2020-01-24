package com.appium.base.test;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.appium.utils.Config;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class IOSWebTestBase extends TestBase{
	protected IOSDriver<IOSElement> driver;
	
	@BeforeMethod
	public void launchIOSWebBrowser() {		
		driver = launchIOSApplication("WEB");		
		driver.get(Config.getProperties("iOS.web.url"));
	}
	
	@AfterMethod
	public void quitIOSWebBrowser(final ITestResult result) throws IOException {
		quit(driver, result);
	}

}
