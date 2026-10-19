
package com.figs.automation.runners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CommonFlow {

    private WebDriver driver;

    // Constructor
    public CommonFlow(WebDriver driver) {
        	this.driver = driver;
    }
 // RDSTDR flow (Nitin Lawate, 15/10/25)
	public void RDSTDRflow() {
		try {
			
			// Click on "Cash Deposit" main menu
			WebElement value = driver.findElement(By.xpath("//a[normalize-space(text())='Account Opening Services']"));
			value.click();
			// Call submenu selection
			
			WebElement submenu = driver.findElement(By.xpath("//a[normalize-space(text())='RD/STDR Account Opening']"));
			submenu.click();
		} catch (Exception e) {
		}
	}
    	// Deposit flow (Lingaraj Desai, 04/06/25)
    	public void DepositeFlow(String menu) {
    		try {
    			
    			// Click on "Cash Deposit" main menu
    			WebElement value = driver.findElement(By.xpath("//a[@role='button' and normalize-space(text())='Cash Deposit']"));
    			value.click();
    			// Call submenu selection
    			ClickSubMenuKO(value, menu);

    		} catch (Exception e) {
    		}
    	}
    	
    	//withdrawal flow (Lingaraj Desai, 04/06/25)
		public void WithdrawalFlow(String menu)
		{
		    try {
		    	WebElement value =driver.findElement(By.xpath("//a[@role='button' and normalize-space(text())='Cash Withdrawal']")); 
		    	value.click();
		    	// Call submenu selection
    			ClickSubMenuKO(value, menu);
		    }
		    catch(Exception e)
		    {
		    }
		}
		
		// Fund Transfer flow (Lingaraj Desai, 04/06/25)
    	public void FundTransferFlow(String menu) {
    		try {
    			
    			// Click on "Cash Deposit" main menu
    			WebElement value = driver.findElement(By.xpath("//a[@role='button' and normalize-space(text())='Fund Transfer']"));
    			value.click();
    			// Call submenu selection
    			ClickSubMenuKO(value, menu);

    		} catch (Exception e) {
    		}
    	}
    	
    	// Balance Enquiry flow (Lingaraj Desai, 04/06/25)
    	public void BalanceEnquiryFlow(String menu) {
    		try {
    			
    			// Click on "Balance Enquiry" main menu
    			WebElement value = driver.findElement(By.xpath("//a[@role='button' and normalize-space(text())='Balance Enquiry']"));
    			value.click();
    			// Call submenu selection
    			ClickSubMenuKO(value, menu);

    		} catch (Exception e) {
    		}
    	}
    	
    	// Mini Statement flow (Lingaraj Desai, 04/06/25)
    	public void MiniStatementFlow(String menu) {
    		try {
    			
    			// Click on "Balance Enquiry" main menu
    			WebElement value = driver.findElement(By.xpath("//a[@role='button' and normalize-space(text())='Mini Statement']"));
    			value.click();
    			// Call submenu selection
    			ClickSubMenuKO(value, menu);

    		} catch (Exception e) {
    		}
    	}
    	
    	public  void ClickLoanDeposit()
    	{
    		try {
    			  

    			WebElement screen = driver.findElement(By.xpath("//a[normalize-space(text())='Loan Related Services']"));
    			
    			boolean loandpst=screen.isDisplayed();
    			if(loandpst)
    			{
    			//WebElement Menu1 = driver.findElement(By.xpath("//a[starts-with(normalize-space(text()), 'Loan Deposit')][1]"));
    			//Menu1.click();
    				 screen.click();
    			}
    			Thread.sleep(400);

    			WebElement SubMenu = driver.findElement(By.xpath("//a[normalize-space(text())='Loan Deposit']"));
    			SubMenu.click();
    		}
    		catch(Exception e)
    		{
    			WebElement screen = driver.findElement(By.xpath("(//a[starts-with(normalize-space(text()), 'Loan Deposit')][1])[1]"));
    			screen.click();
    			WebElement SubMenu = driver.findElement(By.xpath("(//a[normalize-space(text())='Loan Deposit'])[2]"));
    			SubMenu.click();
    			//System.out.println(e);
    		}
    	}
    	
    	
    	
    	
    	public void ClickViewLoanDataCollection()
    	{
    		try {
    			  

    			WebElement screen = driver.findElement(By.xpath("//a[normalize-space(text())='Loan Related Services']"));
    			
    			boolean loandpst=screen.isDisplayed();
    			if(loandpst)
    			{
    				 screen.click();
    			}
    			
    			WebElement VIEWLOANDATA = driver.findElement(By.xpath("//a[normalize-space(text())='View Loan Data Collection']"));
    			VIEWLOANDATA.click();
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    			
    		}
    	}
    	
	
    	
    	public void MoneyFlow(String menu) {
    		try {
    			
    			// Click on "Cash Deposit" main menu
    			WebElement value = driver.findElement(By.xpath("//a[contains(text(),'Money Transfer')]"));
    			value.click();
    		} catch (Exception e) {
    		}
    	}
    	
    	public  void ClickSSSMenu()
    	{
    		try {
    		WebElement sss = driver.findElement(By.xpath("(//a[normalize-space()='Social Security Schemes'])[1]"));
    		sss.click();
    		WebElement pmjjby = driver.findElement(By.xpath("//a[normalize-space()='PMJJBY']"));
    		}
    		catch (Exception e) {
    			//e.getMessage();
    			WebElement sss = driver.findElement(By.xpath("(//a[normalize-space()='Social Security Schemes'])[1]"));
        		sss.click();                                                          
    		}
    	}
    	
    	public void EaseBankingFlow()
    	{
    		try
    		{
    			WebElement easebanking = driver.findElement(By.xpath("//a[@role='button' and text()='Ease Banking']"));
    			easebanking.click();
    			boolean atm = driver.findElement(By.xpath("//a[contains(text(), 'ATM Card Block')]")).isDisplayed();
    			while(atm)
    			{
    				break;
    			}
    			
    		}
    		catch(Exception e)
    		{
    			WebElement easebanking = driver.findElement(By.xpath("//a[@role='button' and text()='Ease Banking']"));
    			easebanking.click();
    			
    		}
    	}
    	
    	
    	
    	/* Passbook Related Services  */
    	
    	
    	public void PassbookServicesFlow()
    	{
    		try
    		{
    			WebElement Passbook = driver.findElement(By.xpath("//a[@role='button' and text()='Passbook Services']"));
    			Passbook.click();
    			boolean atm = driver.findElement(By.xpath("//a[contains(text(), 'Return Passbook to Branch')]")).isDisplayed();
    			while(atm)
    			{
    				break;
    			}
    			
    		}
    		catch(Exception e)
    		{
    			WebElement Passbook = driver.findElement(By.xpath("//a[@role='button' and text()='Passbook Services']"));
    			Passbook.click();
    			
    		}
    	}
    	
    	
    	
    	
    	
    	
    	
    	
    	/* KYC Related Services  */
    	
    	public void KYCRelatedServicesFlow()
    	{
    		try
    		{
    			WebElement remittance = driver.findElement(By.xpath("//a[normalize-space(text())='KYC Related Services']"));
    			remittance.click();
    			boolean atm = driver.findElement(By.xpath("//a[normalize-space(text())='KYC Updation']")).isDisplayed();
    			while(atm)
    			{
    				break;
    			}
    			
    		}
    		catch(Exception e)
    		{
    			WebElement easebanking = driver.findElement(By.xpath("//a[normalize-space(text())='KYC Related Services']"));
    			easebanking.click();
    			
    		}
    	}
    	
    	
    	
    	/* Remittance  */
    	public void RemittanceFlow()
    	{
    		try
    		{
    			WebElement remittance = driver.findElement(By.xpath("//a[@role='button' and text()='Remittance']"));
    			remittance.click();
    			boolean atm = driver.findElement(By.xpath("//a[contains(text(), 'NEFT')]")).isDisplayed();
    			while(atm)
    			{
    				break;
    			}
    			
    		}
    		catch(Exception e)
    		{
    			WebElement easebanking = driver.findElement(By.xpath("//a[@role='button' and text()='Remittance']"));
    			easebanking.click();
    			
    		}
    	}

    	// Select Sub Menu (Lingaraj Desai, 04/06/25)
    	private void ClickSubMenuKO(WebElement eleMenu, String subMenuText) {
    		try {
    			
    			List<WebElement> subMenus = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".react-switch-selector-option-label")));

    			for (WebElement subMenu : subMenus) {
    				if (subMenu.getText().trim().equalsIgnoreCase(subMenuText.trim())) {
    					subMenu.click();
    					break;
    				}
    			}
    		} catch (Exception e) {
    			
    		}
    	}
	}
