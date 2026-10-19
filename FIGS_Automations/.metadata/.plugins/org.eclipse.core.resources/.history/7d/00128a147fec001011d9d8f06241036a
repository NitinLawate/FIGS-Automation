package com.figs.automation.pages;

import com.figs.automation.utils.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*; // for manual captcha input

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By userId       = By.xpath("//input[@placeholder='Enter User ID' or @name='username' or @id='username']");
    private final By password     = By.xpath("//input[@placeholder='Enter Password' or @name='password' or @id='password']");
    private final By captchaInput = By.xpath("//input[contains(@placeholder,'Captcha') or contains(@id,'captcha') or contains(@name,'captcha')]");
    private final By loginBtn     = By.xpath("//button[@type='button'][@class='proceedbutton btn btn-contained']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void login() {
        try {
            handleSSLError();
            type(userId, ConfigReader.get("admin.username"));
            type(password, ConfigReader.get("admin.password"));

            //  Removed OCR — always ask user
            String captchaText = JOptionPane.showInputDialog("Please enter captcha shown on screen:");

            type(captchaInput, captchaText);

            //  Ensure login button is visible
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);",
                driver.findElement(loginBtn)
            );
            
            click(loginBtn);
//            handleAllowPopup();
        } catch (Exception e) {
            throw new RuntimeException(" Failed to login", e);
        }
    }

    private void type(By locator, String text) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        el.click();
        el.clear();
        el.sendKeys(text);
    }

    private void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Handle SSL Privacy Error page
    private void handleSSLError() {
        try {
            if (driver.getTitle().contains("Privacy error") ||
                driver.getPageSource().contains("Your connection is not private")) {

                driver.findElement(By.id("details-button")).click(); // Advanced
                driver.findElement(By.id("proceed-link")).click();   // Proceed
            }
        } catch (Exception e) {
            System.out.println(" No SSL warning found, continuing...");
        }
    }
    
    
    
   //  New method: handle Chrome’s “Allow” popup
    
     
    public void handleAllowPopup() {
        try {
            // Wait for Chrome permission popup
            Thread.sleep(5000);
            
            ((JavascriptExecutor) driver).executeScript("window.focus();");
            
            Thread.sleep(1000);
            
            Robot robot = new Robot();

            // TAB twice to move focus to "Allow"
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            Thread.sleep(500);

            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            Thread.sleep(500);

            // Press ENTER
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
