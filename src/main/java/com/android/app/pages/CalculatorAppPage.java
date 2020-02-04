package com.android.app.pages;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appium.base.page.BasePage;
import com.appium.utils.AppOperations;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CalculatorAppPage extends BasePage {
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
	
	public CalculatorAppPage(AppiumDriver<MobileElement> driver) {
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
		return AppOperations.isElementPresent(driver, deleteButton);
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
