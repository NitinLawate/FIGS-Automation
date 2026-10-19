
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
    	

    	public  void ClickLoanDeposit()
    	{
    		try {
    			  

    			WebElement screen = driver.findElement(By.xpath("(//a[starts-with(normalize-space(text()), 'Loan Deposit')][1])[1]"));
    			
    			boolean loandpst=screen.isDisplayed();
    			if(loandpst)
    			{
    			//WebElement Menu1 = driver.findElement(By.xpath("//a[starts-with(normalize-space(text()), 'Loan Deposit')][1]"));
    			//Menu1.click();
    				 screen.click();
    			}
    			Thread.sleep(400);

    			WebElement SubMenu = driver.findElement(By.xpath("(//a[normalize-space(text())='Loan Deposit'])[2]"));
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
    			  

    			WebElement screen = driver.findElement(By.xpath("(//a[starts-with(normalize-space(text()), 'Loan Deposit')][1])[1]"));
    			
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
    		}
    		catch (Exception e) {
    			e.getMessage();
    		}
    	}
    	
    	public void EaseBankingFlow()
    	{
    		try
    		{
    			WebElement easebanking = driver.findElement(By.xpath("//a[@role='button' and text()='Ease Banking']"));
    			easebanking.click();
    			boolean atm = driver.findElement(By.xpath("//a[normalize-space(text())='ATM Card Block Service']")).isDisplayed();
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
