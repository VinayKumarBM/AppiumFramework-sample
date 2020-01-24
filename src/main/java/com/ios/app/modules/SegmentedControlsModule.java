package com.ios.app.modules;

import static org.testng.Assert.assertFalse;

import com.ios.app.pages.SegmentedControlsPage;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class SegmentedControlsModule {
	private IOSDriver<IOSElement> driver;
	private SegmentedControlsPage segmentedControlsPage;

	public SegmentedControlsModule(IOSDriver<IOSElement> driver) {
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
