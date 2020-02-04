package com.android.app.modules;

import java.util.List;

import com.android.app.pages.ProductsPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class ProductsModule {

	private AppiumDriver<MobileElement> driver;
	private ProductsPage productsPage;
	
	public ProductsModule(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		productsPage = new ProductsPage(driver);
	}
	
	public void addSingleProductToCart(String productName) {
		productsPage.addProdutToCart(productName);
		productsPage.clickCartButton();
	}

	public void addMultipleProductsToCart(List<String> productList) {
		for(String productName: productList) {
			productsPage.addProdutToCart(productName);
		}
		productsPage.clickCartButton();
	}
}
