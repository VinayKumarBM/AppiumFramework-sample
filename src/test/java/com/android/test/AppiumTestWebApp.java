package com.android.test;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.appium.base.test.AndroidWebTestBase;
import com.appium.utils.WebOperations;

public class AppiumTestWebApp extends AndroidWebTestBase{
  
  @Test
  public void validLoginTest() {
	  System.out.println("Testing if browser will be launched!!");
	  driver.findElementByXPath("//a[@class='login']").click();
	  driver.findElementById("email").sendKeys("test123@yahoo.com");
	  driver.findElementById("passwd").sendKeys("test123");
	  driver.findElementById("SubmitLogin").click();
	  
	  String currentPage = driver.findElementByXPath("//div[@class='breadcrumb clearfix']/span[@class='navigation_page']").getText();
	  System.out.println("Current Page is: "+currentPage);
	  assertEquals(currentPage, "My account", "Login failed!!");
	  WebElement footer = driver.findElementById("footer");
	  WebOperations.scrollToElement(driver, footer);
	  System.out.println("Scrolled To Footer");
	  WebOperations.scrollToTop(driver);
	  System.out.println("Scrolled To Top of Page");
	  WebOperations.scrollToBottom(driver);
	  System.out.println("Scrolled To Bottom of Page");
  }
  
  @Test
  public void invalidLoginTest() throws InterruptedException {
	  driver.get("http://facebook.com");
	  driver.findElementByName("email").sendKeys("test123@yahoo.com");
	  driver.findElementByName("pass").sendKeys("test123");
	  driver.findElementByName("login").click();
	  Thread.sleep(2000);
	  String errorMessage = driver.findElementById("login_error").getText();	  
	  System.out.println("Error Message is "+errorMessage);
  }
}
