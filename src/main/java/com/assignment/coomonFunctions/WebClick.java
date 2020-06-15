package com.assignment.coomonFunctions;

import org.openqa.selenium.WebElement;

public class WebClick {

	public static void click(WebElement element) {
		if(WebElementCommon.isClickable(element)){
			element.click();
		}else {
			System.out.println("Button is not available");
		}
	}
}
