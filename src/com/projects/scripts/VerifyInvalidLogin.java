package com.projects.scripts;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.projects.pom.BasePage;
import com.projects.pom.SignInPage;
import com.projects.utils.Excel;
import com.projects.utils.PropertyFileDataFetcher;
import com.projects.utils.TimeStamp;

public class VerifyInvalidLogin extends SuperTest {

	@Test(priority=1, enabled=true, groups={"Login"})
	public void testInvalidLogin()
	{	
		BasePage bsp = new BasePage(driver);
		
		Assert.assertTrue(bsp.isSearchBoxDisplayed(), "Warning! Home page cannot be verified. Aborting the current test..\n");
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" HomePage is correctly displayed", true);
		
		bsp.clickHelloSignInLink();
		
		SignInPage sip = new SignInPage(driver);
		Assert.assertTrue(sip.IsThisSignInPage(), "Warning! Sign In page cannot be verified. Aborting the current test..\n");

		String sheetname_invalid = PropertyFileDataFetcher.getPropertyFileData(propertyFile, "sheetName_invalidLogin");
		int rCount = Excel.getRowCount(excelPath, sheetname_invalid);
		
		for(int i=1; i<=rCount; i++)
		{
			String USN = Excel.getCellValue(excelPath, sheetname_invalid, i, 0);
			String PSW = Excel.getCellValue(excelPath, sheetname_invalid, i, 1);
			
			sip.sendUsername(USN);
			sip.sendPassword(PSW);
			sip.Login();
			
			Assert.assertTrue(sip.CheckAlertMessage(), TimeStamp.getSimpleTimeStampt()+"Warning! Invalid login warning text is not displayed\n");
			Reporter.log(TimeStamp.getSimpleTimeStampt()+" Invalid text on the sign-in page is verified", true);
			
			bsp.goBack();
		}
				
	}
	
}
