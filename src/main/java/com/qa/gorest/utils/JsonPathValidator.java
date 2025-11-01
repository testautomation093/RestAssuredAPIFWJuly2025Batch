package com.qa.gorest.utils;

import java.util.List;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class JsonPathValidator {
	
	
	private String getJsonResponseAsString(Response response)
	{
		String res=response.getBody().asString();

		return res;
	}
	
	public <T> T read(Response res, String path)
	{
		String response=getJsonResponseAsString(res);
		return JsonPath.read(response,path);
		
	}
	
	public <T> List<T> readList(Response res, String path)
	{
		String response=getJsonResponseAsString(res);
		
		return JsonPath.read(response,path);
		
	}

}
