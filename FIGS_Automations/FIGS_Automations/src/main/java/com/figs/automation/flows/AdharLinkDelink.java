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

public class AdharLinkDelink {

    private final WebDriver driver;
    private final List<String> ardata = new ArrayList<>();
    private List<String> arl;

    // ✅ Constructor to accept WebDriver
    public AdharLinkDelink(WebDriver driver) {
        this.driver = driver;
    }

    // ✅ Wrapper method so FunctionalitySelector can call run()
    public void run() {
        try {
        	 AdharLinkKO();
        } catch (Exception e) {
            throw new RuntimeException(" AadharLink flow failed", e);
        }
    }

    public void  AdharLinkKO() throws InterruptedException, IOException {
        // ✅ Normalize Excel path
        String excelPath = ConfigReader.get("testdata.path");
        File excelFile = new File(excelPath).getAbsoluteFile();
        System.out.println(" Using Excel file: " + excelFile.getAbsolutePath());

        try (FileInputStream fis = new FileInputStream(excelFile);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheet("AadharLink");
            if (sheet == null) {
                throw new IllegalArgumentException(" Sheet 'AadharLink' not found in file: " + excelFile.getAbsolutePath());
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

                if (arl.size() > 10 && "Y".equalsIgnoreCase(arl.get(10))) {
                    if (("Ease Banking".equalsIgnoreCase(arl.get(6)))) {
                        new CommonFlow(driver).EaseBankingFlow();
                    }
                
//                if (arl.size() > 10 && "Y".equalsIgnoreCase(arl.get(10)))
                {
                    new CommonElements(driver).AcctServiceElements(arl);
                    new Validation(driver).AcctServiceValidations(arl);
                    new Result(driver).AcctServiceResults(arl);
                }

                System.out.println("=====================================================");
            }
        }

        // ✅ Backup Excel file
        ReportsCreation.Take_TestData_Backup(excelFile.getAbsolutePath());
    }
  }
}
