package com.assignment.coomonFunctions;

import org.openqa.selenium.WebElement;

public class WebElementCommon {
	
	public static boolean isDisplay(WebElement element) {
		boolean isPresent = false;
		if(element.isDisplayed()) {
			isPresent = true;
		}
		return isPresent;
	}
	
	public static boolean isClickable(WebElement element) {
		boolean isClick = false;
		if(isDisplay(element)){
			if(element.isEnabled()) {
				isClick = true;
			}
		}
		return isClick;
	}
	


}
