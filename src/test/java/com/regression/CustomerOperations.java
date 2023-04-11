package com.regression;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.core.Setup;
import com.pages.AddCustomerPage;
import com.pages.BankManagerOperationsHomePage;
import com.pages.CustomerHomePage;
import com.pages.CustomersPage;
import com.pages.SelectCustomerPage;
import com.pages.XYZBankHomePage;

public class CustomerOperations extends Setup
{
	@BeforeMethod
	public static void beforeMethod() 
	{
		startTest("Customer Operations");
		openBrowser();
	}

	@Test
	public static void testCase() 
	{	
		//Verify XYZ bank home page and log as Bank manager
		XYZBankHomePage bankHomePage = new XYZBankHomePage();
		bankHomePage.verifyXYZBankHomePageLoading();
		bankHomePage.clickCustomerLoginButton();
		
		//Select Customer name from drop down
		SelectCustomerPage selectCustomer = new SelectCustomerPage();
		selectCustomer.selectCustomerName();
		selectCustomer.clickLoginButton();
		
		//Navigates to own customer portal page
		CustomerHomePage customerAcc = new CustomerHomePage();
		customerAcc.verifyCustomerAccountPageNavigation();
		
		//Select customer account
		customerAcc.selectCustomerAccount();
		
		//Perform transactions
		customerAcc.performTransactions("Customer_1003_Transactions");
		
		
	}

	@AfterMethod
	public static void afterMethod()
	{
		endTest();
	    driver.quit();
	}
}
