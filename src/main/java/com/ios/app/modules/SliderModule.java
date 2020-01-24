package com.ios.app.modules;

import static org.testng.Assert.assertEquals;

import com.ios.app.pages.SlidersPage;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class SliderModule {
	private IOSDriver<IOSElement> driver;
	private SlidersPage sliderPage;
	
	public SliderModule(IOSDriver<IOSElement> driver) {
		this.driver = driver;
		sliderPage = new SlidersPage(driver);
	}

	public void setSliderValue(String sliderType, String slideValue) {
		sliderPage.setSliderTo(sliderType, slideValue);
	}
	
	public void verifySliderValue(String sliderType, String slideValue) {
		int value = (int) (Double.valueOf(slideValue)*100);
		assertEquals(sliderPage.getSliderValue(sliderType), value+"%", sliderType+" was not set to correct value");
	}
}
