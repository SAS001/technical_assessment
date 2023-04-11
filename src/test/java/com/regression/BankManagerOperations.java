package com.regression;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.core.Setup;
import com.pages.AddCustomerPage;
import com.pages.BankManagerOperationsHomePage;
import com.pages.CustomersPage;
import com.pages.XYZBankHomePage;

public class BankManagerOperations extends Setup 
{
	@BeforeMethod
	public static void beforeMethod() 
	{
		startTest("Bank Manager Operations");
		openBrowser();
	}

	@Test
	public static void testCase() 
	{	
		//Verify XYZ bank home page and log as Bank manager
		XYZBankHomePage bankHomePage = new XYZBankHomePage();
		bankHomePage.verifyXYZBankHomePageLoading();
		bankHomePage.clickBankManagerLoginButton();
		
		//Navigates to add new customers
		BankManagerOperationsHomePage managerOperations = new BankManagerOperationsHomePage();
		managerOperations.clickCustomersButtonMainMenu();
		managerOperations.clicAddCustomerButtonMainMenu();
		
		//Add new customers
		AddCustomerPage addCustomers = new AddCustomerPage();
		addCustomers.addNewCustomers();
		
		//Navigates to Customer Tab
		managerOperations.clickCustomersButtonMainMenu();
		
		//Verify added customers availability
		CustomersPage customerPage = new CustomersPage();
		customerPage.verifyCustomersAvailability();
		
		//Delete Customers
		customerPage.deleteCustomers();
	}

	@AfterMethod
	public static void afterMethod()
	{
		endTest();
	    driver.quit();
	}
}
