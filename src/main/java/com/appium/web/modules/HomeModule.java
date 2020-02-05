package com.appium.web.modules;

import com.appium.web.pages.HomePage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class HomeModule {
	private AppiumDriver<MobileElement> driver;
	private HomePage homePage;
	
	public HomeModule(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		homePage = new HomePage(driver);
	}

	public void navigateToLoginPage() {
		homePage.clickLoginLink();
	}
}
