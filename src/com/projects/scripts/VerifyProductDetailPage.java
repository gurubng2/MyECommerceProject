package com.projects.scripts;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.projects.pom.HomePage;
import com.projects.pom.ProductDetailPage;
import com.projects.utils.CatchCustomeMessagePrint;
import com.projects.utils.CollectProductName;
import com.projects.utils.Screenshot;
import com.projects.utils.TimeStamp;

public class VerifyProductDetailPage extends SuperTest {
	
	@Test(priority=1)
	public void testProductDetailedPageName()
	{
		//Testing the product detail page
		
		HomePage hp = new HomePage(driver);
		
		Assert.assertTrue(hp.isSearchBoxDisplayed(), "Warning! Home page cannot be verified. Aborting the current test..\n");
		String prodName = CollectProductName.getProductNameToSearch();
		hp.SendProductToSearchBar(prodName);
		hp.clickSearchButton();
		
		//Getting on the first product listed
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		int attemptNb=1;
		while(attemptNb<3)
		{
			try
			{
				wait.until(ExpectedConditions.elementToBeClickable(hp.FirstProductListed()));
				break;
			}
			catch(StaleElementReferenceException e)
			{
				CatchCustomeMessagePrint.reportMessage(driver, attemptNb, "FindingFirstProduct");
			}
			attemptNb++;
		}
		
		String beforeText = hp.getTextFirstProductListed();
		
		//Clicking on the first product listed
		hp.FirstProductListed().click();
		
		//Switching to the newly launched window
		hp.switchToNewWindow();
		Reporter.log(TimeStamp.getSimpleTimeStampt()+"  Switched to the newly launched window", true);
		
		
		ProductDetailPage pdp = new ProductDetailPage(driver);
		String afterText = pdp.getProductTitle();
		
		try
		{
			//Asserting the previous product name & the current product name
			Assert.assertEquals(beforeText, afterText, "Failed, Product name is not matching/n");
			Reporter.log(TimeStamp.getSimpleTimeStampt()+"  Assertion success, titles are matching", true);
		}
		catch(Exception e)
		{
			Screenshot.getScreenshot(driver, "testProductDetailedPageName");
			Assert.fail("Failed, Product name is not matching/n");
		}
		
	}

}
