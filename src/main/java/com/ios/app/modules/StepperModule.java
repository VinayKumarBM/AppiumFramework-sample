package com.ios.app.modules;

import static org.testng.Assert.assertEquals;

import com.ios.app.pages.SteppersPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class StepperModule {
	private AppiumDriver<MobileElement> driver;
	private SteppersPage steppersPage;
	
	public StepperModule(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		steppersPage = new SteppersPage(driver);
	}

	public void incrementStepper(String stepperType, int numberOfIncrement) {
		for(int i=0; i< numberOfIncrement; i++) {
			steppersPage.incrementStepper(stepperType);
		}
	}
	
	public void decrementStepper(String stepperType, int numberOfDecrement) {
		for(int i=0; i< numberOfDecrement; i++) {
			steppersPage.decrementStepper(stepperType);
		}
	}
	
	public void verifyStepperValue(String stepperType, String expectedCount) {
		assertEquals(steppersPage.getStepperCount(stepperType), expectedCount,stepperType+" Stepper count is not matichg");
	}
}
