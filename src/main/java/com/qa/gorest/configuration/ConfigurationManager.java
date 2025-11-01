package com.qa.gorest.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.qa.gorest.frameworkException.APIFrameworkException;

public class ConfigurationManager {
	
	private Properties prop;
	private FileInputStream fp;
	
// mvn clean install	
	
	public Properties initProp()
	{
		prop=new Properties();
		
		String envName=System.getProperty("env");
		
		System.out.println("Environment Name is : "+envName);
		
		try {
			
			if(envName==null) {
				
				System.out.println("No Env Name is provided Hence running the test case on your default env");
			
				fp=new FileInputStream("src/test/resources/config/config.properties");

			
			}
			else
			{
				
				switch (envName.toLowerCase()) {
				case "uat":
					System.out.println("Started Running the test cases on UAT");
					fp=new FileInputStream("src/test/resources/config/config_uat.properties");

					break;
				case "prod":
					System.out.println("Started Running the test cases on PROD");

					fp=new FileInputStream("src/test/resources/config/config_prod.properties");

					break;
				case "dev":
					System.out.println("Started Running the test cases on DEV");

					fp=new FileInputStream("src/test/resources/config/config_dev.properties");

					break;

				default:
					throw new APIFrameworkException("INVALID ENVIRONMENT NAME");
				}
				
				
			}
	
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			prop.load(fp);		
		
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return prop;
		
	}
	

}
