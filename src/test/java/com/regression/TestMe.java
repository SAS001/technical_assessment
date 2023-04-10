package com.regression;

import org.testng.annotations.*;

import com.core.Setup;

public class TestMe extends Setup
{	
	@BeforeMethod
	public static void beforeMethod() 
	{
		startTest("Test case 01");
		openBrowser();
	}
	
	@Test
	public static void testCase() 
	{
		
	}
	
	@AfterMethod
	public static void afterMethod() 
	{
		endTest();
		driver.quit();
	}
}
