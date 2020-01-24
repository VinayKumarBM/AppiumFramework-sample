package com.ios.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.appium.base.page.WebBasePage;

public class HomePage extends WebBasePage {

	@FindBy (xpath = "//a[@class='login']")
	private WebElement loginLink;
	
	
	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void clickLoginLink() {
		loginLink.click();
	}
}
