package com.ios.app.modules;

import com.ios.app.pages.ImageViewPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class ImageViewModule {
	private AppiumDriver<MobileElement> driver;
	private ImageViewPage imageViewPage;
	
	public ImageViewModule(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		imageViewPage = new ImageViewPage(driver); 
	}

	public void zoomImage() {		
		imageViewPage.zoomImage(0.5,  -0.1);
	}
}
