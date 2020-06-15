package com.assignment.coomonFunctions;

import org.openqa.selenium.WebElement;

public class WebButton {
	
	public static void buttonClick(WebElement button) {
		if(WebElementCommon.isClickable(button)){
			button.click();
		}else {
			System.out.println("Button is not available");
		}
	}

}
