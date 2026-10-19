package com.figs.automation.base;

import com.figs.automation.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;
import org.testng.annotations.*;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod 
    public void setUp() {
        // Read values from config.properties
        String browser = ConfigReader.get("browser", "chrome").toLowerCase();
        boolean headless = ConfigReader.getBoolean("headless", false);
        int implicit = ConfigReader.getInt("implicitWait", 10);
        String baseUrl = ConfigReader.get("baseUrl");

        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions fOpts = new FirefoxOptions();
                if (headless) {
                    fOpts.addArguments("--headless=new");
                }
                driver = new FirefoxDriver(fOpts);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions eOpts = new EdgeOptions();
                if (headless) {
                    eOpts.addArguments("--headless=new");
                }
                driver = new EdgeDriver(eOpts);
                break;

            default: // chrome
                WebDriverManager.chromedriver().setup();
                ChromeOptions cOpts = new ChromeOptions();
                if (headless) {
                    cOpts.addArguments("--headless=new");
                }
                cOpts.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu");
                driver = new ChromeDriver(cOpts);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicit));
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
