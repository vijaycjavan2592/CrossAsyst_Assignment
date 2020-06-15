package com.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.assignment.base.BaseClass;
import com.assignment.coomonFunctions.WebButton;
import com.assignment.coomonFunctions.WebEdit;

public class LoginPage extends BaseClass {
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(name="email")
	WebElement enterUserName;
	
	@FindBy(name="passwd")
	WebElement enterPassword;
	
	@FindBy(id="SubmitLogin")
	WebElement clkSignInButton;
	
	@FindBy(id="email_create")
	WebElement enterEmailId;
	
	@FindBy(id="SubmitCreate")
	WebElement clkCreateAnAccount;
	
	public HomePage login(String userName, String password) throws InterruptedException {
		System.out.println("Application Launching...");		
		WebEdit.sendInput(enterUserName, userName);
		WebEdit.sendInput(enterPassword, password);
		WebButton.buttonClick(clkSignInButton);
		return new HomePage(driver);		
	}
	
	public CreateAccountPage clkCreateAnAccount(String emailId) throws InterruptedException {	
		enterEmailId.click();
		enterEmailId.sendKeys(emailId);	
		Thread.sleep(2000);
		WebButton.buttonClick(clkCreateAnAccount);		
		return new CreateAccountPage(driver);
	}
	
}
