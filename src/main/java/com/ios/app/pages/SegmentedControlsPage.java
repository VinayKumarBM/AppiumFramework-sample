package com.ios.app.pages;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appium.base.page.IOSBasePage;
import com.appium.utils.IOSAppOperations;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class SegmentedControlsPage extends IOSBasePage {

	private final Logger LOG = LoggerFactory.getLogger(SegmentedControlsPage.class);
	
	@iOSXCUITFindBy(xpath = "//*[@label='Check']")
	private WebElement defaultCheckButton;
	
	@iOSXCUITFindBy(accessibility = "Burst")
	private WebElement customBurstButton;
	
	@iOSXCUITFindBy(xpath = "(//*[@label='Check'])[2]")
	private WebElement tintedCheckButton;
	
	
	public SegmentedControlsPage(IOSDriver<IOSElement> driver) {
		super(driver);
	}
	
	public boolean isDefaultCheckEnabeled() {
		return defaultCheckButton.isEnabled();
	}

	public void longPressBurstButton() {
		IOSAppOperations.longPressElement(driver, customBurstButton);
	}
	
	public void tapTintedCheck() {
		IOSAppOperations.tapOnElement(driver, tintedCheckButton);
	}
}
