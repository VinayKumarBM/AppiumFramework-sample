package com.appium.base.test;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class AndroidInstalledAppTestBase extends TestBase{
	protected AndroidDriver<AndroidElement> driver;
	
	@BeforeMethod
	public void launchAndroidInstalledApplication() {
		driver = launchAndroidApplication("APP");
	}
	
	@AfterMethod
	public void quitAndroidInstalledApplication(final ITestResult result) throws IOException {
		quit(driver, result);
	}
	
}
