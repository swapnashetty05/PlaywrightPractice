package com.qa.api.tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;

public class APIDisposeTest {

	Playwright pw;
	APIRequest req;
	APIRequestContext reqCon;

	@BeforeTest
	public void setup() {

		pw = Playwright.create();
		req = pw.request();
		reqCon = req.newContext();
	}

	@Test
	public void disposeResponseTest() {
		//Request 1
		APIResponse apiResp = reqCon.get("https://gorest.co.in/public/v2/users");

		int statusCode = apiResp.status();
		String statusText = apiResp.statusText();
		System.out.println("status code is : " + statusCode + "statusText :" + statusText);
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(apiResp.ok(), true);

		System.out.println("print api response with plaintest -------" + apiResp.text());
//Dispose method will only remove the response body
		apiResp.dispose();
		
		//System.out.println("print api response after dispose -------" + apiResp.text());
		System.out.println("status code is after dispose: " + statusCode  );
	
		
		//request 2
		APIResponse apiResp1 = reqCon.get("https://reqres.in/api/users/2");

		System.out.println("resp on req 2: " + apiResp1.text() + "status code is:" +apiResp1.status());
		
		//request context dispose
		reqCon.dispose();
		
	System.out.println("response body 1 :" +apiResp.text());
	System.out.println("response body 2 :" +apiResp1.text());
	}

	@AfterTest
	public void tearDown() {
		pw.close();
	}

}
