package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void loginPageNAvigationTest() {
		loginPage = homePage.navigateToLoginPage();
		String actualLoginTitle = loginPage.getLoginPageTitle();
		System.out.println("actual login page title: " + actualLoginTitle);
		Assert.assertEquals(actualLoginTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void forgotPAsswordLinkExists() {

		Assert.assertTrue(loginPage.isForgotPAsswordLinkPresent());
	}

	@Test(priority = 3)
	public void appLoginText() {

		Assert.assertTrue(loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim()));
	}

}
