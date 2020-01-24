package com.ios.app.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appium.base.page.IOSBasePage;
import com.appium.utils.WaitUtil;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class SteppersPage extends IOSBasePage{

	private final Logger LOG = LoggerFactory.getLogger(SteppersPage.class);
	private final String stepperIncrementXpath = "(//XCUIElementTypeOther[@label='%s']/../following-sibling::XCUIElementTypeCell/XCUIElementTypeButton[@name='Increment'])[1]";
	private final String stepperDecrementXpath = "(//XCUIElementTypeOther[@label='%s']/../following-sibling::XCUIElementTypeCell/XCUIElementTypeButton[@name='Decrement'])[1]";
	private final String stepperCountXpath = "(//XCUIElementTypeOther[@label='%s']/../following-sibling::XCUIElementTypeCell/XCUIElementTypeStaticText)[1]";
	
	public SteppersPage(IOSDriver<IOSElement> driver) {
		super(driver);
	}
	
	public String getStepperCount(String stepperType) {
		WaitUtil.pause(1);
		String count = driver.findElementByXPath(String.format(stepperCountXpath, stepperType)).getText();
		LOG.info(stepperType+" Count: "+count);
		return count;
	}

	public void incrementStepper(String stepperType) {
		driver.findElementByXPath(String.format(stepperIncrementXpath, stepperType)).click();
	}

	public void decrementStepper(String stepperType) {
		driver.findElementByXPath(String.format(stepperDecrementXpath, stepperType)).click();
	}
}
