package com.ios.app.pages;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appium.base.BasePage;
import com.appium.utils.AppOperations;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class SegmentedControlsPage extends BasePage {

	private final Logger LOG = LoggerFactory.getLogger(SegmentedControlsPage.class);
	
	@iOSXCUITFindBy(xpath = "//*[@label='Check']")
	private WebElement defaultCheckButton;
	
	@iOSXCUITFindBy(accessibility = "Burst")
	private WebElement customBurstButton;
	
	@iOSXCUITFindBy(xpath = "(//*[@label='Check'])[2]")
	private WebElement tintedCheckButton;
	
	
	public SegmentedControlsPage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
	
	public boolean isDefaultCheckEnabeled() {
		return defaultCheckButton.isEnabled();
	}

	public void longPressBurstButton() {
		AppOperations.longPressElement(driver, customBurstButton);
	}
	
	public void tapTintedCheck() {
		AppOperations.tapOnElement(driver, tintedCheckButton);
	}
}
