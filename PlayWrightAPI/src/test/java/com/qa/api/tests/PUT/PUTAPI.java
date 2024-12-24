package com.qa.api.tests.PUT;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import com.qa.api.base.BaseTest;
import com.qa.api.data.User;

public class PUTAPI extends BaseTest {

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
		System.out.println(postResp.text());

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode postresponse = objectMapper.readTree(postResp.body());
		System.out.println(postresponse.toPrettyString());

		// capture id from the post response

		String userID = postresponse.get("id").asText();
		System.out.println("userID created is :" + userID);

		// get the response and check

		APIResponse getAPIResponse = requestContext.get("https://gorest.co.in/public/v2/users/" + userID,
				RequestOptions.create().setHeader("Authorization",
						"Bearer e7840d6fb24f862535eb13afe81c6fe786a938fca6470284a87f72e2991e9a75")

		);

		Assert.assertEquals(getAPIResponse.status(), 200);
		Assert.assertEquals(getAPIResponse.statusText(), "OK");

		System.out.println(getAPIResponse.text());
		Assert.assertTrue(getAPIResponse.text().contains(userID));
		//Assert.assertTrue(getAPIResponse.text().contains("swap"));
		Assert.assertTrue(getAPIResponse.text().contains(emailID));

		//put call
		
		//update user object
				 user = new User("pkUpdate",getRandomEmail(),"active","male");
				System.out.println("user data is :" + user.getEmail());
				System.out.println("user data is :" + user.getGender());
		
		APIResponse putResp = requestContext.put("https://gorest.co.in/public/v2/users/"+ userID,
				RequestOptions.create()
				.setHeader("Content-Type", "application/json")
						.setHeader("Authorization",
								"Bearer e7840d6fb24f862535eb13afe81c6fe786a938fca6470284a87f72e2991e9a75")
						.setData(user));
		
		// get the response and check

				APIResponse getAPIResponseAfterPUT = requestContext.get("https://gorest.co.in/public/v2/users/" + userID,
						RequestOptions.create().setHeader("Authorization",
								"Bearer e7840d6fb24f862535eb13afe81c6fe786a938fca6470284a87f72e2991e9a75")

				);

				Assert.assertEquals(getAPIResponseAfterPUT.status(), 200);
				Assert.assertEquals(getAPIResponseAfterPUT.statusText(), "OK");

				System.out.println(getAPIResponseAfterPUT.text());
				Assert.assertTrue(getAPIResponseAfterPUT.text().contains(userID));
				Assert.assertTrue(getAPIResponseAfterPUT.text().contains("pkUpdate"));
				Assert.assertTrue(getAPIResponseAfterPUT.text().contains(emailID));

		
	}
	

}
