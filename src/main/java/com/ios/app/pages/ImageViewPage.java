package com.ios.app.pages;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appium.base.BasePage;
import com.appium.utils.AppOperations;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ImageViewPage extends BasePage {

	private final Logger LOG = LoggerFactory.getLogger(ImageViewPage.class);
		
	@iOSXCUITFindBy(accessibility = "Animated")
	private WebElement image;
	
	public ImageViewPage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
		
	public void zoomImage(double scale, double velocity) {
		AppOperations.zoomOperation(driver, image, scale, velocity);
	}
}
