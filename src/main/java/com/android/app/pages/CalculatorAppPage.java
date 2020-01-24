package com.android.app.pages;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appium.base.page.AndroidBasePage;
import com.appium.utils.AndroidAppOperations;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CalculatorAppPage extends AndroidBasePage {
	private final Logger LOG = LoggerFactory.getLogger(CalculatorAppPage.class);
	private final String numberPadXpath = "//*[@text='%s']";
	private final String operationsXpath = "//*[@content-desc='%s']";

	@AndroidFindBy (xpath = "//*[@text='=']")
	private WebElement equalButton;
	
	@AndroidFindBy (xpath = "//android.widget.LinearLayout/android.widget.TextView")
	private WebElement resultsText;
	
	@AndroidFindBy (xpath = "//*[@text='CLR']")
	private WebElement clearButton;
	
	@AndroidFindBy (xpath = "//*[@text='DEL']")
	private WebElement deleteButton;
	
	public CalculatorAppPage(AndroidDriver<AndroidElement> driver) {
		super(driver);
	}
	
	public void clickOnNumber(String number) {
		driver.findElementByXPath(String.format(numberPadXpath, number)).click();
	}
	
	public void clickOnEquals() {
		equalButton.click();
	}
	
	public void clickOnClear() {
		clearButton.click();
	}
	
	public boolean isDeleteButtonDisplayed() {
		return AndroidAppOperations.isElementPresent(driver, deleteButton);
	}
	
	public void performOperation(String operation) {
		driver.findElementByXPath(String.format(operationsXpath, operation)).click();
	}
	
	public String getDisplayedResult() {
		String result = resultsText.getText();
		LOG.info("Result is: "+result);
		return result;
	}
}
