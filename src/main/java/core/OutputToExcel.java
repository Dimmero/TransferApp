package core;

import java.io.*;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import pages.WarrantyInfoPage;

public class OutputToExcel {
    private final HSSFWorkbook WORKBOOK;
    private FileOutputStream OUTPUT_STREAM;
    private final HSSFSheet SHEET;
    private String company;

    public OutputToExcel(String company)  {
        WORKBOOK = new HSSFWorkbook();
        try {
            OUTPUT_STREAM = new FileOutputStream("C:\\Users\\Dimmer\\Desktop\\Warranty(copy)\\" + company + ".xlsx");
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

    public void getStatistics(int rowCount, String tag, WarrantyInfoPage warrantyInfoPage){
            HSSFRow rowForFiltering = SHEET.createRow(0);
            HSSFRow row = SHEET.createRow(rowCount + 1);
            rowForFiltering.createCell(0);
            row.createCell(0).setCellValue(tag);
            row.createCell(1).setCellValue(warrantyInfoPage.getWarrantyInfo());
            row.createCell(2).setCellValue(warrantyInfoPage.getCountryLabel());
    }

    public void createRowAndSetCell(int rowCount, int cell, String data) {
        HSSFRow row = SHEET.createRow(rowCount);
        row.createCell(cell).setCellValue(data);
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
