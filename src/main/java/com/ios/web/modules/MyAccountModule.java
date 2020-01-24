package com.ios.web.modules;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;

import com.ios.web.pages.MyAccountPage;

public class MyAccountModule {
	private WebDriver driver;
	private MyAccountPage myAccountPage;
	
	public MyAccountModule(WebDriver driver) {
		this.driver = driver;
		myAccountPage = new MyAccountPage(driver);
	}

	public void scrolToFooter() {
		myAccountPage.scrolToFooter();
	}
	
	public void verifyPageHeading(String currentPage) {
		assertEquals(myAccountPage.getCurrentPageHeading(), currentPage, "Login failed!!");
	}
}
