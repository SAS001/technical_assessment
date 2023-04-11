package com.pages;

import org.openqa.selenium.By;
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

public class CustomerHomePage extends Setup
{
	public CustomerHomePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	//----Web Elements----
	
	@FindBy(id = "accountSelect")
	private WebElement selectCustomerAccountDropDown;
	
	@FindBy(xpath = "//span[normalize-space(text())='Hermoine Granger']")
	private WebElement customerAccountNameLabel;
	
	@FindBy(xpath = "(//button[normalize-space(text())='Deposit'])[1]")
	private WebElement depositMenuButton;
	
	@FindBy(xpath = "//button[normalize-space(text())='Withdrawl']")
	private WebElement withdrawlButton;
	
	@FindBy(xpath = "//input[@placeholder='amount']")
	private WebElement amountAddingTextField;
	
	@FindBy(xpath = "(//button[normalize-space(text())='Deposit'])[2]")
	private WebElement depositButton;
	
	@FindBy(xpath = "//button[normalize-space(text())='Withdraw']")
	private WebElement withdrawButton;
	
	@FindBy(xpath = "(//div[@ng-hide='noAccount'])[1]/strong[2]")
	private WebElement customerBalaceLabel;
	
	@FindBy(xpath = "//span[normalize-space(text())='Deposit Successful']")
	private WebElement depositSuccessMessageLabel;
	
	@FindBy(xpath = "//span[normalize-space(text())='Transaction successful']")
	private WebElement withdrawalSuccessMessageLabel;
	
	@FindBy(xpath = "//span[normalize-space(text())='Transaction Failed. You can not withdraw amount more than the balance.']")
	private WebElement withdrawalFailedMessageLabel;
	
	//------Methods------
	
	//Verify navigation to customer account page
	public void verifyCustomerAccountPageNavigation()
	{
		try 
		{
			String customer = DataReader.getConfigValue().getProperty("customer");
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space(text())='"+customer+"']")));
			
			logStep(Status.PASS, "Customer "+customer+" has navigated to own account portal successfully");
			
		} catch (NoSuchElementException e) 
		{
			logStep(Status.FAIL, "Customer has not navigated to own account portal successfully");
			e.printStackTrace();
		}
	}
	
	//Select customer account
	public void selectCustomerAccount()
	{
		try 
		{
			String customerAccount = DataReader.getConfigValue().getProperty("customeraccount");
			Select selectAcc = new Select(selectCustomerAccountDropDown);
			selectAcc.selectByVisibleText(customerAccount);
			
			logStep(Status.PASS, "Customer account "+customerAccount+" is selected successfully");
			
		} catch (NoSuchElementException e)
		{
			logStep(Status.FAIL, "Customer account is not selected successfully");
			e.printStackTrace();
		}
	}
	
	//Perform single transaction
	public void performSingleTransaction(String transactionType, String Amount) 
	{
		try 
		{
			double balance = Double.parseDouble(customerBalaceLabel.getText());
			
			if(transactionType.equalsIgnoreCase("Debit")) 
			{			
				try
				{
					depositMenuButton.click();
					amountAddingTextField.clear();
					amountAddingTextField.sendKeys(Amount);
					depositButton.click();
					
					if(depositSuccessMessageLabel.isDisplayed()) 
					{
						logStep(Status.PASS, transactionType+" of Amlount(Rupee) "+Amount+" is completed successfully");
						balance = balance + Double.parseDouble(Amount);
					}
				} catch (NoSuchElementException e) 
				{
					logStep(Status.FAIL, transactionType+" of Amlount(Rupee) "+Amount+" is not completed successfully");
					e.printStackTrace();
				}
			}else if (transactionType.equalsIgnoreCase("Credit")) {
				
				if(balance < Double.parseDouble(Amount)) 
				{
					logStep(Status.INFO, transactionType+" of Amlount(Rupee): "+Amount+" is not possible as available balance is(Rupee):"+balance);
				}else 
				{					
					try
					{
						withdrawlButton.click();
						amountAddingTextField.clear();
						Thread.sleep(2000);
						amountAddingTextField.sendKeys(Amount);
						withdrawButton.click();
						
						if(withdrawalSuccessMessageLabel.isDisplayed()) 
						{
							logStep(Status.PASS, transactionType+" of Amlount(Rupee): "+Amount+" is completed successfully");
							balance = balance - Double.parseDouble(Amount);
						}
					} catch (NoSuchElementException e) 
					{
						logStep(Status.FAIL, transactionType+" of Amlount(Rupee): "+Amount+" is not completed successfully");	
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			double newBalanceUI = Double.parseDouble(customerBalaceLabel.getText());
			
			if(newBalanceUI == balance) 
			{
				logStep(Status.PASS, "Calculated current balance is equal to displayed balance in Customer home page (Rupee): "+newBalanceUI);
			}else 
			{
				logStep(Status.FAIL, "Calculated current balance: "+balance+" is not equal to displayed balance in Customer home page:(Rupee) "+newBalanceUI);
			}
			
		} catch (NoSuchElementException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Perform customer transactions
	public void performTransactions(String customerTransactionSheet) 
	{
		try 
		{
			String [][] dataSetTransactions = null;
			
			dataSetTransactions = DataReader.getSpreadSheetData(customerTransactionSheet);
			
			int newTotalColumnsTransactions = dataSetTransactions[0].length;
			
			for(int r=0; r < dataSetTransactions.length; r++) 
			{
				performSingleTransaction(dataSetTransactions[r][newTotalColumnsTransactions-1], dataSetTransactions[r][newTotalColumnsTransactions-2]);
			}
			
			
		} catch (ArrayIndexOutOfBoundsException e) 
		{
			e.printStackTrace();
		}
	}
}
