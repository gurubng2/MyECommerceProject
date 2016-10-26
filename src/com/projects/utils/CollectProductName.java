package com.projects.utils;

import org.testng.Reporter;

import com.projects.scripts.Constants;

public class CollectProductName implements Constants {
	
	public static String getProductNameToSearch()
	{
		String prodName = PropertyFileDataFetcher.getPropertyFileData(productPropertyFile, "productToBeSearched");
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" Product Name: "+prodName, true);
		return prodName;
	}
	


}
