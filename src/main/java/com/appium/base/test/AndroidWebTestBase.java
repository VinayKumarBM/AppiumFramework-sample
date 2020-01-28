package com.appium.base.test;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.appium.utils.Config;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class AndroidWebTestBase extends TestBase{
	protected AndroidDriver<AndroidElement> driver;
	
	@BeforeMethod
	public void launchAndroidWebBrowser() {		
		driver = launchAndroidApplication("WEB");		
		driver.get(Config.getProperties("android.web.url"));
	}
	
	@AfterMethod
	public void quitAndroidWebBrowser(final ITestResult result) throws IOException {
		driver.context("NATIVE_APP");
		quit(driver, result);
	}

}
