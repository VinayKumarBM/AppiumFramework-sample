package com.appium.utils;

import static io.appium.java_client.touch.offset.PointOption.point;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class WebOperations {
	
	public static void scrollOnAndroidWebPage(AppiumDriver<MobileElement> driver, int swipeCount, int startPos, int endPos) {
		String currentContext = driver.getContext();
		AppOperations.switchToNativeApp(driver);
		scroll(driver, 5, startPos, endPos);
		AppOperations.switchToContext(driver, currentContext);
	}
	
	public static void scrollToElement(AppiumDriver<MobileElement> driver, WebElement element) {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);		
	}
		
	public static void scroll(AppiumDriver<MobileElement> driver, int swipeCount, int startPos, int endPos) {
		Dimension size = driver.manage().window().getSize();
		int anchor = (int) (size.width / 2);
		int startPoint = (int) (size.height * 0.01 * startPos);
		int endPoint = (int) (size.height * 0.01 * endPos);
		for(int i=0; i<swipeCount; i++) {
			new TouchAction(driver)
				.longPress(point(anchor, startPoint))
					.moveTo(point(anchor, endPoint))
						.release()
							.perform();
		}
	}
}
