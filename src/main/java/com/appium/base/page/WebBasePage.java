package com.appium.base.page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.appium.utils.Config;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class WebBasePage {
	protected WebDriver driver;
	
	public WebBasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(Long.parseLong(Config.getProperties("explicitWait")))), this);
	}

}
