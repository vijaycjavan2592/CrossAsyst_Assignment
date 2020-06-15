package com.assignment.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.assignment.base.BaseClass;

public class OrderHistoryPage extends BaseClass {
	
	public OrderHistoryPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}
	
	@FindBy(xpath="//table[@id='order-list']//tr")
	List<WebElement> tableSize;
	
	public void getTableSize() {
		int size = tableSize.size();
		System.out.println("Table size is : "+size);
	}

	@FindBy(xpath="//table[@id='order-list']//tbody//tr[1]")
	List<WebElement> orderData;
	
	
	public String getOrderHistory(String orderReference) throws InterruptedException {
		String orderCost = null;
		for(int i=1 ; i<tableSize.size() ; i++) {
			WebElement data = driver.findElement(By.xpath("//table[@id='order-list']//tbody//tr["+i+"]"));
								
			if(data.getText().contains(orderReference)) {
				WebElement el = driver.findElement(By.xpath("//table[@id='order-list']//tr//td[3]"));
				System.out.println("Cost is : "+el.getText());
				Thread.sleep(200);
				orderCost = el.getText();
				break;
			}else {
				System.out.println("Order Reference is not available");
			}
			
		}
		return orderCost;
	}
	
	
	

}
