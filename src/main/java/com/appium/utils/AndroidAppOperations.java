package com.appium.utils;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AndroidAppOperations {

	private static final Logger LOG = LoggerFactory.getLogger(AndroidAppOperations.class);
	
	public static void pressHomeKey(AndroidDriver<AndroidElement>  driver)
	{
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
		LOG.info("Home Key pressed");
	}

	public static void pressBackKey(AndroidDriver<AndroidElement>  driver)
	{
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		LOG.info("Back Key pressed");
	}

	public static void pressMenuKey(AndroidDriver<AndroidElement>  driver)
	{
		driver.pressKey(new KeyEvent(AndroidKey.MENU));
		LOG.info("Menu Key pressed");
	}

	public static void hideKeyBoard(AndroidDriver<AndroidElement>  driver)
	{
		driver.hideKeyboard();
		LOG.info("Keyboard hidden");
	}
	
	public static AndroidElement scrollToContainedText(AndroidDriver<AndroidElement>  driver, String containedText)
	{	
		//  return driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"containedText\"));");
		return driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + containedText + "\").instance(0))"));  
	}	
	
	public static void longPressElement(AndroidDriver<AndroidElement> driver, WebElement elementToLongPress) {
		new TouchAction(driver)
			.longPress(longPressOptions().withElement(element(elementToLongPress)).withDuration(Duration.ofSeconds(2)))
				.release().perform();
	}

	public static void tapOnElement(AndroidDriver<AndroidElement> driver, WebElement elementToTap) {
		new TouchAction(driver)
			.tap(tapOptions().withElement(element(elementToTap))).perform();
	}
	
	public static String switchContext(AndroidDriver<AndroidElement> driver) {
		WaitUtil.pause(10);
		String currentContext = driver.getContext();
		Set<String> contexts = driver.getContextHandles();
		LOG.info("Contexts: "+contexts);
		for(String context: contexts) {
			if(!context.equals(currentContext)) {
				driver.context(context);
				LOG.info("Switched to "+context);
				break;
			}
		}
		return currentContext;
	}
	
	public static void switchToContext(AndroidDriver<AndroidElement> driver, String context) {
		driver.context(context);
	}
	
	public static void scrollToElement(AndroidDriver<AndroidElement> driver, By by) {
		Dimension size = driver.manage().window().getSize();
		int anchor = (int) (size.width / 2);
		// Swipe up to scroll down
		int startPoint = (int) (size.height * 0.95);
		int endPoint = (int) (size.height * 0.5);
		int count = 1;
		while (!isElementPresent(driver, by)) {
			count++;
			new TouchAction(driver)
				.longPress(point(anchor, startPoint))
					.moveTo(point(anchor, endPoint))
						.release()
							.perform();
			if(count>20) {
				break;	  
			}
		}
	}

	public static boolean isElementPresent(AndroidDriver<AndroidElement> driver, By by) {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		try {
			return driver.findElement(by).isDisplayed();
		} catch (Exception e) {
			return false;
		}
		finally {
			driver.manage().timeouts().implicitlyWait(Config.getIntegerProperty("implicitWait"), TimeUnit.SECONDS);
		}		
	}
	
	public static boolean isElementPresent(AndroidDriver<AndroidElement> driver, WebElement elemet) {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		try {
			return elemet.isDisplayed();
		} catch (Exception e) {
			return false;
		}
		finally {
			driver.manage().timeouts().implicitlyWait(Config.getIntegerProperty("implicitWait"), TimeUnit.SECONDS);
		}		
	}
}
