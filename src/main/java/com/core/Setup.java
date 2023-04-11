package com.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Setup 
{
	public static WebDriver driver = null;
	public static ExtentReports extent = null;
	public static ExtentTest test = null;
	
	//Initiate report generation before execution start
	@BeforeSuite
	public static void generateReports() 
	{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		extent = new ExtentReports();
		ExtentSparkReporter report = new ExtentSparkReporter("./reports/"+timeStamp+"_Report.html");
		extent.attachReporter(report);
	}
	
	//Initiate driver instance, open web browser 
	public static void openBrowser()
	{	
		String browserName = DataReader.getConfigValue().getProperty("browser");
		String baseURL = DataReader.getConfigValue().getProperty("baseurl");
		
		if(browserName.equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.get(baseURL);
		}else 
		{
			org.testng.Assert.fail();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	//Initiate test with test case name
	public static void startTest(String testCaseName) 
	{
		test = extent.createTest(testCaseName);
	}
	
	//log execution step
	public static void logStep(Status status, String message) 
	{
		test.log(status, message);
	}
	
	//End of the test
	public static void endTest() 
	{
		extent.flush();
	}
}
