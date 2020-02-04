package com.appium.base.test;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AndroidAppTestBase extends TestBase{
	protected AppiumDriver<MobileElement> driver;
	
	@BeforeMethod
	public void launchAndroidNativeApplication() {
		driver = launchAndroidApplication("NATIVE");
	}
	
	@AfterMethod
	public void quitAndroidNativeApplication(final ITestResult result) throws IOException {
		quit(driver, result);
	}

}
