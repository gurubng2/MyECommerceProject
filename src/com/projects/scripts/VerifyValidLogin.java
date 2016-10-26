package com.projects.scripts;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.projects.pom.BasePage;
import com.projects.pom.SignInPage;
import com.projects.utils.Excel;
import com.projects.utils.PropertyFileDataFetcher;
import com.projects.utils.TimeStamp;

public class VerifyValidLogin extends SuperTest {
	
	@Test(priority=1, enabled=true, groups={"Login"})
	public void testValidLogin()
	{
		BasePage bsp = new BasePage(driver);
		
		Assert.assertTrue(bsp.isSearchBoxDisplayed(), "Warning! Home page cannot be verified. Aborting the current test..\n");
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" HomePage is correctly displayed", true);
		
		String LinkText = bsp.get_YourAmazon_LinkText();
		
		bsp.clickHelloSignInLink();
		
		SignInPage sip = new SignInPage(driver);
		Assert.assertTrue(sip.IsThisSignInPage(), "Warning! Sign In page cannot be verified. Aborting the current test..\n");
		
		String sheetname_Valid = PropertyFileDataFetcher.getPropertyFileData(propertyFile, "sheetName_validLogin");
		String USN = Excel.getCellValue(excelPath, sheetname_Valid, 1, 0);
		String PSW = Excel.getCellValue(excelPath, sheetname_Valid, 1, 1);
		
		sip.sendUsername(USN);
		sip.sendPassword(PSW);
		sip.Login();
		
		String LinkText2 = bsp.get_YourAmazon_LinkText();
		Assert.assertTrue(LinkText != LinkText2, TimeStamp.getSimpleTimeStampt()+"Assertion is failed for Successful login");
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" Sign in is successful", true);
	}
}
