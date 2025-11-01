package com.qa.gorest.base;

import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.configuration.ConfigurationManager;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {
	
// Service Endpoints :
	
	public final static String GOREST_ENDPOINT="/public/v2/users/";
	public final static String GOREST_ENDPOINT_XML="/public/v2/users.xml";
	public final static String REQRES_ENDPOINT="/api/users/";
	public final static String AMADEUS_TOKEN_ENDPOINT="v1/security/oauth2/token";
	public final static String AMADEUS_FLIGHTBOOKING_ENDPOINT="v1/shopping/flight-destinations";
	public final static String FAKESTORE_PRODUCT_ENDPOINT="/products";
	
	public Properties prop;
	public ConfigurationManager cm;
	public RestClient res;
	public String baseURI;
	
	@Parameters({"baseURI"})
	@BeforeTest
	public void setUp(String baseURI)
	{
		RestAssured.filters(new AllureRestAssured());
		
		cm=new ConfigurationManager();
		prop=cm.initProp();
		this.baseURI=baseURI;
		
		//String baseURI=prop.getProperty("baseUri");
		
		//res=new RestClient(prop, baseURI);
		
		
	}
	

}
