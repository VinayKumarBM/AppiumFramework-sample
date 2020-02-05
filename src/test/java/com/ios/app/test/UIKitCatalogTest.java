package com.ios.app.test;

import org.testng.annotations.Test;

import com.appium.base.TestBase;
import com.ios.app.modules.AlertViewModule;
import com.ios.app.modules.ImageViewModule;
import com.ios.app.modules.PickerModule;
import com.ios.app.modules.SegmentedControlsModule;
import com.ios.app.modules.SliderModule;
import com.ios.app.modules.StepperModule;
import com.ios.app.modules.UIKitCatalogModule;

public class UIKitCatalogTest extends TestBase{

	@Test
	public void tapTest() throws Exception {
		new UIKitCatalogModule(driver).navigateToSegmentedControls();
		SegmentedControlsModule segmentedControlsModule = new SegmentedControlsModule(driver);
		segmentedControlsModule.longPressBurstOption();
		segmentedControlsModule.tapTintedCheckOption();
		segmentedControlsModule.verifyDefaultCheckIsEnabled();
	}
	
	@Test
	public void zoomOperations() {
		new UIKitCatalogModule(driver).navigateToImageView();
		new ImageViewModule(driver).zoomImage();
	}

	@Test
	public void sliderTest() throws Exception {
		new UIKitCatalogModule(driver).navigateToSliders();
		SliderModule sliderModule = new SliderModule(driver);
		String sliderType = "TINTED";
		String slideValue = "0.6";
		sliderModule.setSliderValue(sliderType, slideValue);
		sliderModule.verifySliderValue(sliderType, slideValue);
		
		sliderType = "DEFAULT";
	    slideValue = "0.3";
	    sliderModule.setSliderValue(sliderType, slideValue);
		sliderModule.verifySliderValue(sliderType, slideValue);
		
		sliderType = "CUSTOM";
	    slideValue = "0.6";
	    sliderModule.setSliderValue(sliderType, slideValue);
		sliderModule.verifySliderValue(sliderType, slideValue);
		
		sliderType = "MIN AND MAX IMAGES";
	    slideValue = "0.5";
	    sliderModule.setSliderValue(sliderType, slideValue);
		sliderModule.verifySliderValue(sliderType, slideValue);
	}
	
	@Test
	public void enterText() {
		UIKitCatalogModule  catalog = new UIKitCatalogModule(driver);
		catalog.navigateToAlertViews();		
		AlertViewModule alertView = new AlertViewModule(driver);
		alertView.enterShortTextInAlertView("Hellow Vinay!!");
		driver.navigate().back();
	}
	
	@Test
	public void getStepperCountTest() throws InterruptedException {
		new UIKitCatalogModule(driver).navigateToSteppers();
		String stepperType = "TINTED";
		StepperModule stepperModule = new StepperModule(driver);
		
		stepperModule.incrementStepper(stepperType, 3);
		stepperModule.verifyStepperValue(stepperType, "3");
		stepperModule.decrementStepper(stepperType, 2);
		stepperModule.verifyStepperValue(stepperType, "1");
		
		stepperType = "DEFAULT";
		stepperModule.incrementStepper(stepperType, 4);
		stepperModule.verifyStepperValue(stepperType, "4");
		stepperModule.decrementStepper(stepperType, 1);
		stepperModule.verifyStepperValue(stepperType, "3");
		
		stepperType = "CUSTOM";
		stepperModule.incrementStepper(stepperType, 5);
		stepperModule.verifyStepperValue(stepperType, "6");
		stepperModule.decrementStepper(stepperType, 3);
		stepperModule.verifyStepperValue(stepperType, "2");
	}

	@Test
	public void scrollTest() {
		new UIKitCatalogModule(driver).navigateToWebView();
	}
	
	@Test
	public void pickerWheelTest() {
		new UIKitCatalogModule(driver).navigateToPickerView();
		new PickerModule(driver).selectPickerComponents("150", "100", "200");
	}

}
