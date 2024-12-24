package com.qa.api.tests.GET;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

public class GETAPICall {

	

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
	public void getSpecificUSerTest() {

		APIResponse apiResp = reqCon.get("https://gorest.co.in/public/v2/users",

				RequestOptions.create()
				.setQueryParam("id", 7020264)
				.setQueryParam("status", "active"));
		int statusCode = apiResp.status();
		String statusText = apiResp.statusText();
		System.out.println("status code is : " + statusCode + "statusText :" + statusText);
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(apiResp.ok(), true);

		System.out.println("print api response with plaintest -------" + apiResp.text());

		

	}

	@Test
	public void getUSersAPITest() throws IOException {

		APIResponse apiResp = reqCon.get("https://gorest.co.in/public/v2/users");

		int statusCode = apiResp.status();
		String statusText = apiResp.statusText();
		System.out.println("status code is : " + statusCode + "statusText :" + statusText);
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(apiResp.ok(), true);

		System.out.println("print api response with plaintest -------" + apiResp.text());

		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode jsonResponse = objectMapper.readTree(apiResp.body());
		String jsonPretty = jsonResponse.toPrettyString();

		System.out.println(jsonPretty);

		System.out.println(" print api url " + apiResp.url());
		Map<String, String> headers = apiResp.headers();

		System.out.println("HEADERS ------" + headers);
		Assert.assertEquals(headers.get("content-type"), "application/json; charset=utf-8");

	}
	
	@AfterTest
	public void tearDown() {
		pw.close();
	}

}
