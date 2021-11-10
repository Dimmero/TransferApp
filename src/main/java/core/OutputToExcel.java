package core;

import java.io.*;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import pages.WarrantyInfoPage;

public class OutputToExcel {
    private final HSSFWorkbook WORKBOOK;
    private final FileOutputStream OUTPUT_STREAM;
    private final HSSFSheet SHEET;
    private String company;

    public OutputToExcel(String company) throws FileNotFoundException {
        WORKBOOK = new HSSFWorkbook();
        OUTPUT_STREAM = new FileOutputStream("C:\\Users\\Dimmer\\Desktop\\Warranty(copy)\\" + company + ".xlsx");
        SHEET = WORKBOOK.createSheet("Tags");
    }

    public void generateExcelWithTags(List<String> list) throws IOException {
        for (int index = 0; index < list.size(); index++) {
            HSSFRow row = SHEET.createRow(index);
            row.createCell(0).setCellValue(list.get(index));
        }
        writeToFile();
    }

    public void getStatistics(int rowCount, String tag, WarrantyInfoPage warrantyInfoPage){
            HSSFRow row = SHEET.createRow(rowCount);
            row.createCell(0).setCellValue(tag);
            row.createCell(1).setCellValue(warrantyInfoPage.getWarrantyInfo());
            row.createCell(2).setCellValue(warrantyInfoPage.getCountryLabel());
    }

    public void createRowAndSetCell(int rowCount, int cell, String data) {
        HSSFRow row = SHEET.createRow(rowCount);
        row.createCell(cell).setCellValue(data);
    }

    public void writeToFile() throws IOException {
        WORKBOOK.write(OUTPUT_STREAM);
        WORKBOOK.close();
        OUTPUT_STREAM.close();
    }

}
