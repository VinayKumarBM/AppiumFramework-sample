package com.ios.web.modules;

import org.openqa.selenium.WebDriver;

import com.ios.web.pages.HomePage;

public class HomeModule {
	private WebDriver driver;
	private HomePage homePage;
	
	public HomeModule(WebDriver driver) {
		this.driver = driver;
		homePage = new HomePage(driver);
	}

	public void navigateToLoginPage() {
		homePage.clickLoginLink();
	}
}
