package com.appium.web.modules;

import static org.testng.Assert.assertEquals;

import com.appium.web.pages.LoginPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class LoginModule {
	private AppiumDriver<MobileElement> driver;
	private LoginPage loginPage;
	
	public LoginModule(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		loginPage = new LoginPage(driver);
	}

	public void loginToAccount(String email, String password) {
		loginPage.enterRegisteredEmailId(email);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
	}
	
	public void verifyErrorMessage(String errorMessage) {
		assertEquals(loginPage.getErrorMessage(), errorMessage, "Error message was not displayed correctly");
	}
}
