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

public class Remittance {

    private final WebDriver driver;
    private final List<String> ardata = new ArrayList<>();
    private List<String> arl;

    //  Constructor to accept WebDriver
    public Remittance(WebDriver driver) {
        this.driver = driver;
    }

    //  Wrapper method so FunctionalitySelector can call run()       
    
    public void run_DeposittoPPFSSA() {
        try {
        	DeposittoPPFSSA();
        } catch (Exception e) {
            throw new RuntimeException("Deposit to PPF SSA flow failed", e);
        }
    }
    
    public void run_RDRemittance() {
        try {
        	RDRemittance();
        } catch (Exception e) {
            throw new RuntimeException("RD Remittance flow failed", e);
        }
    }
    
    public void run_BBPS() {
        try {
        	BBPS();
        } catch (Exception e) {
            throw new RuntimeException("BBPS flow failed", e);
        }
    }
    
    public void run_AddBeneficiary() {
        try {
        	AddBeneficiary();
        } catch (Exception e) {
            throw new RuntimeException("Add Beneficiary flow failed", e);
        }
    }
    
    public void run_DeleteBeneficiary() {
        try {
        	DeleteBeneficiary();
        } catch (Exception e) {
            throw new RuntimeException("Delete Beneficiary flow failed", e);
        }
    }
    
    public void run_IMPS() {
        try {
        	IMPS();
        } catch (Exception e) {
            throw new RuntimeException("IMPS flow failed", e);
        }
    }
    
	public void run_NEFT() {
		try {
			NEFT();
		} catch (Exception e) {
			throw new RuntimeException("NEFT flow failed", e);
		}
	}
    
	
	
	
    public void DeposittoPPFSSA() throws InterruptedException, IOException {
        //  Normalize Excel path
        String excelPath = ConfigReader.get("Acquisitiontestdata.path");
        File excelFile = new File(excelPath).getAbsoluteFile();
        System.out.println(" Using Excel file: " + excelFile.getAbsolutePath());

        try (FileInputStream fis = new FileInputStream(excelFile);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet("Depositto_PPF_SSA");
            if (sheet == null) {
                throw new IllegalArgumentException(" Sheet 'Depositto_PPF_SSA' not found in file: " + excelFile.getAbsolutePath());
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
                    if (("Remittance".equalsIgnoreCase(arl.get(5)))) {
                        new CommonFlow(driver).RemittanceFlow();
                    }

                    new CommonElements(driver).AcquisitionService(arl);
                    new Validation(driver).AcuqisitonServiceValidations(arl);
                    new Result(driver).AcquisitionResults(arl);
                }

                System.out.println("=====================================================");
            }
        }

