package com.android.app.modules;

import java.util.List;

import com.android.app.pages.ProductsPage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ProductsModule {

	private AndroidDriver<AndroidElement> driver;
	private ProductsPage productsPage;
	
	public ProductsModule(AndroidDriver<AndroidElement> driver) {
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
