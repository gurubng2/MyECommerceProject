package com.projects.pom;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.projects.utils.TimeStamp;

public class BasePage {
	
	//This base page contains common things across all the pages
	
	WebDriver driver;
	
	@FindBy(id="nav-link-yourAccount")
	private WebElement SignInHoverDropDown;
	
	@FindBy(xpath="//div[@id='nav-flyout-ya-signin']//span[@class='nav-action-inner']")
	private WebElement SignInButton;
	
	//Search box WebElement
	@FindBy(id="twotabsearchtextbox")
	private WebElement MainSearchBox;
	
	@FindBy(id="nav-your-amazon")
	private WebElement YourAmazonLink;
	
	@FindBy(xpath="//*[.='Hello. Sign in']")
	private WebElement HelloSignInLink;
	
	public BasePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void InitiateSignIn()
	{
		//Method to hover over the SignIn drop-down & then click the option containing the Sign-In button
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" Hovering over the SignIn drop-down & then clicking the option containing the Sign-In button", true);
		
		Actions act = new Actions(driver);
		act.moveToElement(SignInHoverDropDown).perform();
		SignInButton.click();
		
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" Sign-In button is clicked, navigating to Sign-In page", true);
	}
	
	public void clickHelloSignInLink()
	{
		//Directly clicking the "Hello. Sign In" link
		HelloSignInLink.click();
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" Sign-In button is clicked, navigating to Sign-In page", true);
	}
	
	public Boolean isSearchBoxDisplayed()
	{
		//Boolean check to make sure that the Search box is displayed. This returns true/false.
		boolean SearchBoxAvailability = MainSearchBox.isDisplayed();
		
		if (SearchBoxAvailability)
		{
			Reporter.log(TimeStamp.getSimpleTimeStampt()+" Search box is displayed", true);
		}
		else
		{
			Reporter.log(TimeStamp.getSimpleTimeStampt()+" Search box is NOT displayed", true);
		}
		return SearchBoxAvailability;
	}
	
	public void switchToNewWindow()
	{
		//Using this method we can navigate to the newly launched tab (i.e., window)
		Set<String> winHandles = driver.getWindowHandles();
		for (String newWindow: winHandles)
		{
			driver.switchTo().window(newWindow);
		}
	}
	
	public void goBack()
	{
		driver.navigate().back();
	}
	
	public void SendProductToSearchBar(String ProductName)
	{
		//This method sends the product name to the search box
		MainSearchBox.sendKeys(ProductName);
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" Sent the string "+ProductName+" to the searchbox", true);
	}
	
	public String get_YourAmazon_LinkText()
	{
		//Using this method & then comparing the results we can make sure that the successful login has happened
		
		//Basically, without login this method returns the String "Your Amazon.in".
		//Otherwise, instead of the string "Your's", argument "user-name" will be replaced
		return YourAmazonLink.getText();
	}
	

	

}
