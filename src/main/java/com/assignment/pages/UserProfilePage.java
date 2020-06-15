package com.assignment.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.assignment.base.BaseClass;
import com.assignment.coomonFunctions.WebClick;

public class UserProfilePage extends BaseClass {
	
	public UserProfilePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}
	
	@FindBy(xpath="//div[@class='col-xs-12 col-sm-6 col-lg-4']//a[@title='Orders']")
	WebElement clkOn_OrderHistoryandDetails;

	public OrderHistoryPage getClkOn_OrderHistoryandDetails() throws InterruptedException {
		//Thread.sleep(1000);
		WebClick.click(clkOn_OrderHistoryandDetails);
		return new OrderHistoryPage(driver);	
	}
	

}
