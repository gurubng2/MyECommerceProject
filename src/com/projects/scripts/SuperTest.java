package com.projects.scripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.xml.XmlTest;

import com.projects.utils.PropertyFileDataFetcher;
import com.projects.utils.TimeStamp;

public class SuperTest implements Constants {
	
	WebDriver driver;
	
	@BeforeClass
	public void LaunchBrowser(XmlTest xmlTest)
	{		
		String browserName = xmlTest.getParameter("browser");
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" Selected Browser's Name: "+browserName, true);
		
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./browserDrivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" Navigating to homePage URL", true);
		String pageURL = PropertyFileDataFetcher.getPropertyFileData("./testData/Input/Constants.properties", "mainUrl");
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" Navigating to the URL --> "+pageURL, true);
		
		driver.get(pageURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" URL is loaded", true);
	}
	
	@AfterClass
	public void CloseBrowser()
	{
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" Closing the browser", true);
		driver.quit();
	}

}
