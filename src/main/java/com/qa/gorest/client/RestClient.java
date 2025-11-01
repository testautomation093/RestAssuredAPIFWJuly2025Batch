package com.qa.gorest.client;

import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.Properties;

import org.testng.annotations.Test;

import com.qa.gorest.constants.APIHttpStatus;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

//	private static final String BASE_URI = "https://gorest.co.in";
//	private static final String BEARER_TOKEN = "5eacf1500a6f560ce889fce62cb41c691cb38ed175df154db2310d1663f856e1";

	private static RequestSpecBuilder specBuilder;
	private boolean isAuthorizationAdded=false;
	
	private Properties prop;
	private String baseURI;
	
	public RestClient(Properties prop, String baseURI)
	{
		specBuilder = new RequestSpecBuilder();
		this.prop=prop;
		this.baseURI=baseURI;
		
	}

// *****************Some Common Used Methods Utility*************

	public void addAuthorization() {
		if(!isAuthorizationAdded)
		{
			
		specBuilder.addHeader("Authorization", "Bearer "+ prop.getProperty("tokenId"));
		isAuthorizationAdded=true;
	    
		}
	}

	public void setRequestContentType(String contentType) {

		switch (contentType) {
		case "json":
			specBuilder.setContentType(ContentType.JSON);

			break;

		case "xml":
			specBuilder.setContentType(ContentType.XML);

			break;

		case "html":
			specBuilder.setContentType(ContentType.HTML);

			break;

		case "multipart":
			specBuilder.setContentType(ContentType.MULTIPART);

			break;

		default:
			break;
		}

	}

// *********************Request Specifications Methods***************************

	// For Get call
	public RequestSpecification createRequestSpec(boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth)
		{
		addAuthorization();
		}
		return specBuilder.build();

	}

	public RequestSpecification createRequestSpec(Map<String, String> headersMap, boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth)
		{
		addAuthorization();
		}
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		return specBuilder.build();

	}

	public RequestSpecification createRequestSpec(Map<String, String> headersMap, Map<String, String> queryMap, boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth)
		{
		addAuthorization();
		}
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		if (queryMap != null) {
			specBuilder.addQueryParams(queryMap);
		}
		return specBuilder.build();

	}

	// This method is for POST request
	public RequestSpecification createRequestSpec(Object requestBody, String contentType, boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth)
		{
		addAuthorization();
		}
		setRequestContentType(contentType);
		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();

	}
	
	// This method is for POST request with Headers ALSO
	public RequestSpecification createRequestSpec(Object requestBody, String contentType, Map<String, String> headersMap, boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth)
		{
		addAuthorization();
		}		setRequestContentType(contentType);
		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		if(headersMap!=null)
		{
			specBuilder.addHeaders(headersMap);
		}
		
		return specBuilder.build();

	}
	
// ********************HTTP GET Method that has to be called via Test Class*************
	
	public Response getRequest(String serviceUrl, boolean log, boolean includeAuth)
	{
		if(log)
		{
		Response res=RestAssured.given().log().all()
				              .spec(createRequestSpec(includeAuth))
				                         .when().get(serviceUrl);
		
		return res;

		}
		else
		{
			
			Response res=RestAssured.given()
		              .spec(createRequestSpec(includeAuth))
		                         .when().get(serviceUrl);
			return res;

		}
		
	}
	
	public Response getRequest(String serviceUrl,Map<String, String> headersMap, boolean log, boolean includeAuth)
	{
		if(log)
		{
		Response res=RestAssured.given().log().all()
				              .spec(createRequestSpec(headersMap, includeAuth))
				                         .when().get(serviceUrl);
		
		return res;

		}
		else
		{
			
			Response res=RestAssured.given()
		              .spec(createRequestSpec(headersMap, includeAuth))
		                         .when().get(serviceUrl);
			return res;

		}
		
	}
	
	
	public Response getRequest(String serviceUrl,Map<String, String> headersMap,Map<String, String> queryMap, boolean log, boolean includeAuth)
	{
		if(log)
		{
		Response res=RestAssured.given().log().all()
				              .spec(createRequestSpec(headersMap,queryMap, includeAuth))
				                         .when().get(serviceUrl);
		
		return res;

		}
		else
		{
			
			Response res=RestAssured.given()
		              .spec(createRequestSpec(headersMap, queryMap, includeAuth))
		                         .when().get(serviceUrl);
			return res;

		}
		
	}

	
