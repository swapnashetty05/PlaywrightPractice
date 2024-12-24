package com.qa.api.base;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;

public class BaseTest {
	
	protected Playwright playwright;
	protected APIRequest request;
	protected APIRequestContext requestContext;

	@BeforeTest
	public void setup() {

		playwright = Playwright.create();
		request = playwright.request();
		requestContext = request.newContext();
	}
	
	@AfterTest
	public void tearDown() {
		playwright.close();
	}


}
