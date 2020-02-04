package com.appium.test;


import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.appium.utils.AppOperations;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;


public class AppiumTestNativeApp extends TestBaseNativeApp{

	@Test //(enabled = false)
	public void setWiFiName() throws Exception {
		driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();
		driver.findElementByAndroidUIAutomator("text(\"3. Preference dependencies\")").click();
		MobileElement wiFiCheckbox = driver.findElementById("android:id/checkbox");
		if (! wiFiCheckbox.isSelected()) {
			wiFiCheckbox.click();
		}
		driver.findElementByXPath("//android.widget.TextView[@text='WiFi settings']").click();
		driver.findElementByClassName("android.widget.EditText").clear();		
		String nameOfWiFi = String.format("Vinay_%s",String.valueOf(System.currentTimeMillis()).substring(6));
		System.out.println("WiFi Name Enter: "+nameOfWiFi);
		driver.findElementByClassName("android.widget.EditText").sendKeys(nameOfWiFi);
		driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//android.widget.TextView[@text='WiFi settings']").click();
		String wiFiName = driver.findElementByClassName("android.widget.EditText").getAttribute("text");
		System.out.println("WiFi Name Entered: "+wiFiName);
		assertEquals(wiFiName, nameOfWiFi,"WiFi Name was not set correctly ");
		AppOperations.hideKeyBoard(driver);
	}

	@Test //(enabled = false)
	public void longPressInMobile() {
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		AndroidTouchAction touchAction = new AndroidTouchAction(driver);
		WebElement webElement = driver.findElementByAndroidUIAutomator("text(\"Expandable Lists\")");
		// This will perform tap action on the element
		touchAction.tap(tapOptions().withElement(element(webElement))).perform();
		driver.findElementByXPath("//android.widget.TextView[@text='1. Custom Adapter']").click();
		webElement = driver.findElementByAndroidUIAutomator("text(\"People Names\")");
		// This will perform long press action on the element
		touchAction.longPress(longPressOptions().withElement(element(webElement)).withDuration(Duration.ofSeconds(2)))
		.release().perform();
		boolean menuIsDisplayed = driver.findElementById("android:id/title").isDisplayed();
		System.out.println("Sample Menu is Displayed: "+menuIsDisplayed);
		assertTrue(menuIsDisplayed, "Sample Menu was not displayed");
	}

	@Test //(enabled = false)
	public void swipeGestureInMobile() {
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		driver.findElementByAndroidUIAutomator("text(\"Date Widgets\")").click();
		driver.findElementByAndroidUIAutomator("text(\"2. Inline\")").click();
		driver.findElementByXPath("//*[@content-desc='9']").click();

		AndroidTouchAction touchAction = new AndroidTouchAction(driver);
		WebElement webElementFrom = driver.findElementByXPath("//*[@content-desc='15']");
		WebElement webElementTo = driver.findElementByXPath("//*[@content-desc='30']");
		// Here we are long pressing an element for 2 sec and then moving it to another element
		touchAction.longPress(longPressOptions().withElement(element(webElementFrom)).withDuration(Duration.ofSeconds(2)))
		.moveTo(element(webElementTo)).release().perform();
	}

	@Test //(enabled = false)
	public void scrollingInMobile() {
		driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));");	
	}

	@Test //(enabled = false)
	public void dragAndDropInMobile() {
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		driver.findElementByAndroidUIAutomator("text(\"Drag and Drop\")").click();

		WebElement source = driver.findElementsByClassName("android.view.View").get(0);
		WebElement destination = driver.findElementsByClassName("android.view.View").get(1);
		new TouchAction(driver).longPress(element(source)).moveTo(element(destination)).release().perform();
		AppOperations.pressBackKeyAndroid(driver);
	}

}