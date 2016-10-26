package com.projects.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailPage extends BasePage {
	
	//POM class for the Product Detail Page
	
	@FindBy(id="productTitle")
	private WebElement productTitle;
	
	public ProductDetailPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public String getProductTitle()
	{
		//Gets the product title displayed in the product detail page & returns as String
		return productTitle.getText();
	}

}
