package com.ios.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.appium.base.page.BasePage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class HomePage extends BasePage {

	@FindBy (xpath = "//a[@class='login']")
	private WebElement loginLink;
	
	
	public HomePage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

	public void clickLoginLink() {
		loginLink.click();
	}
}
