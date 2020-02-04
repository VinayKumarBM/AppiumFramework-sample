package com.ios.app.modules;

import com.ios.app.pages.AlertViewPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AlertViewModule {
	private AppiumDriver<MobileElement> driver;
	private AlertViewPage alertViewPage;
	
	public AlertViewModule(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		alertViewPage = new AlertViewPage(driver); 
	}

	public void enterShortTextInAlertView(String textToEnter) {
		alertViewPage.clickTextEnter();
		alertViewPage.enterShortText(textToEnter);
		alertViewPage.clickOk();
	}
}
