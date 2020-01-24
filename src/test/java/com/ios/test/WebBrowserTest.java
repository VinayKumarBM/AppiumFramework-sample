package com.ios.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.appium.base.test.IOSWebTestBase;
import com.appium.utils.WaitUtil;
import com.appium.utils.WebOperations;
import com.ios.web.modules.HomeModule;
import com.ios.web.modules.LoginModule;
import com.ios.web.modules.MyAccountModule;

public class WebBrowserTest extends IOSWebTestBase {

	private final Logger LOG = LoggerFactory.getLogger(WebBrowserTest.class);
	
	@Test
	public void validLoginTest() throws Exception {
		new HomeModule(driver).navigateToLoginPage();
		LoginModule loginModule = new LoginModule(driver);
		loginModule.loginToAccount("test123@yahoo.com", "test123");
		MyAccountModule accountModule = new MyAccountModule(driver);
		accountModule.verifyPageHeading("My account");
		accountModule.scrolToFooter();
		WaitUtil.pause(2);
		LOG.info("Scrolled To Footer");
		WebOperations.scrollToTop(driver);
		WaitUtil.pause(2);
		LOG.info("Scrolled To Top of Page");
		WebOperations.scrollToBottom(driver);
		WaitUtil.pause(2);
		LOG.info("Scrolled To Bottom of Page");
	}

	@Test
	public void invalidLoginTest() throws InterruptedException {
		new HomeModule(driver).navigateToLoginPage();
		LoginModule loginModule = new LoginModule(driver);
		loginModule.loginToAccount("test123@yahoo.com", "test321");
		loginModule.verifyErrorMessage("Authentication failed.");
	}
}
