package com.projects.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Reporter;

public class PropertyFileDataFetcher {
	
	public static String getPropertyFileData(String fileName, String propertyKey)
	{
		//Method to be used to fetch the values from the properties file which is written using key/value combination
		
		String propertyValue = "";
		
		try 
		{
			FileInputStream fis1 = new FileInputStream(fileName);
			Properties prop = new Properties();
			try 
			{
				prop.load(fis1);
				propertyValue = prop.getProperty(propertyKey);
				
			} 
			catch (IOException e) 
			{
				Reporter.log(TimeStamp.getSimpleTimeStampt()+" IO Exception found while loading the Properties file \n"+e.getMessage());
			}
			
		} 
		catch (FileNotFoundException e) 
		{
			Reporter.log(TimeStamp.getSimpleTimeStampt()+" Properties file not found \n"+e.getMessage());
		}
		
		return propertyValue;
	}
}
