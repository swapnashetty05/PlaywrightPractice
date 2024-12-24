package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {

	private Page page;

	// 1. string locators - object repository

	private String search = "input[name='search']";

	private String searchIcon = "div#search button";

	private String searchPageHEader = "div#content h1";

	private String loginLink = "a:text('Login')";

	private String myAccountLink = "a[title='My Account']";
	
	private String newCustomerContinue = "//a[normalize-space()='Continue']";
	
	// 2. Page constructor

	public HomePage(Page page) {

		this.page = page;
	}

	// 3. page action/methods

	public String getHomePAgeTitle() {
		String title = page.title();
		System.out.println("Page title is : " + title);
		return title;
	}

	public String getHomePageURL() {
		String url = page.url();
		System.out.println("url is :" + url);
		return url;

	}

	public String doSearch(String productName) {
		page.fill(search, productName);
		page.click(searchIcon);
		String header = page.textContent(searchPageHEader);
		// page.locator(searcjPageHEader).waitFor();
		System.out.println("header is :" + header);
		return header;
	}
	
	public LoginPage navigateToLoginPage() {
		page.click(myAccountLink);
		page.click(loginLink);
		
		return new LoginPage(page);
	}
	
	public NewCustomerPage navigateToNewCustomerPage() {
		page.click(newCustomerContinue);
		
		return new NewCustomerPage(page);
	}

}
