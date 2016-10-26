package com.projects.scripts;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.projects.pom.HomePage;
import com.projects.utils.PropertyFileDataFetcher;
import com.projects.utils.TimeStamp;

public class VerifyProductListings extends SuperTest {
	
	@Test(priority=1)
	public void testProductListings()
	{
		//To verify whether appropriate products are displayed for the search performed
		
		HomePage hp = new HomePage(driver);
		
		Assert.assertTrue(hp.isSearchBoxDisplayed(), "Warning! Home page cannot be verified. Aborting the current test..\n");
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" HomePage is correctly displayed", true);
		
		//Here below, the product name is fetched from the Property file: testData/Input/sProductToBeSearched.properties
		String prodName = PropertyFileDataFetcher.getPropertyFileData(productPropertyFile, "productToBeSearched");
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" Product Name: "+prodName, true);
		
		//Enter the product name to the search box
		hp.SendProductToSearchBar(prodName);
		
		//Click on the search button 
		hp.clickSearchButton();
		
		//Gets all the products listed
		List<WebElement> allListings = hp.getListOfProductsOnThePage();
		
		SoftAssert sa = new SoftAssert();
		
		for(WebElement eachProduct : allListings)
		{
			String name = eachProduct.getText();
			sa.assertTrue(name.toLowerCase().contains(prodName.toLowerCase()), "Product name is not matching with the product");
			Reporter.log(TimeStamp.getSimpleTimeStampt()+" Products listed : "+name, true);
		}
		
		sa.assertAll();   //Trying to see if all the assertions are passed or not
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" Assertions of product listings are valid ", true);
		
	}

}
