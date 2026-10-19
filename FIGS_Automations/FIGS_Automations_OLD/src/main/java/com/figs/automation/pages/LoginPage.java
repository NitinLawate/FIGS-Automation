package com.figs.automation.pages;

import com.figs.automation.utils.ConfigReader;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import javax.swing.*; // for fallback popup
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.Base64;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By userId   = By.xpath("//input[@placeholder='Enter User ID' or @name='username' or @id='username']");
    private final By password = By.xpath("//input[@placeholder='Enter Password' or @name='password' or @id='password']");
    private final By captchaInput = By.xpath("//input[contains(@placeholder,'Captcha') or contains(@id,'captcha') or contains(@name,'captcha')]");
    private final By captchaImg   = By.xpath("//img[contains(@src,'data:image/png')]");
    private final By loginBtn     = By.xpath("//button[@type='button'][@class='proceedbutton btn btn-contained']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login() {
        try {
            type(userId, ConfigReader.get("admin.username"));
            type(password, ConfigReader.get("admin.password"));

            String captchaText = null;
            int retryCount = 3;

            /*for (int i = 0; i < retryCount; i++) {
                captchaText = readCaptcha();
                System.out.println("🔎 Attempt " + (i + 1) + " OCR Captcha result: [" + captchaText + "]");

                if (captchaText != null && !captchaText.trim().isEmpty()) {
                    break;  // Captcha read successfully
                }

                // Optional: small delay before retry
                Thread.sleep(1000);
            }*/

            if (captchaText == null || captchaText.trim().isEmpty()) {
                // Fallback to manual entry if OCR consistently fails
                captchaText = JOptionPane.showInputDialog("❗ OCR failed. Please enter captcha manually:");
            }

            type(captchaInput, captchaText);
            click(loginBtn);

        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to login due to captcha/OCR issue", e);
        }
    }


    private String readCaptcha() throws Exception {
        String src = wait.until(ExpectedConditions.visibilityOfElementLocated(captchaImg)).getAttribute("src");
        if (src == null || !src.contains(",")) {
            return "";
        }

        String base64 = src.split(",")[1];
        byte[] decoded = Base64.getDecoder().decode(base64);
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(decoded));

        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("tessdata"); // make sure this path contains eng.traineddata
        tesseract.setLanguage("eng");

        String result = tesseract.doOCR(img);
        return result.replaceAll("[^a-zA-Z0-9]", ""); // cleanup OCR noise
    }

    private void type(By locator, String text) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        el.click();   // ensure focus
        el.clear();
        el.sendKeys(text);
    }

    private void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
}
