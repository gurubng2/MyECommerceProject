package com.projects.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.projects.utils.TimeStamp;

public class HomePage extends BasePage {

	//WebElements using Xpath, to find all the Auto-suggestions
	@FindAll({@FindBy(xpath="//div[@class='s-suggestion']/span[@class='s-heavy']")})
	private List<WebElement> AutoSuggestions;
	
	@FindBy(xpath="//div[@class='nav-right']//input[@class='nav-input']")
	private WebElement SearchButton;
	
	//Xpath to find all the product names (searched products)
	@FindAll({@FindBy(xpath="//div[@class='a-row a-spacing-small']/a/h2")})
	private List<WebElement> SearchedProducts;
	
	@FindBy(xpath="//span[@class='pagnDisabled']")
	private WebElement lastPageNumber;
	
	@FindBy(id="pagnNextString")
	private WebElement NextPageButton;
	
	@FindBy(xpath="//span[@class='srSprite pagnNextArrow']")
	private WebElement NextPage;
	
	@FindBy(xpath="//span[@class='pagnCur']")
	private WebElement CurrentPageNumber;
	
	@FindBy(xpath="(//div[@class='a-row a-spacing-small']/a/h2)[1]")
	private WebElement firstProductListed;
	
	public HomePage(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public List<WebElement> getListOfAutoSuggestions()
	{
		//Returns the list of auto suggestions displayed by the amazon search box
		return AutoSuggestions;
	}

	public void clickSearchButton()
	{
		//This method clicks on the Search button
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" Clicking the Search button", true);
		SearchButton.click();
	}
	
	public List<WebElement> getListOfProductsOnThePage()
	{
		return SearchedProducts;
	}
	
	public int getLastPageNumber()
	{
		//Gets the last page number as String, converts that to Integer & returns the value
		return Integer.parseInt(lastPageNumber.getText());
	}
	
	public boolean isElementDisplayed(WebElement Elem)
	{
		try
		{
			return Elem.isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void goToNextPage()
	{
		//Clicks on the next page web link
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" Going to next page", true);
		NextPage.click();
	}
	
	public int getCurrentPageNumber()
	{
		//Returns the current page number
		return Integer.parseInt(CurrentPageNumber.getText());
	}
	
	public WebElement CurrentPageNumber()
	{
		return CurrentPageNumber;
	}
	
	public WebElement NextPageArrow()
	{
		return NextPage;
	}
	
	public WebElement FirstProductListed()
	{
		return firstProductListed;
	}
	
	public String getTextFirstProductListed()
	{
		return firstProductListed.getText();
	}

}
