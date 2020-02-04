package com.ios.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appium.base.page.BasePage;
import com.appium.utils.WaitUtil;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class LoginPage extends BasePage {
	
	private final Logger LOG = LoggerFactory.getLogger(LoginPage.class);

	@FindBy (id = "email")
	private WebElement registeredEmailIdInputbox;
	
	@FindBy (id = "passwd")
	private WebElement passwordInputbox;
	
	@FindBy (id = "SubmitLogin")
	private WebElement loginButton;
	
	@FindBy (xpath = "//div[@class='alert alert-danger']//li")
	private WebElement errorMessageText;
	
	public LoginPage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

	public void enterRegisteredEmailId(String email) {
		registeredEmailIdInputbox.sendKeys(email);
	}
	
	public void enterPassword(String password) {
		passwordInputbox.sendKeys(password);
	}
	
	public void clickLoginButton() {
		loginButton.click();
		WaitUtil.pause(3);
	}
	
	public String getErrorMessage() {
		String errorMessage = errorMessageText.getText();	  
		LOG.info("Error Message: "+errorMessage);
		return errorMessage;
	}
}
