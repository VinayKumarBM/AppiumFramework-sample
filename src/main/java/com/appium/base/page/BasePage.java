package com.appium.base.page;

import java.time.Duration;

import org.openqa.selenium.support.PageFactory;

import com.appium.utils.Config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BasePage {
	protected AppiumDriver<MobileElement> driver;
	
	public BasePage(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(Long.parseLong(Config.getProperties("explicitWait")))), this);
	}

}
