package com.assignment.testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.assignment.base.BaseClass;
import com.assignment.coomonFunctions.ReadExcelDataFile;
import com.assignment.coomonFunctions.TestDataProvider;
import com.assignment.coomonFunctions.Util;

import com.assignment.pages.HomePage;
import com.assignment.pages.LoginPage;

public class Login_TC extends BaseClass {
	
	LoginPage login;
	HomePage home;
	
	
	
	@Test(dataProvider="getTestData")
	public void Login_Functionality(Hashtable<String, String> dataTable) throws InterruptedException {
		login = new LoginPage(driver);
		home = new HomePage(driver);
		
		//Click on Sign In button/link
		home.signInButtonClk();

		//Enter the credentials and click on Sign In button
		login.login(dataTable.get("UserName"), dataTable.get("Password"));
		Thread.sleep(5000);
		AssertJUnit.assertEquals(driver.getTitle(), dataTable.get("PageTitle"));
		
	}
		
	@DataProvider
	public Object[][] getTestData() {
		return TestDataProvider.getTestData("C:\\Users\\chavan_v\\eclipse-workspace\\CrossAsyst_Test\\src\\test\\TestData\\CrossAsyst_TestData.xlsx", "LoginTestData", "doXornetLoginTest");
	}
	
}