        //  Backup Excel file
        ReportsCreation.Take_TestData_Backup(excelFile.getAbsolutePath());
    }
    
    
    
    
    
    public void AddBeneficiary() throws InterruptedException, IOException {
        //  Normalize Excel path
        String excelPath = ConfigReader.get("Acquisitiontestdata.path");
        File excelFile = new File(excelPath).getAbsoluteFile();
        System.out.println(" Using Excel file: " + excelFile.getAbsolutePath());

        try (FileInputStream fis = new FileInputStream(excelFile);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet("AddBeneficiary");
            if (sheet == null) {
                throw new IllegalArgumentException(" Sheet 'AddBeneficiary' not found in file: " + excelFile.getAbsolutePath());
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
                    if (("Remittance".equalsIgnoreCase(arl.get(5)))) {
                        new CommonFlow(driver).RemittanceFlow();
                    }

                    new CommonElements(driver).AcquisitionService(arl);
                    new Validation(driver).AcuqisitonServiceValidations(arl);
                    new Result(driver).AcquisitionResults(arl);
                }

                System.out.println("=====================================================");
            }
        }

        //  Backup Excel file
        ReportsCreation.Take_TestData_Backup(excelFile.getAbsolutePath());
    }
    
    
    
    public void DeleteBeneficiary() throws InterruptedException, IOException {
        //  Normalize Excel path
        String excelPath = ConfigReader.get("Acquisitiontestdata.path");
        File excelFile = new File(excelPath).getAbsoluteFile();
        System.out.println(" Using Excel file: " + excelFile.getAbsolutePath());

        try (FileInputStream fis = new FileInputStream(excelFile);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet("DeleteBeneficiary");
            if (sheet == null) {
                throw new IllegalArgumentException(" Sheet 'DeleteBeneficiary' not found in file: " + excelFile.getAbsolutePath());
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
                    if (("Remittance".equalsIgnoreCase(arl.get(5)))) {
                        new CommonFlow(driver).RemittanceFlow();
                    }

                    new CommonElements(driver).AcquisitionService(arl);
                    new Validation(driver).AcuqisitonServiceValidations(arl);
                    new Result(driver).AcquisitionResults(arl);
                }

                System.out.println("=====================================================");
            }
        }

        //  Backup Excel file
        ReportsCreation.Take_TestData_Backup(excelFile.getAbsolutePath());
    }
    
    
    
    public void IMPS() throws InterruptedException, IOException {
        //  Normalize Excel path
        String excelPath = ConfigReader.get("Acquisitiontestdata.path");
        File excelFile = new File(excelPath).getAbsoluteFile();
        System.out.println(" Using Excel file: " + excelFile.getAbsolutePath());

        try (FileInputStream fis = new FileInputStream(excelFile);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet("IMPS");
            if (sheet == null) {
                throw new IllegalArgumentException(" Sheet 'IMPS' not found in file: " + excelFile.getAbsolutePath());
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
                    if (("Remittance".equalsIgnoreCase(arl.get(5)))) {
                        new CommonFlow(driver).RemittanceFlow();
                    }

                    new CommonElements(driver).AcquisitionService(arl);
                    new Validation(driver).AcuqisitonServiceValidations(arl);
                    new Result(driver).AcquisitionResults(arl);
                }

                System.out.println("=====================================================");
            }
        }

        //  Backup Excel file
        ReportsCreation.Take_TestData_Backup(excelFile.getAbsolutePath());
    }
    
    
    
    public void NEFT() throws InterruptedException, IOException {
        //  Normalize Excel path
        String excelPath = ConfigReader.get("Acquisitiontestdata.path");
        File excelFile = new File(excelPath).getAbsoluteFile();
        System.out.println(" Using Excel file: " + excelFile.getAbsolutePath());

        try (FileInputStream fis = new FileInputStream(excelFile);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet("NEFT");
            if (sheet == null) {
                throw new IllegalArgumentException(" Sheet 'NEFT' not found in file: " + excelFile.getAbsolutePath());
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
                    if (("Remittance".equalsIgnoreCase(arl.get(5)))) {
                        new CommonFlow(driver).RemittanceFlow();
                    }

                    new CommonElements(driver).AcquisitionService(arl);
                    new Validation(driver).AcuqisitonServiceValidations(arl);
                    new Result(driver).AcquisitionResults(arl);
                }

                System.out.println("=====================================================");
            }
        }

        //  Backup Excel file
        ReportsCreation.Take_TestData_Backup(excelFile.getAbsolutePath());
    }
    
    
    
    public void BBPS() throws InterruptedException, IOException {
        //  Normalize Excel path
        String excelPath = ConfigReader.get("Acquisitiontestdata.path");
        File excelFile = new File(excelPath).getAbsoluteFile();
        System.out.println(" Using Excel file: " + excelFile.getAbsolutePath());

        try (FileInputStream fis = new FileInputStream(excelFile);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet("BBPS");
            if (sheet == null) {
                throw new IllegalArgumentException(" Sheet 'BBPS' not found in file: " + excelFile.getAbsolutePath());
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
                    if (("Remittance".equalsIgnoreCase(arl.get(5)))) {
                        new CommonFlow(driver).RemittanceFlow();
                    }

                    new CommonElements(driver).AcquisitionService(arl);
                    new Validation(driver).AcuqisitonServiceValidations(arl);
                    new Result(driver).AcquisitionResults(arl);
                }

                System.out.println("=====================================================");
            }
        }

        //  Backup Excel file
        ReportsCreation.Take_TestData_Backup(excelFile.getAbsolutePath());
    }
    
    
    
    
    public void RDRemittance() throws InterruptedException, IOException {
        //  Normalize Excel path
        String excelPath = ConfigReader.get("Acquisitiontestdata.path");
        File excelFile = new File(excelPath).getAbsoluteFile();
        System.out.println(" Using Excel file: " + excelFile.getAbsolutePath());

        try (FileInputStream fis = new FileInputStream(excelFile);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet("RDRemittance");
            if (sheet == null) {
                throw new IllegalArgumentException(" Sheet 'RDRemittance' not found in file: " + excelFile.getAbsolutePath());
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
                    if (("Remittance".equalsIgnoreCase(arl.get(5)))) {
                        new CommonFlow(driver).RemittanceFlow();
                    }

                    new CommonElements(driver).AcquisitionService(arl);
                    new Validation(driver).AcuqisitonServiceValidations(arl);
                    new Result(driver).AcquisitionResults(arl);
                }

                System.out.println("=====================================================");
            }
        }

        //  Backup Excel file
        ReportsCreation.Take_TestData_Backup(excelFile.getAbsolutePath());
    }
    
    
    
    
    
}