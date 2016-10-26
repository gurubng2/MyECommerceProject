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

public class VerifyAutosuggestions extends SuperTest {
	
	@Test(priority=1, enabled=true)
	public void testAutosuggestions()
	{
		//To verify whether the Product name entered in the Search box is generating relevant Auto suggestions or not
		
		HomePage hp = new HomePage(driver);
		
		Assert.assertTrue(hp.isSearchBoxDisplayed(), "Warning! Home page cannot be verified. Aborting the current test..\n");
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" HomePage is correctly displayed", true);
		
		//Here below, the product name is fetched from the Property file: testData/Input/sProductToBeSearched.properties
		String prodName = PropertyFileDataFetcher.getPropertyFileData(productPropertyFile, "productToBeSearched");
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" Product Name: "+prodName, true);
		
		hp.SendProductToSearchBar(prodName);
		
		//Soft assertion is used, just not to break the flow of this method where many assertions are to be mades
		SoftAssert sa = new SoftAssert();
		
		List<WebElement> allSuggestions = hp.getListOfAutoSuggestions();
		
		for(WebElement eachSuggestion : allSuggestions)
		{
			String texty = eachSuggestion.getText();
			sa.assertTrue(texty.toLowerCase().contains(prodName.toLowerCase()), "Product name is not matching in autoSuggestions");
			Reporter.log(TimeStamp.getSimpleTimeStampt()+" Pass: Auto Suggestion for the product name is as per the entered name'"+texty, true);
		}
		
		sa.assertAll();   //Trying to see if all the assertions are passed or not
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" Assertions of Auto-suggestions are PASSED ", true);
				
	}

}
