package com.appium.base.test;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class IOSAppTestBase extends TestBase{
	protected IOSDriver<IOSElement> driver;
	
	@BeforeMethod
	public void launchIOSNativeApplication() {
		driver = launchIOSApplication("NATIVE");
	}
	
	@AfterMethod
	public void quitIOSNativeApplication(final ITestResult result) throws IOException {
		quit(driver, result);
	}

}
