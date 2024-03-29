package com.android.app.test;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.android.app.modules.CartModule;
import com.android.app.modules.GeneralStoreHomeModule;
import com.android.app.modules.ProductsModule;
import com.appium.base.TestBase;

public class GenralStoreTest extends TestBase{

	@Test
	public void fillShoppingForm() {
		new GeneralStoreHomeModule(driver).fillShoppingForm("Angola", "Vinay", "Female");
	}

	@Test
	public void shoppingFormErrorValidation() {
		new GeneralStoreHomeModule(driver).shoppingFormErrorValidation("Brazil", "Please enter your user name");
	}

	@Test
	public void addProductToCart() {
		new GeneralStoreHomeModule(driver).fillShoppingForm("Angola", "Vinay", "Female");
		String productName = "Jordan 6 Rings";
		new ProductsModule(driver).addSingleProductToCart(productName);
		new CartModule(driver).verifyProductInCart(productName);
	}

	@Test
	public void validateSumOfProductsInCart() {
		new GeneralStoreHomeModule(driver).fillShoppingForm("Angola", "Vinay", "Female");
		
		List<String> productList = new ArrayList<String>();
		productList.add("Converse All Star");
		productList.add("PG 3");
		new ProductsModule(driver).addMultipleProductsToCart(productList);
		
		new CartModule(driver).validateCostOfProductsInCart();
	}
	
	@Test
	public void mobileGesturesOnCart() {
		new GeneralStoreHomeModule(driver).fillShoppingForm("Angola", "Vinay", "Female");
		String productName = "Jordan 6 Rings";
		new ProductsModule(driver).addSingleProductToCart(productName);
		
		CartModule cartModule = new CartModule(driver);
		cartModule.subscribeToEmail();
		cartModule.validateTermsAndConditions("Terms Of Conditions");
		cartModule.completePurchase();
	}
	
	@Test 
	public void testSwitchingFromNativeToWebAndBack() {
		GeneralStoreHomeModule generalStoreModule = new GeneralStoreHomeModule(driver);
		generalStoreModule.fillShoppingForm("Angola", "Vinay", "Female");
		new ProductsModule(driver).addSingleProductToCart("Jordan 6 Rings");
		CartModule cartModule = new CartModule(driver);
		cartModule.completePurchase();
		String currentContext = cartModule.enterTextIntoGoogleSearch("Chelsea FC");
		generalStoreModule.verifyCountrySelectionDropdownIsDisplayed(currentContext);		
	}
}
