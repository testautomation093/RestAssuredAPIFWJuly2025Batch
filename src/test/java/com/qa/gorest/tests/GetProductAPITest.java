package com.qa.gorest.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.utils.JsonPathValidator;

import io.restassured.response.Response;

public class GetProductAPITest extends BaseTest {
	

	@BeforeMethod
	public void getUserSetup()
	{
		res=new RestClient(prop, baseURI);
		
	}
	
	@Test
	public void getAllProductInfo()
	{
		
		Response response=res.getRequest(FAKESTORE_PRODUCT_ENDPOINT, true, false);
		
		Assert.assertEquals(response.statusCode(), APIHttpStatus.OK_200.getCode());
		
		JsonPathValidator js=new JsonPathValidator();
		
		List<String> titleList=js.readList(response, "$..[?(@.price>100)].[\"title\"]");
		
		System.out.println("List of Titles are: "+titleList);
		
		Assert.assertTrue(titleList.contains("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"));
		
	}
	
}
