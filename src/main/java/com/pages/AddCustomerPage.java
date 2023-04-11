package com.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.core.DataReader;
import com.core.Setup;

public class AddCustomerPage extends Setup
{
	public AddCustomerPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	//----Web Elements----
	
	@FindBy(xpath = "//input[@placeholder='First Name']")
	private WebElement firstNameTextField;
	
	@FindBy(xpath = "//input[@placeholder='Last Name']")
	private WebElement lastNameTextField;
	
	@FindBy(xpath = "//input[@placeholder='Post Code']")
	private WebElement postCodeTextField;
	
	@FindBy(xpath = "(//button[normalize-space(text())='Add Customer'])[2]")
	private WebElement addCustomerButton;
	
	//----Methods--------
	
	//Enter new Customer details
	public void enterCustomerDetails(String firstName, String lastName, String postCode) 
	{
		try 
		{
			firstNameTextField.sendKeys(firstName);

			lastNameTextField.sendKeys(lastName);
			
			postCodeTextField.sendKeys(postCode);
			
			logStep(Status.PASS, "Customer "+firstName+" "+lastName+" is entered successfully with post code "+postCode);
			
			addCustomerButton.click();
			logStep(Status.PASS, "Add Customer button is clicked successfully");
			
			acceptCustomerAddedMessagePopUp();
			
		} catch (NoSuchElementException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Accept customer adding verify pop-up
	public void acceptCustomerAddedMessagePopUp() 
	{
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	//Add new  customers
	public void addNewCustomers() 
	{
		try 
		{
			String [][] newDataSet = null;
			
			newDataSet = DataReader.getSpreadSheetData("CustomerRegistration");
			
			int newTotalColumns = newDataSet[0].length;
			
			for(int r=0; r < newDataSet.length; r++) 
			{
				enterCustomerDetails(newDataSet[r][newTotalColumns-3], newDataSet[r][newTotalColumns-2], newDataSet[r][newTotalColumns-1]);
			}
			
		} catch (ArrayIndexOutOfBoundsException e)
		{
			e.printStackTrace();
		}
	}
	
}
