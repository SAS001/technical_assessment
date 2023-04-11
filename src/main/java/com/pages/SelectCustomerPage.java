package com.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.core.DataReader;
import com.core.Setup;

public class SelectCustomerPage extends Setup
{
	public SelectCustomerPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	//----Web Elements----
	
	@FindBy(id = "userSelect")
	private WebElement selectYourNameDropDown;
	
	@FindBy(xpath = "//button[normalize-space(text())='Login']")
	private WebElement customerLoginButton;
	
	//---Methods---------
	
	//Select customer name from drop down
	public void selectCustomerName() 
	{
		try
		{
			String customerName = DataReader.getConfigValue().getProperty("customer");
			
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(selectYourNameDropDown));
			
			logStep(Status.PASS, "Your Name drop down is loaded successfully");
			
			Select selectName = new Select(selectYourNameDropDown);
			selectName.selectByVisibleText(customerName);
			logStep(Status.PASS, "Name "+customerName+" is selected successfully");
		} catch (NoSuchElementException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Login to customer portal
	public void clickLoginButton() 
	{
		try 
		{
			customerLoginButton.click();
			logStep(Status.PASS, "Login button is clicked successfully");
		} catch (NoSuchElementException e)
		{
			e.printStackTrace();
		}
	}
}
