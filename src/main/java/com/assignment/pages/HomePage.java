package com.assignment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.assignment.base.BaseClass;
import com.assignment.coomonFunctions.Util;
import com.assignment.coomonFunctions.WebElementCommon;
import com.assignment.coomonFunctions.WebClick;

public class HomePage extends BaseClass {
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@class='login']")
	WebElement clkSignInBtn;
	
	@FindBy(xpath="//a[@class='sf-with-ul'][contains(text(),'Women')]")
	WebElement clkWomenSection;
	
	@FindBy(xpath="//div[@class='row']//a[@class='account']")
	WebElement clkUserProfile;
	
	
	@FindBy(id = "popup-announcement")
	WebElement popUp;
	
	@FindBy(id = "popup-announcement-close")
	WebElement popUp_close;
	
	//Handle pop up 
	public void handlePopUp() throws InterruptedException  {
		if(popUp.isDisplayed()) {
			WebClick.click(popUp_close);
		}else {
			System.out.println("Pop up is not available on Haome page");
		}
	}
	public LoginPage signInButtonClk() throws InterruptedException {		
		Thread.sleep(1000);
		WebClick.click(clkSignInBtn);
		return new LoginPage(driver);		
	}
	
	public womenSectionPage womenSectionClk() throws InterruptedException {		
		Thread.sleep(1000);
		WebClick.click(clkWomenSection);
		return new womenSectionPage(driver);		
	}
	
	public UserProfilePage userProfileClk() throws InterruptedException {		
		Thread.sleep(1000);
		WebClick.click(clkUserProfile);
		return new UserProfilePage(driver);		
	}
}
