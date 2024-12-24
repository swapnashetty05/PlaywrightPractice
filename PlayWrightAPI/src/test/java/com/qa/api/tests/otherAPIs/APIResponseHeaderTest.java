package com.qa.api.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.HttpHeader;
import com.qa.api.base.BaseTest;

public class APIResponseHeaderTest extends BaseTest {

	@Test
	public void APIResponseHeader() {
		APIResponse apiResp = requestContext.get("https://gorest.co.in/public/v2/users");

		int statusCode = apiResp.status();
		String statusText = apiResp.statusText();
		System.out.println("status code is : " + statusCode + "statusText :" + statusText);
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(apiResp.ok(), true);

		System.out.println("print api response with plaintest -------" + apiResp.text());
		
		//using map : this is easy for validation.. as we get key and value pairs for validation
		Map<String, String> headerMap = apiResp.headers();

		headerMap.forEach((k, v) -> System.out.println(k + ":" + v));
	
		Assert.assertEquals(headerMap.get("server"), "cloudflare");
	System.out.println("header count is :" +headerMap.size());

	//using list
	
	List<HttpHeader> headerList = apiResp.headersArray();
	
	for(HttpHeader e : headerList) {
		System.out.println("name is :" +e.name + "value :"+e.value);
	}
	
	}

}