// ********************HTTP POST Method that has to be called via Test Class*************

	public Response postRequest(String serviceUrl, String contentType, Object requestBody, boolean log, boolean includeAuth)
	{
		
		if(log)
		{
			
			Response res=RestAssured.given().log().all()
			           .spec(createRequestSpec(requestBody, contentType, includeAuth))
			           .when()
			           .post(serviceUrl);
			
			return res;
			
		}
		else
		{
         	Response res=RestAssured.given()
				           .spec(createRequestSpec(requestBody, contentType, includeAuth))
				           .when()
				           .post(serviceUrl);
				
				return res;
	
		}

	}
	
	public Response postRequest(String serviceUrl, String contentType, Object requestBody,Map<String, String> headersMap, boolean log, boolean includeAuth)
	{
		
		if(log)
		{
			
			Response res=RestAssured.given().log().all()
			           .spec(createRequestSpec(requestBody, contentType,headersMap, includeAuth))
			           .when()
			           .post(serviceUrl);
			
			return res;
			
		}
		else
		{
         	Response res=RestAssured.given()
				           .spec(createRequestSpec(requestBody, contentType,headersMap, includeAuth))
				           .when()
				           .post(serviceUrl);
				
				return res;
	
		}

	}

// ********************HTTP PUT Method that has to be called via Test Class*************

		public Response putRequest(String serviceUrl, String contentType, Object requestBody, boolean log, boolean includeAuth)
		{
			
			if(log)
			{
				
				Response res=RestAssured.given().log().all()
				           .spec(createRequestSpec(requestBody, contentType, includeAuth))
				           .when()
				           .put(serviceUrl);
				
				return res;
				
			}
			else
			{
	         	Response res=RestAssured.given()
					           .spec(createRequestSpec(requestBody, contentType, includeAuth))
					           .when()
					           .put(serviceUrl);
					
					return res;
		
			}

		}
		
		public Response putRequest(String serviceUrl, String contentType, Object requestBody,Map<String, String> headersMap, boolean log, boolean includeAuth)
		{
			
			if(log)
			{
				
				Response res=RestAssured.given().log().all()
				           .spec(createRequestSpec(requestBody, contentType,headersMap, includeAuth))
				           .when()
				           .put(serviceUrl);
				
				return res;
				
			}
			else
			{
	         	Response res=RestAssured.given()
					           .spec(createRequestSpec(requestBody, contentType,headersMap, includeAuth))
					           .when()
					           .put(serviceUrl);
					
					return res;
		
			}

		}

// ********************HTTP PATCH Method that has to be called via Test Class*************

				public Response patchRequest(String serviceUrl, String contentType, Object requestBody, boolean log, boolean includeAuth)
				{
					
					if(log)
					{
						
						Response res=RestAssured.given().log().all()
						           .spec(createRequestSpec(requestBody, contentType, includeAuth))
						           .when()
						           .patch(serviceUrl);
						
						return res;
						
					}
					else
					{
			         	Response res=RestAssured.given()
							           .spec(createRequestSpec(requestBody, contentType, includeAuth))
							           .when()
							           .patch(serviceUrl);
							
							return res;
				
					}

				}
				
				public Response patchRequest(String serviceUrl, String contentType, Object requestBody,Map<String, String> headersMap, boolean log, boolean includeAuth)
				{
					
					if(log)
					{
						
						Response res=RestAssured.given().log().all()
						           .spec(createRequestSpec(requestBody, contentType,headersMap, includeAuth))
						           .when()
						           .patch(serviceUrl);
						
						return res;
						
					}
					else
					{
			         	Response res=RestAssured.given()
							           .spec(createRequestSpec(requestBody, contentType,headersMap, includeAuth))
							           .when()
							           .patch(serviceUrl);
							
							return res;
				
					}

				}	

// ********************HTTP Delete Method that has to be called via Test Class*************
				
	public Response deleteRequest(String serviceUrl, boolean log, boolean includeAuth)
	{
		if(log)
		{
			
			Response res=RestAssured.given().log().all()
			           .spec(createRequestSpec(includeAuth))
			           .when()
			           .delete(serviceUrl);
			
			return res;
			
		}
		else
		{
         	Response res=RestAssured.given()
				           .spec(createRequestSpec(includeAuth))
				           .when()
				           .delete(serviceUrl);
				
				return res;
	
		}		
		
	}
	
// ****************OAuth 2.0 Token Generation :*******************
	
	@Test
	public String getAccessTokenOAuth2(String serviceUrl, String grantType, String cliendId, String clientSecret)
	{
		RestAssured.baseURI="https://test.api.amadeus.com";
		
		// 1. Post Request for Fetching the Token:
		
		String token=given().log().all()
		.contentType(ContentType.URLENC)
		.formParam("grant_type",grantType)
		.formParam("client_id",cliendId)
		.formParam("client_secret",clientSecret)
		.when()
		.post(serviceUrl)
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.OK_200.getCode())
		.extract().path("access_token");
		
		System.out.println("OAuth 2.0 Token is : "+token);
	
	    return token;

}
}