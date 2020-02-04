package com.android.app.modules;

import static org.testng.Assert.assertEquals;

import com.android.app.pages.CartPage;
import com.appium.utils.AppOperations;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class CartModule {

	private AppiumDriver<MobileElement> driver;
	private CartPage cartPage;

	public CartModule(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		cartPage = new CartPage(driver);
	}

	public void verifyProductInCart(String productName) {
		assertEquals(cartPage.getProductName(), productName, "Correct product was not added to cart");
	}

	public void validateCostOfProductsInCart() {
		Double sum = 0.0;
		for(int i=0; i<cartPage.getCountOfProductsInCart(); i++) {
			String price = cartPage.getProductPriceByIndex(i);
			sum = sum + getAmount(price);
		}

		assertEquals(getAmount(cartPage.getCartTotalAmount()), sum, "Total purchase amount is not correct in Cart");
	}

	private static Double getAmount(String price) {
		price = price.replaceAll("[$]", "");
		return Double.valueOf(price);
	}

	public void subscribeToEmail() {
		cartPage.tapOnSendEmailCheckbox();
	}

	public void validateTermsAndConditions(String termsTitle) {
		cartPage.longPressTnCButton();
		assertEquals(cartPage.getTermsAndConditionsTitle(), termsTitle, "T&C Popup was not displayed");		
		cartPage.clickCloseTermsAndConditionsButton();
	}

	public void completePurchase() {
		cartPage.clickCompletePurchaseButton();
	}

	public String enterTextIntoGoogleSearch(String key) {
		String currentContext = AppOperations.switchContext(driver);
		cartPage.enterSearchKey(key);
		return currentContext;
	}
}
