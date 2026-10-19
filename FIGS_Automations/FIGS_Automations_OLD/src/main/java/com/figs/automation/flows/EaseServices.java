package com.figs.automation.flows;

import com.figs.automation.runners.CommonElements;
import com.figs.automation.runners.CommonFlow;
import com.figs.automation.runners.Result;
import com.figs.automation.runners.Validation;
import com.figs.automation.utils.ConfigReader;
import com.figs.automation.utils.ReportsCreation;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EaseServices {

    private final WebDriver driver;
    private final List<String> ardata = new ArrayList<>();
    private List<String> arl;

    // ✅ Constructor to accept WebDriver
    public EaseServices(WebDriver driver) {
        this.driver = driver;
    }

    // ✅ Wrapper method so FunctionalitySelector can call run()
    public void run_ATM() {
        try {
        	ATMCardBlock();
        } catch (Exception e) {
            throw new RuntimeException("❌ MoneyTrasnfer flow failed", e);
        }
    }
    public void run_ChequeBookRequest() {
        try {
        	ChequeBookRequest();
        } catch (Exception e) {
            throw new RuntimeException("❌ MoneyTrasnfer flow failed", e);
        }
    }
    
    public void ATMCardBlock() throws InterruptedException, IOException {
        // ✅ Normalize Excel path
        String excelPath = ConfigReader.get("Acquisitiontestdata.path");
        File excelFile = new File(excelPath).getAbsoluteFile();
        System.out.println("✅ Using Excel file: " + excelFile.getAbsolutePath());

        try (FileInputStream fis = new FileInputStream(excelFile);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet("ATMCard_Block");
            if (sheet == null) {
                throw new IllegalArgumentException("❌ Sheet 'ATMCard_Block' not found in file: " + excelFile.getAbsolutePath());
            }

            for (int rowindx = 2; rowindx <= sheet.getLastRowNum(); rowindx++) {
                XSSFRow rtc = sheet.getRow(rowindx);
                if (rtc == null) continue;

                ardata.clear();
                for (int colindx = 0; colindx < rtc.getLastCellNum(); colindx++) {
                    String cv = rtc.getCell(colindx).getStringCellValue();
                    ardata.add(cv);
                }

                arl = new ArrayList<>(ardata);
                System.out.println(arl);

                if (arl.size() > 10 && "Y".equalsIgnoreCase(arl.get(9))) {
                    if (("Ease Banking".equalsIgnoreCase(arl.get(5)))) {
                        new CommonFlow(driver).EaseBankingFlow();
                    }

                    new CommonElements(driver).AcquisitionService(arl);
                    new Validation(driver).AcuqisitonServiceValidations(arl);
                    new Result(driver).AcquisitionResults(arl);
                }

                System.out.println("=====================================================");
            }
        }

        // ✅ Backup Excel file
        ReportsCreation.Take_TestData_Backup(excelFile.getAbsolutePath());
    }
    
    public void ChequeBookRequest() throws InterruptedException, IOException {
        // ✅ Normalize Excel path
        String excelPath = ConfigReader.get("Acquisitiontestdata.path");
        File excelFile = new File(excelPath).getAbsoluteFile();
        System.out.println("✅ Using Excel file: " + excelFile.getAbsolutePath());

        try (FileInputStream fis = new FileInputStream(excelFile);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet("ChequeBook");
            if (sheet == null) {
                throw new IllegalArgumentException("❌ Sheet 'ChequeBook' not found in file: " + excelFile.getAbsolutePath());
            }

            for (int rowindx = 2; rowindx <= sheet.getLastRowNum(); rowindx++) {
                XSSFRow rtc = sheet.getRow(rowindx);
                if (rtc == null) continue;

                ardata.clear();
                for (int colindx = 0; colindx < rtc.getLastCellNum(); colindx++) {
                    String cv = rtc.getCell(colindx).getStringCellValue();
                    ardata.add(cv);
                }

                arl = new ArrayList<>(ardata);
                System.out.println(arl);

                if (arl.size() > 10 && "Y".equalsIgnoreCase(arl.get(9))) {
                    if (("Ease Banking".equalsIgnoreCase(arl.get(5)))) {
                        new CommonFlow(driver).EaseBankingFlow();
                    }

                    new CommonElements(driver).AcquisitionService(arl);
                    new Validation(driver).AcuqisitonServiceValidations(arl);
                    new Result(driver).AcquisitionResults(arl);
                }

                System.out.println("=====================================================");
            }
        }

        // ✅ Backup Excel file
        ReportsCreation.Take_TestData_Backup(excelFile.getAbsolutePath());
    }
    
    
}
