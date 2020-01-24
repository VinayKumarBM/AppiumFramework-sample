package com.ios.app.modules;

import com.ios.app.pages.AlertViewPage;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class AlertViewModule {
	private IOSDriver<IOSElement> driver;
	private AlertViewPage alertViewPage;
	
	public AlertViewModule(IOSDriver<IOSElement> driver) {
		this.driver = driver;
		alertViewPage = new AlertViewPage(driver); 
	}

	public void enterShortTextInAlertView(String textToEnter) {
		alertViewPage.clickTextEnter();
		alertViewPage.enterShortText(textToEnter);
		alertViewPage.clickOk();
	}
}
