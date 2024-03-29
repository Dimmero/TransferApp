package core;

import java.io.*;
import java.util.List;

import cycleForTransfer.PreviousOwnerForm;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import cycleForStats.WarrantyInfoPage;

public class OutputToExcel {
    private final HSSFWorkbook WORKBOOK;
    private FileOutputStream OUTPUT_STREAM;
    private final HSSFSheet SHEET;
    private String company;

    public OutputToExcel(String company)  {
        WORKBOOK = new HSSFWorkbook();
        int randNum = (int) (Math.random() * (100 - 1 + 1) + 1);
        try {
            OUTPUT_STREAM = new FileOutputStream("/Users/dimaakulicz/Desktop/Desktop - Dima’s MacBook Pro/Laptok/" + company + randNum + ".xlsx");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        SHEET = WORKBOOK.createSheet("Tags");
    }

    public void generateExcelWithTags(List<String> list) {
        for (int index = 0; index < list.size(); index++) {
            HSSFRow row = SHEET.createRow(index);
            row.createCell(0).setCellValue(list.get(index));
        }
        writeToFile();
    }

    public void getStatisticsTransfer(int rowCount, String tag, String country){
        if (rowCount == 0) {
            HSSFRow rowForFiltering = SHEET.createRow(0);
            rowForFiltering.createCell(0).setCellValue("Service tag:");
            rowForFiltering.createCell(1).setCellValue("Country:");
        }
            HSSFRow row = SHEET.createRow(rowCount + 1);
            row.createCell(0).setCellValue(tag);
            row.createCell(1).setCellValue(country);
    }

    public void getStatistics(int rowCount, String tag, WarrantyInfoPage warrantyInfoPage){
        if (rowCount == 0) {
            HSSFRow rowForFiltering = SHEET.createRow(0);
            rowForFiltering.createCell(0).setCellValue("Service tag:");
            rowForFiltering.createCell(1).setCellValue("Country:");
            rowForFiltering.createCell(2).setCellValue("Warranty expires:");
        }
        HSSFRow row = SHEET.createRow(rowCount + 1);
        row.createCell(0).setCellValue(tag);
        if (warrantyInfoPage.checkIfIssue()){
            row.createCell(1).setCellValue(warrantyInfoPage.INLINE_ISSUE);
            row.createCell(2).setCellValue(warrantyInfoPage.INLINE_ISSUE);
        } else {
            row.createCell(1).setCellValue(warrantyInfoPage.getCountryLabel());
            row.createCell(2).setCellValue(warrantyInfoPage.getWarrantyExpiresInfo());
        }
    }

    public void writeToFile() {
        try {
            WORKBOOK.write(OUTPUT_STREAM);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            WORKBOOK.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            OUTPUT_STREAM.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
