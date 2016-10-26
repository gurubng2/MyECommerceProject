package com.projects.utils;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class CatchCustomeMessagePrint {
	
	public static void reportMessage(WebDriver driver, int attemptName, String stringTestName)
	{
		if(attemptName<3)
		{
			Reporter.log(TimeStamp.getSimpleTimeStampt()+" Exception seen while performing "+stringTestName+". Trying again", true);
		}
		else
		{
			Screenshot.getScreenshot(driver, stringTestName);
			Assert.fail(TimeStamp.getSimpleTimeStampt()+" Unable to find the Element while validating "+stringTestName);
		}
	}

}
