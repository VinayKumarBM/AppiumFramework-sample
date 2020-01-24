package com.android.app.modules;

import static org.testng.Assert.assertEquals;

import com.android.app.pages.GeneralStoreHomePage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class GeneralStoreHomeModule {

	private AndroidDriver<AndroidElement> driver;
	private GeneralStoreHomePage homePage;
	
	public GeneralStoreHomeModule(AndroidDriver<AndroidElement> driver) {
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

}
