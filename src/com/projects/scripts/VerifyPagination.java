package com.projects.scripts;

import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.projects.pom.HomePage;
import com.projects.utils.CollectProductName;
import com.projects.utils.PropertyFileDataFetcher;
import com.projects.utils.ReturningPageSource;
import com.projects.utils.Screenshot;
import com.projects.utils.ScrollTo;
import com.projects.utils.TimeStamp;

public class VerifyPagination extends SuperTest {
	
	@Test(priority=1, enabled=true)
	public void testPagination()
	{
		//Testing the Pagination of the search results
		
		HomePage hp = new HomePage(driver);
		
		Assert.assertTrue(hp.isSearchBoxDisplayed(), "Warning! Home page cannot be verified. Aborting the current test..\n");
		String prodName = CollectProductName.getProductNameToSearch();
		hp.SendProductToSearchBar(prodName);
		hp.clickSearchButton();
		
		String fpi = PropertyFileDataFetcher.getPropertyFileData(propertyFile, "first_page_indicator");
		SoftAssert sas = new SoftAssert();

		boolean found = ReturningPageSource.searchStringInPageSource(driver, fpi);

		if(found==false)
		{
			Reporter.log(TimeStamp.getSimpleTimeStampt()+" Cannot perform Pagination, as only 1 page is present for the current product", true);
		}
		else
		{
			int lastPageNbr = hp.getLastPageNumber();
			Reporter.log(TimeStamp.getSimpleTimeStampt()+" Last page number is : "+lastPageNbr, true);
			Reporter.log(TimeStamp.getSimpleTimeStampt()+" Starting the Pagination...", true);
			WebDriverWait wait = new WebDriverWait(driver, 10);

			
			for(int i=1; i<lastPageNbr; i++)
			{		
				int attempt=1;
				while(attempt<3)
				{
					try
					{
						wait.until(ExpectedConditions.elementToBeClickable(hp.FirstProductListed()));
						//Reporter.log(TimeStamp.getSimpleTimeStampt()+" First product of the page is identified. Success at the attempt #"+attempt, true);
						break;
					}
					catch(StaleElementReferenceException e)
					{
						
						Reporter.log(TimeStamp.getSimpleTimeStampt()+" Exception seen. Continuing with the flow", true);
					}
					attempt++;
				}
				
				int pageNumAttempt = 1;
				while(pageNumAttempt<3)
				{
					try
					{
						wait.until(ExpectedConditions.elementToBeClickable(hp.CurrentPageNumber()));
						//Reporter.log(TimeStamp.getSimpleTimeStampt()+" Current page number is identified. Success at the attempt #"+pageNumAttempt, true);
						break;	
					}
					catch(StaleElementReferenceException e)
					{
						Screenshot.getScreenshot(driver, "Pagination");
						Reporter.log(TimeStamp.getSimpleTimeStampt()+" Exception seen. Continuing with the flow", true);
					}
					pageNumAttempt++;
				}
				
				try
				{					
					Reporter.log(TimeStamp.getSimpleTimeStampt()+" Current page number is : "+hp.getCurrentPageNumber(), true);
				}
				catch (Exception e)
				{
					Screenshot.getScreenshot(driver, "Pagination");
					Reporter.log(TimeStamp.getSimpleTimeStampt()+" Exception found while trying to convert the current page number\n"+e.toString(), true);
				}

				//Getting all the product title WebElements in a List
				List<WebElement> allProds = hp.getListOfProductsOnThePage();
				int sizeOfAllProds = allProds.size();
				
				for(int k=1; k<sizeOfAllProds; k += 3)
				{
					ScrollTo.scrollToElement(driver, allProds.get(k));
				}
				
				int arrowAttempt = 1;
				while(arrowAttempt<3)
				{
					try
					{
						wait.until(ExpectedConditions.elementToBeClickable(hp.NextPageArrow()));
						//Reporter.log(TimeStamp.getSimpleTimeStampt()+" NextPageArrow Link is identified. Success at the attempt #"+arrowAttempt, true);
						break;
					}
					catch(StaleElementReferenceException e)
					{
						Screenshot.getScreenshot(driver, "Pagination");
						Reporter.log(TimeStamp.getSimpleTimeStampt()+" Exception seen. Continuing with the flow"+e.getMessage(), true);
					}
					arrowAttempt++;
				}
				
				sas.assertTrue(hp.NextPageArrow().isDisplayed(), "Assertion of Current page is failed");
				Reporter.log(TimeStamp.getSimpleTimeStampt()+" PASS: Assertion of this page is successful");
				
				hp.NextPageArrow().click();
				
				if (i==(lastPageNbr-1))
				{
					Reporter.log(TimeStamp.getSimpleTimeStampt()+" Last page is found. Stopping the Pagination", true);
					sas.assertAll();
					Reporter.log(TimeStamp.getSimpleTimeStampt()+"PASS: Assertion of Pagination is successful", true);
				}
				else
				{
					Reporter.log(TimeStamp.getSimpleTimeStampt()+" Navigating to next page..", true);
				}
			}
		}
	}
}
