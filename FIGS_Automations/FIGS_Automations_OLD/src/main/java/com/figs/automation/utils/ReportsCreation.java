package com.figs.automation.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Utility for creating test reports and backups
 */
public class ReportsCreation {

    private static File destFolder; // backup destination

    /**
     * Recursively copy a folder
     */
    public static void copyFolder(File src, File dest) throws IOException {
        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdirs();
            }
            String[] files = src.list();
            if (files != null) {
                for (String file : files) {
                    File srcFile = new File(src, file);
                    File destFile = new File(dest, file);
                    copyFolder(srcFile, destFile);
                }
            }
        } else {
            try (InputStream in = new FileInputStream(src);
                 OutputStream out = new FileOutputStream(dest)) {

                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
            }
        }
    }

    /**
     * Create backup folder for current run
     */
    public static void Take_TestOutput_Backup(String functionName) throws IOException {
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());

        File srcFolder = new File("test-output"); // TestNG output folder
        destFolder = new File("backup/" + timeStamp + "_" + functionName);

        if (!srcFolder.exists()) {
            System.out.println("⚠️ Test-output folder does not exist, skipping backup.");
            return;
        }

        try {
            copyFolder(srcFolder, destFolder);
            System.out.println("✅ Test-output backed up to: " + destFolder.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Backup Excel test data file
     */
    public static void Take_TestData_Backup(String excelPath) throws IOException {
        if (destFolder == null) {
            // If backup folder wasn’t created yet, make one
            destFolder = new File("backup");
            destFolder.mkdirs();
        }
        File srcExcelFile = new File(excelPath);
        if (srcExcelFile.exists()) {
            FileUtils.copyFileToDirectory(srcExcelFile, destFolder);
            System.out.println("✅ Test data backed up to: " + destFolder.getAbsolutePath());
        } else {
            System.out.println("⚠️ Excel file not found: " + excelPath);
        }
    }
}
