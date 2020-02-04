package com.ios.app.pages;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appium.base.page.BasePage;
import com.appium.utils.WaitUtil;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class SlidersPage extends BasePage {

	private final Logger LOG = LoggerFactory.getLogger(SlidersPage.class);
	private final String sliderXpath = "(//*[@label='%s']/../following-sibling::XCUIElementTypeCell/XCUIElementTypeSlider)[1]";
	
	@iOSXCUITFindBy(accessibility = "Animated")
	private WebElement image;
	
	public SlidersPage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
		
	public void setSliderTo(String sliderType, String slideValue) {
		driver.findElementByXPath(String.format(sliderXpath, sliderType)).sendKeys(slideValue);
		WaitUtil.pause(1);
	}
	
	public String getSliderValue(String sliderType) {
		String value = driver.findElementByXPath(String.format(sliderXpath, sliderType)).getAttribute("value");
		LOG.info(sliderType+" slider value: "+value);
		return value;
	}
}
