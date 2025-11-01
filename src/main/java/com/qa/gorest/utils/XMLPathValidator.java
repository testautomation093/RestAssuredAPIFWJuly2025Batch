package com.qa.gorest.utils;

import java.util.List;

import com.jayway.jsonpath.JsonPath;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class XMLPathValidator {
	
	
	private String getXMLResponseAsString(Response response)
	{
		String res=response.getBody().asString();

		return res;
	}
	
	public <T> T read(Response res, String path)
	{
		String response=getXMLResponseAsString(res);
		XmlPath xml=new XmlPath(response);
		return xml.get(path);
		
	}
	
	public <T> List<T> readList(Response res, String path)
	{
		String response=getXMLResponseAsString(res);
		XmlPath xml=new XmlPath(response);
		return xml.getList(path);
		
	}

}
