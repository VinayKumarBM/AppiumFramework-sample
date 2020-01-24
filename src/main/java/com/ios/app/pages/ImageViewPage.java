package com.ios.app.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appium.base.page.IOSBasePage;
import com.appium.utils.IOSAppOperations;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ImageViewPage extends IOSBasePage {

	private final Logger LOG = LoggerFactory.getLogger(ImageViewPage.class);
		
	@iOSXCUITFindBy(accessibility = "Animated")
	private WebElement image;
	
	public ImageViewPage(IOSDriver<IOSElement> driver) {
		super(driver);
	}
		
	public void zoomImage(double scale, double velocity) {
		IOSAppOperations.zoomOperation(driver, image, scale, velocity);
	}
}
