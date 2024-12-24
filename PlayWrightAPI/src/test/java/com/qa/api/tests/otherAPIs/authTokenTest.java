package com.qa.api.tests.otherAPIs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import com.qa.api.base.BaseTest;

public class authTokenTest extends BaseTest {

	

	@Test
	public void getTokenTest() throws IOException {

		
		String requestTokenJSONBody = "{\"" + "username\":\"admin\"," + "\"password\":\"password123\"}";

		System.out.println("Request json String is :" + requestTokenJSONBody);

		//post call : create a token
		APIResponse postTokenResp = requestContext.post("https://restful-booker.herokuapp.com/auth",
				RequestOptions.create()
				.setHeader("Content-Type", "application/json")
						.setData(requestTokenJSONBody));

		System.out.println("status is :" + postTokenResp.status());
		Assert.assertEquals(postTokenResp.status(), 200);
		Assert.assertEquals(postTokenResp.statusText(), "OK");
		System.out.println(postTokenResp.text());

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode postresponse = objectMapper.readTree(postTokenResp.body());
		System.out.println(postresponse.toPrettyString());

		// capture token from the post response

		String tokenID = postresponse.get("token").asText();
		System.out.println("tokenID  is :" + tokenID);

		Assert.assertNotNull(tokenID);
	}

}
