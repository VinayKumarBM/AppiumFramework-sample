package com.appium.utils;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;

import java.time.Duration;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class IOSAppOperations {

	private static final Logger LOG = LoggerFactory.getLogger(IOSAppOperations.class);

	public static void pressBackKey(IOSDriver<IOSElement>  driver)
	{
		driver.navigate().back();
		LOG.info("Back Key pressed");
	}

	public static void hideKeyBoard(IOSDriver<IOSElement>  driver)
	{
		driver.hideKeyboard();
		LOG.info("Keyboard hidden");
	}
	
	public static void longPressElement(IOSDriver<IOSElement> driver, WebElement elementToLongPress) {
		new TouchAction(driver)
			.longPress(longPressOptions().withElement(element(elementToLongPress)).withDuration(Duration.ofSeconds(2))).perform();
	}

	public static void tapOnElement(IOSDriver<IOSElement> driver, WebElement elementToTap) {
		new TouchAction(driver).tap(tapOptions().withElement(element(elementToTap))).perform();
	}
	
	public static String switchContext(IOSDriver<IOSElement> driver) {
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
	
	public static void scrollToElement(IOSDriver<IOSElement> driver, By by) {
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

	public static boolean isElementPresent(IOSDriver<IOSElement> driver, By by) {
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
	
	public static boolean isElementPresent(IOSDriver<IOSElement> driver, WebElement elemet) {
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
	
	public static void zoomOperation(IOSDriver<IOSElement> driver, WebElement element, double scale, double velocity) {
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		HashMap<String, Object> zoomObject = new HashMap<String, Object>();
		zoomObject.put("scale", scale);
		zoomObject.put("velocity", velocity);
		zoomObject.put("element", ((RemoteWebElement)element).getId()); 
		js.executeScript("mobile: pinch", zoomObject);
	}
	
	public static void scrollAndClick(IOSDriver<IOSElement> driver, WebElement element, String direction) {
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", direction);
		scrollObject.put("element", ((RemoteWebElement)element).getId()); 
		js.executeScript("mobile: swipe", scrollObject);
	}
}
