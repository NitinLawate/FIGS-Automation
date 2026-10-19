
package com.figs.automation.runners;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Result {

    private final WebDriver driver;
    private static String[] Arr2 = new String[2]; // [0]=status, [1]=message
    private final String excelPath;
    private final String excelPath2;
    private final String excelPath3;
    public Result(WebDriver driver) {
        this.driver = driver;
        this.excelPath = com.figs.automation.utils.ConfigReader.get("testdata.path");
        this.excelPath2 = com.figs.automation.utils.ConfigReader.get("Acquisitiontestdata.path");
        this.excelPath3 = com.figs.automation.utils.ConfigReader.get("AccountOpening.path");
    }

    /**
     * Write results for Account Service flows
     * Matches Testcase ID (arl.get(0)) instead of using row index directly.
     */
    public void AcctServiceResults(List<String> arl) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        XSSFWorkbook wb = null;

        try {
            fis = new FileInputStream(excelPath);
            wb = new XSSFWorkbook(fis);

            Sheet sheet;

            // pick sheet dynamically from arl.get(1) or arl.get(2)
            String moduleName = arl.size() > 2 ? arl.get(1) : "";
            switch (moduleName) {
                case "Onus_Deposit_AEPS":
                case "Offus_Deposit_AEPS":
                case "Deposit_Account":
                case "Deposit_Card":
                    sheet = wb.getSheet("Deposit");
                    break;
                default:
                    sheet = wb.getSheetAt(0);
            }

            if (sheet == null) {
                System.out.println("⚠️ Sheet not found for module: " + moduleName);
                return;
            }

            // 🔹 Find row by Testcase ID (column 0)
            int rowNum = -1;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Cell cell = row.getCell(0);
                    if (cell != null && cell.getStringCellValue().equalsIgnoreCase(arl.get(0))) {
                        rowNum = i;
                        break;
                    }
                }
            }

            if (rowNum == -1) {
                System.out.println("⚠️ Testcase ID not found in sheet: " + arl.get(0));
                return;
            }

            Row row = sheet.getRow(rowNum);

            // ✅ Column mappings from your Excel
            int colActualResult = 8;
            int colActualStatus = 9;
            //int colExecutionStatus = 10;
            int colFinalStatus = 11;

            // Format timestamp
            String timestamp = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a").format(new Date());

            // Write Actual Result
            Cell actualResultCell = row.getCell(colActualResult);
            if (actualResultCell == null) actualResultCell = row.createCell(colActualResult);
            actualResultCell.setCellValue(Arr2[1]);

            // Write Actual Status with timestamp
            Cell statusCell = row.getCell(colActualStatus);
            if (statusCell == null) statusCell = row.createCell(colActualStatus);
            statusCell.setCellValue(Arr2[0]);

            // Write Final Status (same as status)
            Cell finalStatusCell = row.getCell(colFinalStatus);
            if (finalStatusCell == null) finalStatusCell = row.createCell(colFinalStatus);
            finalStatusCell.setCellValue(Arr2[0]);

            fos = new FileOutputStream(excelPath);
            wb.write(fos);

            System.out.println("✅ Results written successfully for Testcase: " + arl.get(0));

            // clear Arr2
            Arr2[0] = "";
            Arr2[1] = "";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (wb != null) wb.close();
                if (fis != null) fis.close();
                if (fos != null) fos.close();
            } catch (IOException ignored) {}
        }
    }

    /**
     * Write results for Acquisition Service flows
     * Matches Testcase ID (arl.get(0)) instead of using row index directly.
     */
    public void AcquisitionResults(List<String> arl) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        XSSFWorkbook wb = null;

        try {
            fis = new FileInputStream(excelPath2);
            wb = new XSSFWorkbook(fis);

            Sheet sheet;

            // pick sheet dynamically from arl.get(1) or arl.get(2)
            String moduleName = arl.size() > 2 ? arl.get(1) : "";
            switch (moduleName) {
                case "MoneyTransfer_CIF":
                case "MoneyTransfer_ACC":
                    sheet = wb.getSheet("MoneyTransfer");
                    break;
                case "DepositToLoanAccount":
                    sheet = wb.getSheet("Deposit_LoanAccount");
                    break; 
                case "View Loan Data Collection":
                    sheet = wb.getSheet("LoanDataCollection");
                    break;
                case "ATMCardBlock_Card":
                case "ATMCardBlock_ACC":
                    sheet = wb.getSheet("ATMCard_Block");
                    break;   
                case "Cheque Book Payment":
                    sheet = wb.getSheet("ChequeBook");
                    break; 
                case "PMJJBY":
                    sheet = wb.getSheet("SSS_PMJJBY");
                    break;
                case "PMSBY":
                    sheet = wb.getSheet("SSS_PMSBY");
                    break;
                case "APY":
                        sheet = wb.getSheet("SSS_APY");
                        break;
                default:
                    sheet = wb.getSheetAt(0);
            }

            if (sheet == null) {
                System.out.println("⚠️ Sheet not found for module: " + moduleName);
                return;
            }

            // 🔹 Find row by Testcase ID (column 0)
            int rowNum = -1;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Cell cell = row.getCell(0);
                    if (cell != null && cell.getStringCellValue().equalsIgnoreCase(arl.get(0))) {
                        rowNum = i;
                        break;
                    }
                }
            }

            if (rowNum == -1) {
                System.out.println("⚠️ Testcase ID not found in sheet: " + arl.get(0));
                return;
            }

            Row row = sheet.getRow(rowNum);

            // ✅ Column mappings from your Excel
            int colActualResult = 7;
            int colActualStatus = 8;
            //int colExecutionStatus = 9;
            int colFinalStatus = 11;

            // Format timestamp
            String timestamp = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a").format(new Date());

            // Write Actual Result
            Cell actualResultCell = row.getCell(colActualResult);
            if (actualResultCell == null) actualResultCell = row.createCell(colActualResult);
            actualResultCell.setCellValue(Arr2[1]);

            // Write Actual Status with timestamp
            Cell statusCell = row.getCell(colActualStatus);
            if (statusCell == null) statusCell = row.createCell(colActualStatus);
            statusCell.setCellValue(Arr2[0]);
            
            // Write Final Status (same as status)
            Cell finalStatusCell = row.getCell(colFinalStatus);
            if (finalStatusCell == null) finalStatusCell = row.createCell(colFinalStatus);
            finalStatusCell.setCellValue(Arr2[0]);

            fos = new FileOutputStream(excelPath2);
            wb.write(fos);

            System.out.println("✅ Results written successfully for Testcase: " + arl.get(0));

            // clear Arr2
            Arr2[0] = "";
            Arr2[1] = "";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (wb != null) wb.close();
                if (fis != null) fis.close();
                if (fos != null) fos.close();
            } catch (IOException ignored) {}
        }
    }
    
    
    public void AccountOpeningResults(List<String> arl) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        XSSFWorkbook wb = null;

        try {
            fis = new FileInputStream(excelPath3);
            wb = new XSSFWorkbook(fis);

            Sheet sheet;

            // pick sheet dynamically from arl.get(1) or arl.get(2)
            String moduleName = arl.size() > 2 ? arl.get(1) : "";
            switch (moduleName) {
                case "Deposit_Card":
                    sheet = wb.getSheet("Deposit");
                    break;
                default:
                    sheet = wb.getSheetAt(0);
            }

            if (sheet == null) {
                System.out.println("⚠️ Sheet not found for module: " + moduleName);
                return;
            }

            // 🔹 Find row by Testcase ID (column 0)
            int rowNum = -1;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Cell cell = row.getCell(0);
                    if (cell != null && cell.getStringCellValue().equalsIgnoreCase(arl.get(0))) {
                        rowNum = i;
                        break;
                    }
                }
            }

            if (rowNum == -1) {
                System.out.println("⚠️ Testcase ID not found in sheet: " + arl.get(0));
                return;
            }

            Row row = sheet.getRow(rowNum);

            // ✅ Column mappings from your Excel
            int colActualResult = 8;
            int colActualStatus = 9;
            int colFinalStatus = 11;

            // Format timestamp
            String timestamp = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a").format(new Date());

            // Write Actual Result
            Cell actualResultCell = row.getCell(colActualResult);
            if (actualResultCell == null) actualResultCell = row.createCell(colActualResult);
            actualResultCell.setCellValue(Arr2[1]);

            // Write Actual Status with timestamp
            Cell statusCell = row.getCell(colActualStatus);
            if (statusCell == null) statusCell = row.createCell(colActualStatus);
            statusCell.setCellValue(Arr2[0]);

            // Write Final Status (same as status)
            Cell finalStatusCell = row.getCell(colFinalStatus);
            if (finalStatusCell == null) finalStatusCell = row.createCell(colFinalStatus);
            finalStatusCell.setCellValue(Arr2[0]);

            fos = new FileOutputStream(excelPath3);
            wb.write(fos);

            System.out.println("✅ Results written successfully for Testcase: " + arl.get(0));

            // clear Arr2
            Arr2[0] = "";
            Arr2[1] = "";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (wb != null) wb.close();
                if (fis != null) fis.close();
                if (fos != null) fos.close();
            } catch (IOException ignored) {}
        }
    }
    
    public static void setResult(String status, String message) {
        Arr2[0] = status;
        Arr2[1] = message;
    }
}

