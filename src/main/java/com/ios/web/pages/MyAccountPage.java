package com.ios.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appium.base.page.WebBasePage;
import com.appium.utils.WebOperations;

public class MyAccountPage extends WebBasePage {
	
	private final Logger LOG = LoggerFactory.getLogger(MyAccountPage.class);
			
	@FindBy (xpath = "//div[@class='breadcrumb clearfix']/span[@class='navigation_page']")
	private WebElement currentPageText;
	
	@FindBy (id = "footer")
	private WebElement footerSection;
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	public String getCurrentPageHeading() {
		String currentPage = currentPageText.getText();
		LOG.info("Current Page is: "+currentPage);
		return currentPage;
	}
	
	public void scrolToFooter() {
		WebOperations.scrollToElement(driver, footerSection);
	}
}
