package com.projects.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.projects.utils.TimeStamp;

public class SignInPage {
	
	WebDriver driver;
	//This is the POM class for SignInPage 
	
	@FindBy(id="auth-fpp-link-bottom")
	private WebElement ForgotPasswordLink;
	
	@FindBy(id="ap_email")
	private WebElement InputBoxEmailUSN;
	
	@FindBy(id="ap_password")
	private WebElement InputBoxPassword;
	
	@FindBy(id="signInSubmit")
	private WebElement LoginButton;
	
	@FindBy(xpath="//i[@class='a-icon a-icon-alert']")
	private WebElement InvalidLoginAlert;
	
	@FindBy(xpath="//h4[@class='a-alert-heading']")
	private WebElement InvalidMessageHeader1;
	
	@FindBy(xpath="//span[@class='a-list-item']")
	private WebElement InvalidMessageHeader2;
	
	public SignInPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean IsThisSignInPage()
	{
		//Boolean check for the valid SignIn page by checking the text from the Forgot Password link
		
		String forgotPswdText = ForgotPasswordLink.getText();
		if (forgotPswdText.contains("Forgot Password"))
		{
			Reporter.log(TimeStamp.getSimpleTimeStampt()+" Checked for 'Forgot Password' text --> SignIn page is correctly displayed", true);
			return true;
		}
		else
		{
			Reporter.log(TimeStamp.getSimpleTimeStampt()+" 'Forgot Password' text is NOT displayed. Indicates that, SignIn page is NOT displayed", true);
			return false;
		}
	}
	
	public void sendUsername(String userName)
	{
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" Sending UserName to the Email/mobile number field", true);
		InputBoxEmailUSN.clear();
		InputBoxEmailUSN.sendKeys(userName);
	}
	
	public void sendPassword(String Password)
	{
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" Sending password to the password field", true);
		InputBoxPassword.clear();
		InputBoxPassword.sendKeys(Password);
	}
	
	public void Login()
	{
		Reporter.log(TimeStamp.getSimpleTimeStampt()+" Clicking the Login button", true);
		LoginButton.click();
	}
	
	public boolean CheckAlertMessage()
	{
		//On trying an Invalid login, this method checks whether the alert message is displayed 
		
		if (InvalidMessageHeader1.isDisplayed() || InvalidMessageHeader2.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	
	public String getInvalidLoginAlertHeaderText()
	{
		//Returns the text which is displayed when trying to do an invalid entry 
		return InvalidMessageHeader1.getText();
	}
	
	public String getInvalidLoginAlertContentText()
	{
		//Returns the text of the alert message which is displayed whn trying an invalid login
		return InvalidMessageHeader1.getText();
	}

}
