package com.projects.scripts;

import com.projects.utils.PropertyFileDataFetcher;

public interface Constants {
	
	String propertyFile = "./testData/Input/Constants.properties";
	String productPropertyFile = "./testData/Input/ProductToBeSearched.properties";
	String excelPath = PropertyFileDataFetcher.getPropertyFileData(propertyFile, "excelPath");
	

}
