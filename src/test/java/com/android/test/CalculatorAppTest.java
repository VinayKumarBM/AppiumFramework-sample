package com.android.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.android.app.modules.CalculatorModule;
import com.appium.base.test.AndroidInstalledAppTestBase;

public class CalculatorAppTest extends AndroidInstalledAppTestBase{

	@Test (dataProvider="calculatorData")
	public void testCalculator(String input1, String input2, String operation, String result) throws Exception {
		CalculatorModule calculator = new CalculatorModule(driver);
		calculator.calulate(input1, input2, operation);
		calculator.verifyResultDisplayed(result);
		calculator.clearData();
	}

	@DataProvider (name="calculatorData")
	public Object[][] calculatorInputData() {
		return new Object[][] {{"12","34","plus","46"},{"25","4","multiply","100"},{"70","69","minus","1"},{"81","3","divide","27"}};	
	}

}
