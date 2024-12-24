package com.qa.opencart.base;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlayWrightFactory;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;

public class BaseTest {
	
	PlayWrightFactory pf;
	Page page;
	protected Properties prop;
	
	protected HomePage homePage;
	protected LoginPage loginPage;
	
	@BeforeTest
	public void setUp() {
		pf = new PlayWrightFactory();
		prop = pf.init_prop();
		page = pf.initBrowser(prop);
		homePage = new HomePage(page);
	}
	
	@AfterTest
	public void tearDown() {
		page.context().browser().close();
	}

}
