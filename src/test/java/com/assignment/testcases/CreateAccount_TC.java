package com.assignment.testcases;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.assignment.base.BaseClass;
import com.assignment.coomonFunctions.TestDataProvider;
import com.assignment.coomonFunctions.Util;
import com.assignment.pages.CreateAccountPage;
import com.assignment.pages.HomePage;
import com.assignment.pages.LoginPage;
import com.assignment.pages.OrderHistoryPage;
import com.assignment.pages.UserProfilePage;
import com.assignment.pages.womenSectionPage;

public class CreateAccount_TC extends BaseClass{
	
	LoginPage login;
	HomePage home;
	womenSectionPage womenSection;
	UserProfilePage userProfile;
	OrderHistoryPage orderHistory;
	CreateAccountPage createAccount;
	
	
	@Test(dataProvider = "getTestData")
	public void verifyCreateAccountFunctionality(Hashtable<String, String> dataTable) throws InterruptedException {
		login = new LoginPage(driver);
		home = new HomePage(driver);
		womenSection = new womenSectionPage(driver);
		userProfile = new UserProfilePage(driver);
		orderHistory = new OrderHistoryPage(driver);
		createAccount = new CreateAccountPage(driver);
		
		home.signInButtonClk();
		
		login.clkCreateAnAccount(Util.generateRandomEmailId());
		
		
		createAccount.createAccount(dataTable.get("firstName"), dataTable.get("lastName"), dataTable.get("password"),
				dataTable.get("date"), dataTable.get("month"), dataTable.get("year"),
				dataTable.get("address_firstName"), dataTable.get("address_lastName"), dataTable.get("address_company"),
				dataTable.get("address_Address"), dataTable.get("address_AddressLine2"), dataTable.get("address_city"),
				dataTable.get("state"), dataTable.get("address_zipCode"), dataTable.get("mobileNumber"),
				dataTable.get("addressAlias"));
	}

	@DataProvider
	public Object[][] getTestData() {
		return TestDataProvider.getTestData(
				"C:\\Users\\chavan_v\\eclipse-workspace\\CrossAsyst_Test\\src\\test\\TestData\\CrossAsyst_TestData.xlsx",
				"Order_Place", "CreateAccount");
	}

}
