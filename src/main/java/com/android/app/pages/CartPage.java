package com.android.app.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appium.base.page.AndroidBasePage;
import com.appium.utils.AndroidAppOperations;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CartPage extends AndroidBasePage {
	
	private final Logger LOG = LoggerFactory.getLogger(CartPage.class);
	private final String addToCartXpath = "//*[@text='%s']/..//*[@text='ADD TO CART']";
	
	@AndroidFindBy (id = "productName")
	private WebElement productNameText;
	
	@AndroidFindBy (id = "productPrice")
	private List<WebElement> productPriceText;
	
	@AndroidFindBy (id = "totalAmountLbl")
	private WebElement totalAmountText;
	
	@AndroidFindBy (className = "android.widget.CheckBox")
	private WebElement sendEmailCheckbox;
	
	@AndroidFindBy (id = "termsButton")
	private WebElement termsAndConditionsButton;
	
	@AndroidFindBy (id = "alertTitle")
	private WebElement termsAndConditionsTitleText;
	
	@AndroidFindBy (xpath = "//android.widget.Button[@text='CLOSE']")
	private WebElement closeTermsAndConditionsButton;
	
	@AndroidFindBy (id = "btnProceed")
	private WebElement completePurchaseButton;
	
	@FindBy (name = "q")
	private WebElement googleSearchInputbox;
	
	public CartPage(AndroidDriver<AndroidElement> driver) {
		super(driver);
	}
	
	public void addProdutToCart(String productName) {
		AndroidAppOperations.scrollToContainedText(driver, productName);
		driver.findElementByXPath(String.format(addToCartXpath, productName)).click();
	}
	
	public int getCountOfProductsInCart() {
		LOG.info("Products in Cart: "+productPriceText.size());
		return productPriceText.size();
	}
	
	public String getProductPriceByIndex(int index) {
		String cost = productPriceText.get(index).getText();
		LOG.info("Product "+(index+1)+" cost: "+cost);
		return cost;
	}
	
	public String getProductName() {		
		String productName = productNameText.getText();
		LOG.info("Product Name: "+productName);
		return productName;
	}
	
	public String getCartTotalAmount() {		
		String totalCost = totalAmountText.getText();
		LOG.info("Cart Total: "+totalCost);
		return totalCost;
	}

	public void tapOnSendEmailCheckbox() {
		AndroidAppOperations.tapOnElement(driver, sendEmailCheckbox);
	}
	
	public void longPressTnCButton() {
		AndroidAppOperations.longPressElement(driver, termsAndConditionsButton);
	}
	
	public String getTermsAndConditionsTitle() {		
		String title = termsAndConditionsTitleText.getText();
		LOG.info("Terms & Conditions Title: "+title);
		return title;
	}
	
	public void clickCloseTermsAndConditionsButton() {
		closeTermsAndConditionsButton.click();
	}
	
	public void clickCompletePurchaseButton() {
		completePurchaseButton.click();
	}
	
	public void enterSearchKey(String key) {
		googleSearchInputbox.sendKeys(key);
		googleSearchInputbox.sendKeys(Keys.ENTER);
	}
}
