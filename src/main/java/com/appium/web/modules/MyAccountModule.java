package com.appium.web.modules;

import static org.testng.Assert.assertEquals;

import com.appium.web.pages.MyAccountPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MyAccountModule {
	private AppiumDriver<MobileElement> driver;
	private MyAccountPage myAccountPage;
	
	public MyAccountModule(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		myAccountPage = new MyAccountPage(driver);
	}

	public void scrolToFooter() {
		myAccountPage.scrolToFooter();
	}
	
	public void verifyPageHeading(String currentPage) {
		assertEquals(myAccountPage.getCurrentPageHeading(), currentPage, "Login failed!!");
	}
}
