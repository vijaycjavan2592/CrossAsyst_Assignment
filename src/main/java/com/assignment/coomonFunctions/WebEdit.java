package com.assignment.coomonFunctions;

import org.openqa.selenium.WebElement;

public class WebEdit {
	
	public static void sendInput(WebElement textBox, String text) throws InterruptedException {
		if(WebElementCommon.isClickable(textBox)) {
			textBox.clear();
			Thread.sleep(100);
			textBox.sendKeys(text);
		}else {
			System.out.println("Text box is not available");
		}
	}
}
