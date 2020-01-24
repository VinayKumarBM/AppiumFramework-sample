package com.appium.base.page;

import java.time.Duration;

import org.openqa.selenium.support.PageFactory;

import com.appium.utils.Config;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AndroidBasePage {
	protected AndroidDriver<AndroidElement> driver;
	
	public AndroidBasePage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(Long.parseLong(Config.getProperties("explicitWait")))), this);
	}

}
