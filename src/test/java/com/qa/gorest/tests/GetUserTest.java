package com.qa.gorest.tests;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.utils.XMLPathValidator;

import io.qameta.allure.Description;
import io.restassured.response.Response;

public class GetUserTest extends BaseTest {
	
	@BeforeMethod
	public void getUserSetup()
	{
		res=new RestClient(prop, baseURI);
		
	}
	
	
	@Test(priority=3)
	public void getAllUserTest()
	{
		res.getRequest(GOREST_ENDPOINT, true,true)
		.then().log().all()
		.assertThat().statusCode(APIHttpStatus.OK_200.getCode());

	}
	
	@Test(priority=2, enabled=true)
	public void getSingleUserTest()
	{
		res.getRequest(GOREST_ENDPOINT+8193370, true, true)
		.then().log().all()
		.assertThat().statusCode(APIHttpStatus.OK_200.getCode())
		.and()
		.body("id", equalTo(8193370));

	}
	
	@Test(priority=1, description=" This test method is about getting user info with query params")
	public void getUser_With_Query_Params_Test()
	{
		Map<String,String> queryMap=new HashMap<String, String>();
		queryMap.put("gender","male");
		queryMap.put("status", "active");
		
		res.getRequest(GOREST_ENDPOINT, null, queryMap, true, true)
		.then().log().all()
		.assertThat().statusCode(APIHttpStatus.OK_200.getCode());

	}
	
	@Test(priority=4)
	public void getAllUserTest_With_XMLs()
	{
		Response response=res.getRequest(GOREST_ENDPOINT_XML, true,true);
		
		response.prettyPrint();
		
	    Assert.assertEquals(response.statusCode(), APIHttpStatus.OK_200.getCode());
		
		XMLPathValidator xml=new XMLPathValidator();
		List<String> listNames=xml.readList(response, "objects.object.name");
		
		System.out.println("List of Names are : "+listNames);
		
		Assert.assertTrue(listNames.contains("Piku"));

	}
	
}
