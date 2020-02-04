package com.appium.base.test;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.appium.utils.AppOperations;
import com.appium.utils.Config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AndroidWebTestBase extends TestBase{
	protected AppiumDriver<MobileElement> driver;
	
	@BeforeMethod
	public void launchAndroidWebBrowser() {		
		driver = launchAndroidApplication("WEB");		
		driver.get(Config.getProperties("android.web.url"));
	}
	
	@AfterMethod
	public void quitAndroidWebBrowser(final ITestResult result) throws IOException {
		AppOperations.switchToNativeApp(driver);
		quit(driver, result);
	}

}
