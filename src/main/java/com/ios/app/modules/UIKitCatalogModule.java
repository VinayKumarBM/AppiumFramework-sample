package com.ios.app.modules;

import com.ios.app.pages.UIKitCatalogPage;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class UIKitCatalogModule {
	private IOSDriver<IOSElement> driver;
	private UIKitCatalogPage catalogPage;
	
	public UIKitCatalogModule(IOSDriver<IOSElement> driver) {
		this.driver = driver;
		catalogPage = new UIKitCatalogPage(driver); 
	}

	public void navigateToAlertViews() {
		catalogPage.clickAlertView();
	}
	
	public void navigateToPickerView() {
		catalogPage.clickPickerView();
	}
	
	public void navigateToSegmentedControls() {
		catalogPage.clickSegmentedControls();
	}
	
	public void navigateToImageView() {
		catalogPage.clickImageView();
	}

	public void navigateToSliders() {
		catalogPage.clickSliders();
	}

	public void navigateToWebView() {
		catalogPage.clickWebView();
	}
	
	public void navigateToSteppers() {
		catalogPage.clickStepper();
	}
}
