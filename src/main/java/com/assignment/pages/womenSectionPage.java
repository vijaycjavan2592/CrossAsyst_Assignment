package com.assignment.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.assignment.base.BaseClass;
import com.assignment.coomonFunctions.WebClick;
import com.assignment.coomonFunctions.WebEdit;

public class womenSectionPage extends BaseClass{
	
	public static String amount;
	public static String amount_SummaryPage;
	public static String amount_PaymentPage;
	

	public womenSectionPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}
	
	@FindBy(xpath="//div[@id='center_column']//ul[@class='product_list grid row']/li")
	List<WebElement> itemList;
	
	@FindBy(xpath="//li[@style='height: 605px; margin-bottom: -114px;']//a[@class='quick-view']")
	WebElement quickViewClk;
				
	@FindBy(xpath="//input[@id='quantity_wanted']")
	WebElement enterQuantity;
		
	@FindBy(xpath="//select[@id='group_1']")
	WebElement selectSize;

	@FindBy(name="Submit")
	WebElement AddToCardBtnClk;
	
	@FindBy(xpath="//span[@class='ajax_block_cart_total']")
	WebElement totalAmount;
	
	@FindBy(xpath="//span[contains(text(),'Proceed to checkout')]")
	WebElement ProceedToCheckoutBtnClk;

	@FindBy(xpath="//p[@class='cart_navigation clearfix']//span[contains(text(),'Proceed to checkout')]")
	WebElement proceedToCheckoutBtnClk_summary;
	
	@FindBy(xpath="//div[@id='uniform-cgv']")
	WebElement selectTermsOfService;
	
	@FindBy(xpath="//a[@class='bankwire']")
	WebElement clkPayByBankWireOption;
	
	@FindBy(xpath="//a[@class='cheque']")
	WebElement clkPayByChequeOption;
	
	@FindBy(xpath="//p[@class='cart_navigation clearfix']//span[contains(text(),'I confirm my order')]")
	WebElement iConfirmMyOrderBtnClk;
	
	@FindBy(xpath="//div[@class='row']//div[@class='box order-confirmation']")
	WebElement orderReferenceNumber_PayByCheck;
	
	@FindBy(xpath="//div[@class='row']//div[@class='box']")
	WebElement orderReferenceNumber_PayByWire;
	
	@FindBy(xpath="//span[@id='total_price']")
	WebElement totalAmount_SummaryPage;
	
	@FindBy(xpath="//span[@id='total_price']")
	WebElement totalAmount_PaymentPage;
	
	public void selectItemFrom_List(String itemToSelect) throws InterruptedException {
		for(WebElement el:itemList) {
			System.out.println("Item is : "+el.getText());
			if(el.getText().contains(itemToSelect)) {	
				Thread.sleep(2000);
				Actions action = new Actions(driver);
				action.moveToElement(el).build().perform();
				Thread.sleep(1000);
				WebClick.click(quickViewClk);
				System.out.println("We have selected : "+itemToSelect);
				
			}else {
				System.out.println("Item is not available");
			}
		}		
	}
	
	public String getOrderReferenceNumber(String paymentOption) throws InterruptedException {
		Thread.sleep(2000);
		String payment_bankWire = "Pay by bank wire";
		String payment_check = "Pay by check";
		String orderReferenceNumber = null;
		if (payment_bankWire.equalsIgnoreCase(paymentOption)) {
			String orderDetails = orderReferenceNumber_PayByWire.getText();
			String[] splitByReference = orderDetails.split("reference ");

			String referenceData = splitByReference[1];
			String[] splitBySpace = referenceData.split(" ");
			orderReferenceNumber = splitBySpace[0].trim();
		} else if (payment_check.equalsIgnoreCase(paymentOption)) {
			String orderDetails1 = orderReferenceNumber_PayByCheck.getText();			
			String[] splitByReference1 = orderDetails1.split("reference ");

			String referenceData1 = splitByReference1[1];
			String[] splitBySpace1 = referenceData1.split(". ");
			orderReferenceNumber = splitBySpace1[0].replace(".", "").trim();
			System.out.println("order reference is :"+orderReferenceNumber);
		} else {
			System.out.println("User have pay by another option.");
		}
		return orderReferenceNumber;
	}

	public void placeOrder(String itemToSelect, String quantity, String size, String paymentOption) throws InterruptedException {
		// Select the item
		selectItemFrom_List(itemToSelect);
		// Switch to the frame
		int fsize = driver.findElements(By.tagName("iframe")).size();
		System.out.println("frame size is : " + fsize);
		driver.switchTo().frame(0);
		// enter quantity
		enterQuantity.clear();
		WebEdit.sendInput(enterQuantity, quantity);
		// Select size
		Select se = new Select(selectSize);
		se.selectByValue(size);
		// Click on Add To Card Button
		WebClick.click(AddToCardBtnClk);
		
		amount = totalAmount.getText();
		
		Thread.sleep(2000);
		// Click on Proceed To Checkout Button
		WebClick.click(ProceedToCheckoutBtnClk);
		
		driver.switchTo().defaultContent();
	//	Thread.sleep(2000);
		amount_SummaryPage = totalAmount_SummaryPage.getText();
		// Click on Proceed To Checkout Button from summary page
		WebClick.click(proceedToCheckoutBtnClk_summary);
		
	//	Thread.sleep(2000);
		// Click on Proceed To Checkout Button from address page
		WebClick.click(proceedToCheckoutBtnClk_summary);
		
	//	Thread.sleep(2000);
		//Select the check box of terms of services 
		WebClick.click(selectTermsOfService);	
		
		// Click on Proceed To Checkout Button from shipping page
		WebClick.click(proceedToCheckoutBtnClk_summary);
		
	//	Thread.sleep(2000);
		//Get total amount from Payment page
		amount_PaymentPage = totalAmount_PaymentPage.getText();
		//Click on Pay By Bank Wire Option
		String payment_bankWire = "Pay by bank wire";
		String payment_check = "Pay by check";
		if(payment_bankWire.equalsIgnoreCase(paymentOption)) {
			WebClick.click(clkPayByBankWireOption);
			}
		else if(payment_check.equalsIgnoreCase(paymentOption)) {
			WebClick.click(clkPayByChequeOption);			
		}else {
			System.out.println("User have pay by another option.");
		}
		
	//	Thread.sleep(2000);
		//Click on I Confirm My Order button
		WebClick.click(iConfirmMyOrderBtnClk);
	}

	
	

}
