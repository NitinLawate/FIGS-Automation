package com.figs.automation.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

/**
 * FingerPrintPage - Handles all fingerprint steps (agent login, customer, proceed).
 */
public class FingerPrintPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By agentFingerprintBtn = By.cssSelector(
        "#root > div.container > div > div > div:nth-child(2) > div > button > img"
    );
    private final By customerFingerprintBtn = By.xpath("//img[@alt='FingerprintFirstNew']");
    private final By proceedBtn = By.xpath("//div[@role='dialog']//button[.//p[normalize-space()='PROCEED']]");

    public FingerPrintPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    /** ✅ Used only after login */
    public void loginFingerprintIfRequired() {
        try {
            WebElement fingerprintButton = wait.until(ExpectedConditions.visibilityOfElementLocated(agentFingerprintBtn));
            JOptionPane.showMessageDialog(null, "👉 Place Agent finger on scanner.");
            fingerprintButton.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.MuiDrawer-paperAnchorLeft")
            ));
            System.out.println("✅ Agent fingerprint authentication done.");
        } catch (TimeoutException e) {
            System.out.println("ℹ️ Agent fingerprint step skipped/Not Done");
        }
    }

    /** ✅ Used inside a transaction flow */
    public void customerFingerprintIfRequired() {
        try {
            WebElement fingerprintButton = wait.until(ExpectedConditions.visibilityOfElementLocated(customerFingerprintBtn));
            JOptionPane.showMessageDialog(null, "👉 Place Customer finger on scanner.");
            fingerprintButton.click();
            System.out.println("✅ Customer fingerprint authentication done.");
        } catch (TimeoutException e) {
            System.out.println("ℹ️ Customer fingerprint step skipped.");
        }
    }

    /** ✅ Used inside a transaction flow (when PROCEED popup appears) */
    public void proceedFingerprintIfRequired() {
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(proceedBtn));

            for (int attempt = 1; attempt <= 3; attempt++) {
                JOptionPane.showMessageDialog(null, "👉 Place Customer finger again (Attempt " + attempt + ")");
                btn.click();

                // Handle optional OK confirmation dialog
                List<WebElement> okButtons = driver.findElements(By.xpath("/html/body/div[5]/div/div/div/div/div[2]/button[2]/p"));
                if (!okButtons.isEmpty() && okButtons.get(0).isDisplayed()) {
                    okButtons.get(0).click();
                }

                Thread.sleep(500);
            }
            System.out.println("✅ Proceed fingerprint flow done.");
        } catch (TimeoutException e) {
            System.out.println("ℹ️ Proceed fingerprint step skipped.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
