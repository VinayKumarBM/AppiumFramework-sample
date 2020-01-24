package com.appium.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebOperations {
	
	public static void scrollToBottom(WebDriver driver) {
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0, document.body.scrollHeight)");
	}

	public static void scrollToTop(WebDriver driver) {
		((JavascriptExecutor)driver).executeScript("window.scrollBy(document.body.scrollHeight, 0)");
	}
	
	public static void scrollToElement(WebDriver driver, WebElement element) {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
	}
}
