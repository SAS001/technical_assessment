package com.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.core.Setup;

public class BankManagerOperationsHomePage extends Setup
{
	public BankManagerOperationsHomePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	//----Web Elements----
	
	@FindBy(xpath = "(//button[normalize-space(text())='Add Customer'])[1]")
	private WebElement addCustomerMenuButton;
	
	@FindBy(xpath = "//button[normalize-space(text())='Customers']")
	private WebElement customersMenuButton;
	
	//---Methods---------
	
	//click on the Add Customer button in the main menu
	public void clicAddCustomerButtonMainMenu() 
	{
		try 
		{
			addCustomerMenuButton.click();
			logStep(Status.PASS, "Add Customer button on the main menu is clicked successfully");
		} catch (NoSuchElementException e)
		{
			logStep(Status.FAIL, "Add Customer button on the main menu is not clicked successfully");
			e.printStackTrace();
		}
	}
	
	//click on the Customer button in the main menu
	public void clickCustomersButtonMainMenu() 
	{
		try 
		{
			customersMenuButton.click();
			logStep(Status.PASS, "Customers button on the main menu is clicked successfully");
		} catch (NoSuchElementException e)
		{
			logStep(Status.FAIL, "Customers button on the main menu is not clicked successfully");
			e.printStackTrace();
		}
	}
}
