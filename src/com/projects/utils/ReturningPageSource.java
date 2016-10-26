package com.projects.utils;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.projects.scripts.SuperTest;

public class ReturningPageSource extends SuperTest {
	
	public static boolean searchStringInPageSource(WebDriver driver, String text2search)
	{
		//This method collects the page source, then searches for a specified string. Returns the booleans result.
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" Initiating the searching of the String '"+text2search+"' in the page source", true );
		return driver.getPageSource().contains(text2search);
		
		//[Guru]Use the property file parser & feed the String "lastPageRightArrow" which indicates the last page of the search results

	}

}
