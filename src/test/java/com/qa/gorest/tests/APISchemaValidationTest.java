package com.qa.gorest.tests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.pojo.UserPOJO;
import com.qa.gorest.utils.StringUtils;

public class APISchemaValidationTest extends BaseTest {
	
	
	@BeforeMethod
	public void getUserSetup()
	{
		res=new RestClient(prop, baseURI);
		
	}
	
	@Test
	public void checkSchemaValidationSingleUserTest()
	{
		
		UserPOJO up=new UserPOJO("Piku",StringUtils.generateEmailId(), "female", "active");
		
		res.postRequest(GOREST_ENDPOINT, "json", up, true, true)
		.then().log().all()
		.assertThat().body(matchesJsonSchemaInClasspath("createUserSchema.json"));
				
	}
	
	

}
