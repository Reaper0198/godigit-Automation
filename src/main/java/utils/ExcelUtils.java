package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.*;

public class ExcelUtils {
    private static XSSFWorkbook workbook = new XSSFWorkbook();
    private static XSSFSheet sheet = workbook.createSheet("Travel plans");
    private static FileOutputStream out;

    public static void writeHeader() throws FileNotFoundException {
    	out = new FileOutputStream(new File("C:/Users/2407391/eclipse-workspace/modularFramework/ExcelReports/TravelReport.xlsx"));
        XSSFRow titleRow = sheet.createRow(0);
        titleRow.createCell(0).setCellValue("Plan Name");
        titleRow.createCell(1).setCellValue("Plan Price");
    }

    public static void writePlan(String planName, String planPrice, int rowNum) {
        XSSFRow row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue(planName);
        row.createCell(1).setCellValue(planPrice);
    }

    public static void saveToFile() throws IOException {
        workbook.write(out);
    }
    
    public static void workbookClose() throws IOException {
    	out.close();
    	workbook.close();
    }
}