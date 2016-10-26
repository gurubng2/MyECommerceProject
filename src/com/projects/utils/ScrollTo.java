package com.projects.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class ScrollTo {
	
	public static void scrollToElement(WebDriver driver, WebElement elem)
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elem);
	}
	
	public static void scrollToAndClick(WebDriver driver, WebElement elementToClick)
	{
		Reporter.log("Scrolling & trying to click", true);
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+elementToClick.getLocation().y+")");
	}

}
