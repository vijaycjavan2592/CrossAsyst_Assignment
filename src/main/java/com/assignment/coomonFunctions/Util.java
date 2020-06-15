package com.assignment.coomonFunctions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assignment.base.BaseClass;

public class Util extends BaseClass {

	public static long PAGE_LOAD_TIMEOUT = 60;
	public static long IMPLICIT_WAIT = 20;
	private String expectedMonthYear;
	private String expectedDay;

	/**************** Select option from dropdown ****************/
	public static void selectOptionFromDropDown(WebElement element, String selectoption) {
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();

		for (WebElement el : options) {
			if (el.getText().equalsIgnoreCase(selectoption)) {
				select.selectByVisibleText(selectoption);
			}
		}
	}
	/**************** Switch to window ****************/
	public static void switchWindow(int tabNumber) {
		// Get the current window handle
		String windowHandle = driver.getWindowHandle();

		// Get the list of window handles
		ArrayList tabs = new ArrayList(driver.getWindowHandles());
		System.out.println(tabs.size());
		// Use the list of window handles to switch between windows
		driver.switchTo().window((String) tabs.get(tabNumber));

		// Switch back to original window
		// driver.switchTo().window(windowHandle);
	}

	/**************** Clcik on particular element from list of element ****************/
	public static void click_FromList(List<WebElement> ele, String actaulData) throws InterruptedException {
		for (WebElement element : ele) {
			Thread.sleep(2000);
			String getData = element.getText();
			if (getData.equalsIgnoreCase(actaulData)) {
				WebClick.click(element);
			}
		}
	}

	public static void waitForLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(pageLoadCondition);
	}

	/**************** Explicit wait ****************/
	public static void explicityWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	/**************** Handle alert ****************/
	public static void alertHandle() {
		WebDriverWait wait = new WebDriverWait(driver, 10 /* timeout in seconds */);
		if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
			System.out.println("alert was not present");
		} else {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			System.out.println("alert was present and accepted");
		}
	}
	
	
	public static String getTitle() {		
		return driver.getTitle();
	}
	
	/*********** Random character **********************/
	public static String generateRandomEmailId() {
		String [] arr = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        
		int[] no = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		Random random = new Random();

        // randomly selects an index from the arr
        int select = random.nextInt(arr.length); 
        int select1 = random.nextInt(arr.length); 
        int select2 = random.nextInt(arr.length); 
        int select3 = random.nextInt(arr.length); 
        int select4 = random.nextInt(arr.length); 
        
        int number = random.nextInt(no.length);
        int number1 = random.nextInt(no.length);
        int number2 = random.nextInt(no.length);
        int number3 = random.nextInt(no.length);
        
        String name = arr[select]+arr[select1]+arr[select2]+arr[select3]+arr[select4]+no[number]+no[number1]+no[number2]+no[number3];
        String emailId = name+"@gmail.com";
        System.out.println(emailId);
        
		return emailId;       
	}
	

}