package com.qa.api.tests.POST;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import com.qa.api.base.BaseTest;
import com.qa.api.data.User;

public class CreateUserPOSTPOJOTest extends BaseTest {

	static String emailID;

	

	public static String getRandomEmail() {
		// Random random = new Random();

		emailID = "testme" + System.currentTimeMillis() + "@gmail.com";
		System.out.println("emailID is :" + emailID);
		return emailID;
	}

	@Test
	public void createUserTest() throws IOException {
		
		

		//create user object
		User user = new User("pk",getRandomEmail(),"active","male");
		System.out.println("user data is :" + user.getEmail());
		System.out.println("user data is :" + user.getGender());
		
		APIResponse postResp = requestContext.post("https://gorest.co.in/public/v2/users",
				RequestOptions.create()
				.setHeader("Content-Type", "application/json")
						.setHeader("Authorization",
								"Bearer e7840d6fb24f862535eb13afe81c6fe786a938fca6470284a87f72e2991e9a75")
						.setData(user));

		System.out.println("status is :" + postResp.status());
		Assert.assertEquals(postResp.status(), 201);
		Assert.assertEquals(postResp.statusText(), "Created");
		String postResponseText = postResp.text();
		System.out.println(postResponseText);
		
		//convert the response to POJO - deserialization
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		User actualUser = objectMapper.readValue(postResponseText, User.class);
		
		
		Assert.assertEquals(actualUser.getName(), user.getName());
		Assert.assertEquals(actualUser.getEmail(), user.getEmail());
		Assert.assertEquals(actualUser.getGender(), user.getGender());
		Assert.assertEquals(actualUser.getStatus(), user.getStatus());
		Assert.assertNotNull(actualUser.getId());
		
		
	}

}
