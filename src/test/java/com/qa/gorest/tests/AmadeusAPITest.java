package com.qa.gorest.tests;

import java.net.Authenticator;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;

import static org.hamcrest.Matchers.equalTo;
public class AmadeusAPITest extends BaseTest {
	
	private String accessToken;
	
	@Parameters({"grantType","clientId","clientSecret"})
	@BeforeMethod
	public void getAccessTokenSteup(String grantType, String clientId, String clientSecret )
	{
		res=new RestClient(prop, baseURI);
		accessToken=res.getAccessTokenOAuth2(AMADEUS_TOKEN_ENDPOINT, grantType, clientId, clientSecret);
		
	}
	
	@Test
	public void getFlightDetails()
	{
		RestClient restClient2=new RestClient(prop, baseURI);
		
		Map<String, String> headersMap=new HashMap<String, String>();
		headersMap.put("Authorization", "Bearer "+accessToken);
		
		Map<String, String> queryMap=new HashMap<String, String>();
		queryMap.put("origin", "PAR");
		queryMap.put("maxPrice", "200");
		
		restClient2.getRequest(AMADEUS_FLIGHTBOOKING_ENDPOINT, headersMap, queryMap, true, false)
		           .then().log().all()
		           .assertThat()
		           .statusCode(APIHttpStatus.OK_200.getCode())
		           .and()
		           .assertThat()
		           .body("data[0].price.total", equalTo("108.68"));
		
	}

}
