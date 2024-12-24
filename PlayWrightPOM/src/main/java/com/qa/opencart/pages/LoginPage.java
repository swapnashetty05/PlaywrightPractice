package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

	private Page page;

	private String emailID = "#input-email";

	private String password = "#input-password";

	private String loginBtn = "input[value='Login']";
	
	//private String forgotPassword = "div[class='form-group'] a";

	private String forgotPassword = "div[class='form-group'] a1";

	private String logoutLink = "//a[@class='list-group-item'][normalize-space()='Logout']";

	
	
	public LoginPage(Page page) {
		
		this.page = page;
	}
	
	public String getLoginPageTitle() {
		return page.title();
		
	}

	public boolean isForgotPAsswordLinkPresent() {
		return page.isVisible(forgotPassword);
	}

	public boolean doLogin(String userName, String appPassword) {
		
		System.out.println("cred :"+userName + ": " + appPassword);
	page.fill(emailID, userName);
	page.fill(password, appPassword);
	
	page.click(loginBtn);
	
	page.locator(logoutLink).waitFor();
	if(page.locator(logoutLink).isVisible()) {
		System.out.println("user is logged in successfully....");
		return true;
	}else {
		System.out.println("user is not logged in successfully....");
		return false;
	}}
}
