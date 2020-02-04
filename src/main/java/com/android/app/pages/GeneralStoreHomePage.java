package com.android.app.pages;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appium.base.page.BasePage;
import com.appium.utils.AppOperations;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class GeneralStoreHomePage extends BasePage {
	
	private final Logger LOG = LoggerFactory.getLogger(GeneralStoreHomePage.class);
	private final String genderXpath = "//android.widget.RadioButton[@text='%s']";

	@AndroidFindBy (className = "android.widget.Spinner")
	private WebElement countryDropdown;
	
	@AndroidFindBy (className = "android.widget.EditText")
	private WebElement nameInputField;
	
	@AndroidFindBy (className = "android.widget.Button")
	private WebElement letsShopButton;
	
	@AndroidFindBy (xpath = "//android.widget.Toast[1]")
	private WebElement errorMessage;
	
	public GeneralStoreHomePage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
	
	public void selectCountry(String country) {
		countryDropdown.click();
		AppOperations.scrollToContainedTextInAndroid(driver, country).click();
	}
	
	public void enterName(String name) {
		nameInputField.clear();
		nameInputField.sendKeys(name);
		AppOperations.hideKeyBoard(driver);
	}
	
	public void selectGender(String gender) {
		driver.findElementByXPath(String.format(genderXpath, gender)).click();
	}
	
	public void clickLetsShopButton() {
		letsShopButton.click();
	}

	public String getErrorMessage() {
		String errorMsg = errorMessage.getAttribute("name");
		LOG.info("Error Message: "+errorMsg);
		return errorMsg;
	}
	
	public boolean isCountryDropdownDisplayed() {
			return AppOperations.isElementPresent(driver, countryDropdown);
	}
}
