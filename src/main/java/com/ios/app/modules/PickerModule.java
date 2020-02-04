package com.ios.app.modules;

import com.ios.app.pages.PickerViewPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class PickerModule {
	private AppiumDriver<MobileElement> driver;
	private PickerViewPage pickerViewPage;
	
	public PickerModule(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		pickerViewPage = new PickerViewPage(driver);
	}

	public void selectPickerComponents(String redComponent, String greenComponent, String blueComponent) {
		pickerViewPage.pickRedColor(redComponent);
		pickerViewPage.pickGreenColor(greenComponent);
		pickerViewPage.pickBlueColor(blueComponent);
	}
}
