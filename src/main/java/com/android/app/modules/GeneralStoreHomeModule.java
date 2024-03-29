package com.android.app.modules;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.android.app.pages.GeneralStoreHomePage;
import com.appium.utils.AppOperations;
import com.appium.utils.WaitUtil;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class GeneralStoreHomeModule {

	private AppiumDriver<MobileElement> driver;
	private GeneralStoreHomePage homePage;
	
	public GeneralStoreHomeModule(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		homePage = new GeneralStoreHomePage(driver);
	}
	
	public void fillShoppingForm(String coutry, String name, String gender) {
		homePage.selectCountry(coutry);
		homePage.enterName(name);
		homePage.selectGender(gender);
		homePage.clickLetsShopButton();
	}
	
	public void shoppingFormErrorValidation(String country, String errorMessage) {
		homePage.selectCountry(country);	  
		homePage.clickLetsShopButton();
		assertEquals(homePage.getErrorMessage(), errorMessage, "Valid Error message was not displayed");
	}

	public void verifyCountrySelectionDropdownIsDisplayed(String currentContext) {
		AppOperations.pressBackKeyAndroid(driver);
		WaitUtil.pause(2);
		AppOperations.switchToContext(driver, currentContext);
		assertTrue(homePage.isCountryDropdownDisplayed(),"Did not switch back to Native APP");
	}

}
