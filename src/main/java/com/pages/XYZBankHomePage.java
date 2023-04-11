package com.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.core.Setup;

public class XYZBankHomePage extends Setup
{
	public XYZBankHomePage()
	{
		PageFactory.initElements(driver, this);
	}
	//----Web Elements----
	
	@FindBy(xpath = "//button[normalize-space(text())='Home']")
	private WebElement homeButton;
	
	@FindBy(xpath = "//button[normalize-space(text())='Customer Login']")
	private WebElement customerLoginButton;
	
	@FindBy(xpath = "//button[normalize-space(text())='Bank Manager Login']")
	private WebElement bankManagerLoginButton;
	
	//----Methods--------
	
	//Verify XYZ bank home page navigation
	public void verifyXYZBankHomePageLoading() 
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(homeButton));
			logStep(Status.PASS, "XYZ Bank home page is loaded successfully");

		} catch (NoSuchElementException e)
		{
			logStep(Status.FAIL, "XYZ Bank home page is not loaded successfully");
			e.printStackTrace();
		}
	}
	
	//Login as Bank Manager
	public void clickBankManagerLoginButton() 
	{
		try 
		{
			bankManagerLoginButton.click();
			logStep(Status.PASS, "Bank Manger Login button is clicked successfully");
		} catch (NoSuchElementException e) 
		{
			logStep(Status.FAIL, "Bank Manger Login button is not clicked successfully");
			e.printStackTrace();
		}
	}
}
