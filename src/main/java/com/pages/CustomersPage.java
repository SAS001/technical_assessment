package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.core.DataReader;
import com.core.Setup;

public class CustomersPage extends Setup
{
	public CustomersPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	//----Web Elements----
	
	@FindBy(xpath = "//input[@placeholder='Search Customer']")
	private WebElement customerSearchBox;
	
	private int getTotalRowCountInCustomerTable() 
	{
		int totalRowsInCustomerTable = 0;
		totalRowsInCustomerTable = driver.findElements(By.xpath("//table[@class='table table-bordered table-striped']//following::tr")).size();
		return totalRowsInCustomerTable;
	}

	//-----Methods----
	
	//Verify a customer availability
	public void verifyCustomerAvailability(String firstName, String lastName, String postCode, String action) 
	{
		try 
		{
			customerSearchBox.clear();
			customerSearchBox.sendKeys(firstName);
			logStep(Status.PASS, "Customer first name "+firstName+" is typed in the search box to find: "+firstName+" "+lastName);
			int totalRowsCustomerTable = getTotalRowCountInCustomerTable();
			
			if(0 < (totalRowsCustomerTable-1)) 
			{
				try 
				{
					for(int row=1; row<totalRowsCustomerTable; row++)
					{
						String firstNameLabel = driver.findElement(By.xpath("(//table[@class='table table-bordered table-striped']//following::tr/td[1])["+(row+1)+"]")).getText();
						
						String lastNameLabel = driver.findElement(By.xpath("(//table[@class='table table-bordered table-striped']//following::tr/td[2])["+(row+1)+"]")).getText();
						
						String postCodeLabel = driver.findElement(By.xpath("(//table[@class='table table-bordered table-striped']//following::tr/td[3])["+(row+1)+"]")).getText();
						
						if(firstName.equalsIgnoreCase(firstNameLabel)) 
						{
							if(lastName.equalsIgnoreCase(lastNameLabel) && postCode.equalsIgnoreCase(postCodeLabel)) 
							{
								if(action.equalsIgnoreCase("Verify")) 
								{
									logStep(Status.PASS, "Customner "+firstName+" "+lastName+" is added successfully with post code "+postCode);
								}else if (action.equalsIgnoreCase("Delete")) 
								{
									driver.findElement(By.xpath("(//table[@class='table table-bordered table-striped']//following::tr/td[5])["+(row+1)+"]/button")).click();
									logStep(Status.PASS, "Customner "+firstName+" "+lastName+" is deleted successfully");
								}	
								break;
							}
						}else if (!lastNameLabel.equalsIgnoreCase(firstName)) 
						{
							logStep(Status.FAIL, "Customner's both first name: "+firstName+" and last name: "+lastName+" is not matched, defect in the search function");
							Assert.fail();
						}
					}
				} catch (NoSuchElementException e) {
					logStep(Status.FAIL, "User is not added successfully");
					e.printStackTrace();
				}
			}else if(0 == (totalRowsCustomerTable-1))
			{
				logStep(Status.INFO, "Customer table is empty ");
				Assert.fail();
			}
		} catch (NoSuchElementException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Verify customers availability
	public void verifyCustomersAvailability() 
	{
		String [][] newDataSetVerify = null;
		
		newDataSetVerify = DataReader.getSpreadSheetData("CustomerRegistration");
		
		int newTotalColumnsVerify = newDataSetVerify[0].length;
		
		for(int r=0; r < newDataSetVerify.length; r++) 
		{
			verifyCustomerAvailability(newDataSetVerify[r][newTotalColumnsVerify-3], newDataSetVerify[r][newTotalColumnsVerify-2], newDataSetVerify[r][newTotalColumnsVerify-1], "Verify");
		}
	}
	
	//Delete customers
	public void deleteCustomers() 
	{
		String [][] newDataSetDelete = null;
		
		newDataSetDelete = DataReader.getSpreadSheetData("DeleteCustomers");
		
		int newTotalColumnsDelete = newDataSetDelete[0].length;
		
		for(int r=0; r < newDataSetDelete.length; r++) 
		{
			verifyCustomerAvailability(newDataSetDelete[r][newTotalColumnsDelete-3], newDataSetDelete[r][newTotalColumnsDelete-2], newDataSetDelete[r][newTotalColumnsDelete-1], "Delete");
		}
	}
}
