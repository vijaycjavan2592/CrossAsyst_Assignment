package com.assignment.extentManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public static void setExtent()  {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//test-output//MyReport.html");
				//(System.getProperty("user.dir") + "//test-output//Reportsreportextent.html");
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		htmlReporter.config().setDocumentTitle("Automation Test Report");
		htmlReporter.config().setReportName("Book Meeting Room Test report");
		htmlReporter.config().setTheme(Theme.DARK);		
		
		extent.setSystemInfo("ProjectName", "Xornet-Book Meeting Room");
		extent.setSystemInfo("Tester", "Vijay");
		extent.setSystemInfo("Browser", "Chrome");
	}
	
	public static void endReport() {

		extent.flush();
	}

}
