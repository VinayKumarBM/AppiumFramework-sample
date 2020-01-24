package com.ios.app.modules;

import com.ios.app.pages.ImageViewPage;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class ImageViewModule {
	private IOSDriver<IOSElement> driver;
	private ImageViewPage imageViewPage;
	
	public ImageViewModule(IOSDriver<IOSElement> driver) {
		this.driver = driver;
		imageViewPage = new ImageViewPage(driver); 
	}

	public void zoomImage() {		
		imageViewPage.zoomImage(0.5,  -0.1);
	}
}
