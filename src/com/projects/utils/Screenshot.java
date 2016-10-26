package com.projects.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class Screenshot {
	
    public static void getScreenshot(WebDriver driver, String reqScreenshotName)
    {
    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	try 
    	{
    		String timeNow = TimeStamp.getTimeStampt();
			FileUtils.copyFile(scrFile, new File("./testData/Output/Screenshot_"+reqScreenshotName+"_"+timeNow+".png"));
			Reporter.log(TimeStamp.getSimpleTimeStampt()+" Screenshot for "+reqScreenshotName+" is captured", true);
		}
    	catch (IOException e) 
    	{
			Reporter.log(TimeStamp.getSimpleTimeStampt()+" IOException seen while saving the screenshot", true);
		}
    }

}
