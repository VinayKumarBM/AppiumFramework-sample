package com.ios.app.modules;

import com.ios.app.pages.PickerViewPage;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PickerModule {
	private IOSDriver<IOSElement> driver;
	private PickerViewPage pickerViewPage;
	
	public PickerModule(IOSDriver<IOSElement> driver) {
		this.driver = driver;
		pickerViewPage = new PickerViewPage(driver);
	}

	public void selectPickerComponents(String redComponent, String greenComponent, String blueComponent) {
		pickerViewPage.pickRedColor(redComponent);
		pickerViewPage.pickGreenColor(greenComponent);
		pickerViewPage.pickBlueColor(blueComponent);
	}
}
