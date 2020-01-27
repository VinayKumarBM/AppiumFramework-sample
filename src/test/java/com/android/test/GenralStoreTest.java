package com.android.test;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.android.app.modules.CartModule;
import com.android.app.modules.GeneralStoreHomeModule;
import com.android.app.modules.ProductsModule;
import com.appium.base.test.AndroidAppTestBase;
import com.appium.utils.AndroidAppOperations;
import com.appium.utils.WaitUtil;

public class GenralStoreTest extends AndroidAppTestBase{

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
	
	@Test (enabled = false)
	public void testSwitchingFromNativeToWebAndBack() {
		addProductToCart();
		driver.findElementById("btnProceed").click();
		String currentContext = AndroidAppOperations.switchContext(driver);
		System.out.println("Switched from "+currentContext+" to "+driver.getContext());
		driver.findElement(By.name("q")).sendKeys("hello");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		
		AndroidAppOperations.pressBackKey(driver);
		driver.context(currentContext);
		WaitUtil.pause(2);
		assertTrue(driver.findElementByClassName("android.widget.Spinner").isDisplayed(),"Did not switch back to Native APP");
	}
}
