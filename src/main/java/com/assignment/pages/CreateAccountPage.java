package com.assignment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.assignment.base.BaseClass;
import com.assignment.coomonFunctions.WebButton;
import com.assignment.coomonFunctions.WebEdit;

public class CreateAccountPage extends BaseClass{
	
	public CreateAccountPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}

	@FindBy(id="customer_firstname")
	WebElement enterFirstName;
	
	@FindBy(id="customer_lastname")
	WebElement enterLastName;
	
	@FindBy(id="passwd")
	WebElement enterPassword;
	
	@FindBy(id="days")
	WebElement selectDate;
	
	@FindBy(id="months")
	WebElement selectMonth;
	
	@FindBy(id="years")
	WebElement selectYear;
	
	@FindBy(id="firstname")
	WebElement Address_enterFirstName;
	
	@FindBy(id="lastname")
	WebElement Address_enterLastName;
	
	@FindBy(id="company")
	WebElement Address_enterCompanyName;
	
	@FindBy(id="address1")
	WebElement Address_enterAddress;
	
	@FindBy(id="address2")
	WebElement Address_enterAddressLine2;
	
	@FindBy(id="city")
	WebElement Address_enterCity;
	
	@FindBy(id="id_state")
	WebElement selectState;
	
	@FindBy(id="postcode")
	WebElement Address_enterPostCode;
	
	@FindBy(id="phone_mobile")
	WebElement Address_enterMobileNumber;
	
	@FindBy(id="alias")
	WebElement Address_enterAddressAlias;
	
	@FindBy(id="submitAccount")
	WebElement clkRegisterButton;
	
	public void createAccount(String firstName, String lastName, String password, String date, String month, String year, String address_firstName,
			String address_lastName, String address_company, String address_Address, String address_AddressLine2,
			String address_city, String state, String address_zipCode, String mobileNumber, String addressAlias) throws InterruptedException {

		WebEdit.sendInput(enterFirstName, firstName);
		
		WebEdit.sendInput(enterLastName, lastName);
		
		WebEdit.sendInput(enterPassword, password);
		
		Select se = new Select(selectDate);
		se.selectByValue(date);
		
		Select mo = new Select(selectMonth);
		mo.selectByValue(month);
		
		Select ye = new Select(selectYear);
		ye.selectByValue(year);
		
		WebEdit.sendInput(Address_enterFirstName, address_firstName);

		WebEdit.sendInput(Address_enterLastName, address_lastName);

		WebEdit.sendInput(Address_enterCompanyName, address_company);

		WebEdit.sendInput(Address_enterAddress, address_Address);

		WebEdit.sendInput(Address_enterAddressLine2, address_AddressLine2);

		WebEdit.sendInput(Address_enterCity, address_city);
		
		Select st = new Select(selectState);
		st.selectByValue(state);

		WebEdit.sendInput(Address_enterPostCode, address_zipCode);		

		WebEdit.sendInput(Address_enterMobileNumber, mobileNumber);

		WebEdit.sendInput(Address_enterAddressAlias, addressAlias);
		
		WebButton.buttonClick(clkRegisterButton);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
