package com.android.app.modules;

import static org.testng.Assert.assertEquals;

import com.android.app.pages.CartPage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class CartModule {

	private AndroidDriver<AndroidElement> driver;
	private CartPage cartPage;

	public CartModule(AndroidDriver<AndroidElement> driver) {
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
}
