package com.assignment.coomonFunctions;

import org.openqa.selenium.WebElement;

public class WebText {
	
	public static String getText(WebElement element) throws InterruptedException {
		Thread.sleep(3000);
		if(WebElementCommon.isDisplay(element)){
			element.getText();
		}else {
			System.out.println("Button is not available");
		}
		return element.getText();
	}

}
