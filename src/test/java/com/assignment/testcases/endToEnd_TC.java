package com.assignment.testcases;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.assignment.base.BaseClass;
import com.assignment.coomonFunctions.TestDataProvider;
import com.assignment.coomonFunctions.Util;
import com.assignment.coomonFunctions.WebText;
import com.assignment.pages.CreateAccountPage;
import com.assignment.pages.HomePage;
import com.assignment.pages.LoginPage;
import com.assignment.pages.OrderHistoryPage;
import com.assignment.pages.UserProfilePage;
import com.assignment.pages.womenSectionPage;

public class endToEnd_TC extends BaseClass {
	LoginPage login;
	HomePage home;
	womenSectionPage womenSection;
	UserProfilePage userProfile;
	OrderHistoryPage orderHistory;
	CreateAccountPage createAccount;

	public void navigateToHomePage() throws InterruptedException {
		login = new LoginPage(driver);
		home = new HomePage(driver);
		womenSection = new womenSectionPage(driver);
		userProfile = new UserProfilePage(driver);
		orderHistory = new OrderHistoryPage(driver);
		// Click on Sign In button/link
		home.signInButtonClk();
		// Enter the credentials and click on Sign In button
		login.login(prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(3000);
		Assert.assertEquals(Util.getTitle(), "My account - My Store");
	}

	@Test(dataProvider = "getTestData_verifyPlaceOrderWithNewUser")
	public void verifyPlaceOrderWithNewUser(Hashtable<String, String> dataTable) throws InterruptedException {
		try {
			login = new LoginPage(driver);
			home = new HomePage(driver);
			womenSection = new womenSectionPage(driver);
			userProfile = new UserProfilePage(driver);
			orderHistory = new OrderHistoryPage(driver);
			createAccount = new CreateAccountPage(driver);
			// Click on Sign In button/link
			home.signInButtonClk();
			// Enter email and click on Create Account button
			login.clkCreateAnAccount(Util.generateRandomEmailId());
			// Create account
			createAccount.createAccount(dataTable.get("firstName"), dataTable.get("lastName"),
					dataTable.get("password"), dataTable.get("date"), dataTable.get("month"), dataTable.get("year"),
					dataTable.get("address_firstName"), dataTable.get("address_lastName"),
					dataTable.get("address_company"), dataTable.get("address_Address"),
					dataTable.get("address_AddressLine2"), dataTable.get("address_city"), dataTable.get("state"),
					dataTable.get("address_zipCode"), dataTable.get("mobileNumber"), dataTable.get("addressAlias"));

			// Navigate to Women Section page
			home.womenSectionClk();
			// Thread.sleep(2000);
			womenSection.placeOrder(dataTable.get("itemToSelect"), dataTable.get("quantity"), dataTable.get("size"),
					dataTable.get("paymentOption"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Getting the amount of placed order
		String Order_Amount = womenSectionPage.amount;
		String amountOn_SummaryPage = womenSectionPage.amount_SummaryPage;
		String amountOn_PaymentPage = womenSectionPage.amount_PaymentPage;

		// Verify the amount on orderPlace_frame and Summary Page
		Assert.assertEquals(Order_Amount, amountOn_SummaryPage);

		// Verify the amount on orderPlace_frame and Payment Page
		Assert.assertEquals(Order_Amount, amountOn_PaymentPage);

		// Getting Order Reference Number
		String orderReferenceNumber = womenSection.getOrderReferenceNumber(dataTable.get("paymentOption"));

		// Navigate to profile page
		home.userProfileClk();

		// navigate to Order History and Details page
		userProfile.getClkOn_OrderHistoryandDetails();

		// Verify the page title
		Assert.assertEquals(Util.getTitle(), "Order history - My Store");

		// verifying the amount of order under ORDER HISTORY with amount of placed order
		Assert.assertEquals(Order_Amount, orderHistory.getOrderHistory(orderReferenceNumber));

	}

	@DataProvider
	public Object[][] getTestData_verifyPlaceOrderWithNewUser() {
		return TestDataProvider.getTestData(
				"C:\\Users\\chavan_v\\eclipse-workspace\\CrossAsyst_Test\\src\\test\\TestData\\CrossAsyst_TestData.xlsx",
				"Order_Place", "PlaceOrder_With_NewAccount");
	}

	/*****************************************************************************************************************/

	@Test(dataProvider = "getTestData")
	public void verifyPlacedOrderWithOrderHistory(Hashtable<String, String> dataTable) throws InterruptedException {
		try {
			// Navigate to Home page
			navigateToHomePage();

			// Navigate to Women Section page
			home.womenSectionClk();
			// Thread.sleep(2000);
			womenSection.placeOrder(dataTable.get("itemToSelect"), dataTable.get("quantity"), dataTable.get("size"),
					dataTable.get("paymentOption"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Getting the amount of placed order
		String Order_Amount = womenSectionPage.amount;
		String amountOn_SummaryPage = womenSectionPage.amount_SummaryPage;
		String amountOn_PaymentPage = womenSectionPage.amount_PaymentPage;

		// Verify the amount on orderPlace_frame and Summary Page
		Assert.assertEquals(Order_Amount, amountOn_SummaryPage);

		// Verify the amount on orderPlace_frame and Payment Page
		Assert.assertEquals(Order_Amount, amountOn_PaymentPage);

		// Getting Order Reference Number
		String orderReferenceNumber = womenSection.getOrderReferenceNumber(dataTable.get("paymentOption"));

		// Navigate to profile page
		home.userProfileClk();

		// navigate to Order History and Details page
		userProfile.getClkOn_OrderHistoryandDetails();

		// Verify the page title
		Assert.assertEquals(Util.getTitle(), "Order history - My Store");

		// verifying the amount of order under ORDER HISTORY with amount of placed order
		Assert.assertEquals(Order_Amount, orderHistory.getOrderHistory(orderReferenceNumber));

	}

	@Test(dataProvider = "getTestData")
	public void verifyPlacedOrderFunctionality(Hashtable<String, String> dataTable) throws InterruptedException {
		try {
			// Navigate to Home page
			navigateToHomePage();

			// Navigate to Women Section page
			home.womenSectionClk();
			// Thread.sleep(2000);
			womenSection.placeOrder(dataTable.get("itemToSelect"), dataTable.get("quantity"), dataTable.get("size"),
					dataTable.get("paymentOption"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Getting the amount of placed order
		String Order_Amount = womenSectionPage.amount;
		String amountOn_SummaryPage = womenSectionPage.amount_SummaryPage;
		String amountOn_PaymentPage = womenSectionPage.amount_PaymentPage;

		// Verify the amount on orderPlace_frame and Summary Page
		Assert.assertEquals(Order_Amount, amountOn_SummaryPage);

		// Verify the page title - order confirmation page
		Assert.assertEquals(Util.getTitle(), "Order confirmation - My Store");
	}

	@DataProvider
	public Object[][] getTestData() {
		return TestDataProvider.getTestData(
				"C:\\Users\\chavan_v\\eclipse-workspace\\CrossAsyst_Test\\src\\test\\TestData\\CrossAsyst_TestData.xlsx",
				"Order_Place", "Place_order_And_verify_with_order_History");
	}

}
