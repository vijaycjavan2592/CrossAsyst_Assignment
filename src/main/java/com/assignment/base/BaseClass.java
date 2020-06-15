package com.assignment.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.assignment.coomonFunctions.Util;
import com.assignment.extentManager.ExtentManager;


public class BaseClass {
	
	public static WebDriver driver;
	public Properties prop;
	
	
	public void setUp() throws IOException {
		//Read properties file
		prop = new Properties();
		FileInputStream file;
		try {
			file = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\ObjectRepo\\config.properties");
			prop.load(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Browser selection
		if(prop.getProperty("browser").equalsIgnoreCase("firefox")) {
		System.setProperty("webdriver.gecko.driver", "D:\\Jars\\gecko_driver\\geckodriver.exe");
		driver = new FirefoxDriver();
		}
		else if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe");
		//	System.setProperty("webdriver.chrome.silentOutput", "true");
			driver = new ChromeDriver();
			}
		// Timer's
		driver.manage().timeouts().pageLoadTimeout(Util.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Util.IMPLICIT_WAIT, TimeUnit.SECONDS);
		// Browser maximize and pass URL
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		// Delete cookies
		driver.manage().deleteAllCookies();
	}
	
	@BeforeMethod
	public void set() throws IOException {
		setUp();
	}
		
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
	@BeforeTest()
	public void beforeSuite() {
		ExtentManager.setExtent();
	}
	
	@AfterTest
	public void afterSuite() {
		ExtentManager.endReport();
	}
	
	// Screen shot for failed test cases
	public static String screenShot(WebDriver driver, String fileName) {
		String dateName = new SimpleDateFormat("dd-MM-yyyyhhmmss").format(new Date());
		TakesScreenshot takesScreenShot = (TakesScreenshot)driver;
		File source = takesScreenShot.getScreenshotAs(OutputType.FILE);
		String destination = (System.getProperty("user.dir")+"\\test-output\\screenshots\\fileName.png");
		File finalDestination = new File(destination);
		try {
			FileUtils.copyFile(source, finalDestination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return destination;
	}
}
