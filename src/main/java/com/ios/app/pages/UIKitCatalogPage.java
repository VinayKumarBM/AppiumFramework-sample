package com.ios.app.pages;

import org.openqa.selenium.WebElement;

import com.appium.base.page.IOSBasePage;
import com.appium.utils.IOSAppOperations;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class UIKitCatalogPage extends IOSBasePage{

	public UIKitCatalogPage(IOSDriver<IOSElement> driver) {
		super(driver);
	}

	@iOSXCUITFindBy(accessibility = "Alert Views")
	private WebElement alertViewsCatalog;
	
	@iOSXCUITFindBy(accessibility = "Picker View")
	private WebElement pickerViewCatalog;
	
	@iOSXCUITFindBy(accessibility = "Segmented Controls")
	private WebElement segmentedControlsCatalog;
	
	@iOSXCUITFindBy(accessibility = "Image View")
	private WebElement imageViewCatalog;
	
	@iOSXCUITFindBy(accessibility = "Sliders")
	private WebElement slidersCatlog;
	
	@iOSXCUITFindBy (accessibility = "Web View")
	private WebElement webViewCatalog;
	
	@iOSXCUITFindBy (accessibility = "Steppers")
	private WebElement steppersCatalog;
	
	public void clickImageView() {
		imageViewCatalog.click();
	}
	
	public void clickStepper() {
		steppersCatalog.click();
	}
	
	public void clickAlertView() {
		alertViewsCatalog.click();
	}
	
	public void clickPickerView() {
		pickerViewCatalog.click();
	}
	
	public void clickSegmentedControls() {
		segmentedControlsCatalog.click();
	}

	public void clickSliders() {
		slidersCatlog.click();
	}

	public void clickWebView() {
		IOSAppOperations.scrollAndClick(driver, webViewCatalog, "down");
	}
}
