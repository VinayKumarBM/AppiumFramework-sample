package com.android.app.modules;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.android.app.pages.CalculatorAppPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class CalculatorModule {

	private AppiumDriver<MobileElement> driver;
	private CalculatorAppPage calculator;
	
	public CalculatorModule(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		calculator = new CalculatorAppPage(driver);
	}
	
	public void calulate(String number1, String number2, String operation) {
		//calculator.clickOnNumber(number1);
		enterNumber(number1);
		calculator.performOperation(operation);
		enterNumber(number2);
		calculator.clickOnEquals();
	}

	public void verifyResultDisplayed(String result) {
		assertEquals(calculator.getDisplayedResult(), result," Result did not match");
	}
	
	public void clearData() {
		calculator.clickOnClear();
		assertTrue(calculator.isDeleteButtonDisplayed(), "Data is not cleared!");
	}
	
	private void enterNumber(String number) {
		for(char n: number.toCharArray())
		calculator.clickOnNumber(n+"");
	}
}
