package com.android.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appium.base.page.AndroidBasePage;
import com.appium.utils.AndroidAppOperations;
import com.appium.utils.WaitUtil;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductsPage extends AndroidBasePage {
	private final Logger LOG = LoggerFactory.getLogger(ProductsPage.class);
	private final String addToCartXpath = "//*[@text='%s']/..//*[@text='ADD TO CART']";

	@AndroidFindBy (id = "appbar_btn_cart")
	private WebElement cartButton;
	
	@AndroidFindBy (xpath = "//android.widget.Toast[1]")
	private WebElement errorMessage;
	
	public ProductsPage(AndroidDriver<AndroidElement> driver) {
		super(driver);
	}
	
	public void addProdutToCart(String productName) {
		AndroidAppOperations.scrollToElement(driver, By.xpath(String.format(addToCartXpath, productName)));
		driver.findElementByXPath(String.format(addToCartXpath, productName)).click();		
	}
	
	public void clickCartButton() {
		cartButton.click();
		WaitUtil.pause(2);
	}

	public String getErrorMessage() {
		String errorMsg = errorMessage.getAttribute("name");
		LOG.info("Error Message: "+errorMsg);
		return errorMsg;
	}
}
