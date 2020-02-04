package com.ios.app.modules;

import static org.testng.Assert.assertFalse;

import com.ios.app.pages.SegmentedControlsPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class SegmentedControlsModule {
	private AppiumDriver<MobileElement> driver;
	private SegmentedControlsPage segmentedControlsPage;

	public SegmentedControlsModule(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		segmentedControlsPage = new SegmentedControlsPage(driver);
	}

	public void verifyDefaultCheckIsEnabled() {
		assertFalse(segmentedControlsPage.isDefaultCheckEnabeled(),"Check Segment was enabled!");
	}
	
	public void longPressBurstOption() {
		segmentedControlsPage.longPressBurstButton();
	}
	
	public void tapTintedCheckOption() {
		segmentedControlsPage.tapTintedCheck();
	}
}
