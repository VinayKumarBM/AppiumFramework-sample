package com.ios.app.pages;

import org.openqa.selenium.WebElement;

import com.appium.base.page.IOSBasePage;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AlertViewPage extends IOSBasePage{

	public AlertViewPage(IOSDriver<IOSElement> driver) {
		super(driver);
	}

	@iOSXCUITFindBy(accessibility = "Text Entry")
	private WebElement textEnterCatalog;
	
	@iOSXCUITFindBy(xpath = "//*[@type='XCUIElementTypeTextField']")
	private WebElement shortTextFiled;
	
	@iOSXCUITFindBy(accessibility = "OK")
	private WebElement okButton;
	
	public void clickTextEnter() {
		textEnterCatalog.click();
	}
	
	public void enterShortText(String textToEnter) {
		shortTextFiled.sendKeys(textToEnter);
	}
	
	public void clickOk() {
		okButton.click();
	}
}
