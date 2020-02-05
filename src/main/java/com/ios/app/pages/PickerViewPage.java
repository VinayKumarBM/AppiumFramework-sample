package com.ios.app.pages;

import org.openqa.selenium.WebElement;

import com.appium.base.BasePage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class PickerViewPage extends BasePage{

	public PickerViewPage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

	@iOSXCUITFindBy(accessibility = "Red color component value")
	private WebElement redComponentPicker;
	
	@iOSXCUITFindBy(accessibility = "Green color component value")
	private WebElement greenComponentPicker;
	
	@iOSXCUITFindBy(xpath = "//*[@label='Blue color component value']")
	private WebElement blueComponentPicker;
	
	public void pickRedColor(String redComponent) {
		redComponentPicker.sendKeys(redComponent);
	}
	
	public void pickGreenColor(String greenComponent) {
		greenComponentPicker.sendKeys(greenComponent);
	}
	
	public void pickBlueColor(String blueComponent) {
		blueComponentPicker.sendKeys(blueComponent);
	}
}
