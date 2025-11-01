package com.qa.gorest.tests;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIContants;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.pojo.UserPOJO;
import com.qa.gorest.utils.ExcelUtil;
import com.qa.gorest.utils.StringUtils;


public class CreateUserTest extends BaseTest {
	
	@BeforeMethod
	public void getUserSetup()
	{
		res=new RestClient(prop, baseURI);
		
	}
	
//	@DataProvider
//	public Object[][] getUserTestData()
//	{
//		Object obj[][]= {{"Mohan","male","active"},{"Joey","male","inactive"},{"Misha","female","active"}};
//		
//		return obj;
//	}
	
	
	@DataProvider
	public Object[][] getUserTestData_From_Excel()
	{
      Object obj[][]=ExcelUtil.getTestDataFromSheet(APIContants.TEST_SHEET_NAME);
		
		return obj;
	}
	
	@Test( dataProvider = "getUserTestData_From_Excel")
	public void createUserTest(String name, String gender, String status)
	{
	// 1. Create user Request:	
		UserPOJO up=new UserPOJO(name, StringUtils.generateEmailId(),gender, status);
		
		Integer userId=res.postRequest(GOREST_ENDPOINT,"json", up, true,true)
		.then().log().all()
		.assertThat().statusCode(APIHttpStatus.CREATED_201.getCode())
		.extract().path("id");
		
	System.out.println("User id is : "+userId);	
	
	// 2. Get the user request:
	
	RestClient resGet=new RestClient(prop, baseURI);
	resGet.getRequest(GOREST_ENDPOINT+userId, true, true)
	.then().log().all()
	.assertThat().statusCode(APIHttpStatus.OK_200.getCode())
	.and().assertThat().body("id", equalTo(userId));
		
		
	}
	

}
