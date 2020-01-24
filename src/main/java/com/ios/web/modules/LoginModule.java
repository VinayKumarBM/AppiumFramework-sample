package com.ios.web.modules;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;

import com.ios.web.pages.LoginPage;

public class LoginModule {
	private WebDriver driver;
	private LoginPage loginPage;
	
	public LoginModule(WebDriver driver) {
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
