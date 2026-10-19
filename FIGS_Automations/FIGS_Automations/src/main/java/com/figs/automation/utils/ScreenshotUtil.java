/*package com.figs.automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static void capture(WebDriver driver, String testName) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File destFile = new File("screenshots/" + testName + "_" + timestamp + ".png");

            destFile.getParentFile().mkdirs(); // create folder if missing
            Files.copy(srcFile.toPath(), destFile.toPath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/
package com.figs.automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static void capture(WebDriver driver, String testName) {
        try {
            // Take screenshot
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Add milliseconds to timestamp to avoid name collisions
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
            File destFile = new File("screenshots/" + testName + "_" + timestamp + ".png");

            // Create folder if it doesn't exist
            destFile.getParentFile().mkdirs();

            // Copy and overwrite if file already exists (as safety)
            Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("✅ Screenshot saved: " + destFile.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

