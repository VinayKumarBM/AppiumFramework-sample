package com.appium.base.page;

import java.time.Duration;

import org.openqa.selenium.support.PageFactory;

import com.appium.utils.Config;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class IOSBasePage {
	protected IOSDriver<IOSElement> driver;
	
	public IOSBasePage(IOSDriver<IOSElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(Long.parseLong(Config.getProperties("explicitWait")))), this);
	}

}
