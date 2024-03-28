package hyperlinks;

import java.io.*;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CreationHelper;
//import org.apache.poi.xssf.usermodel.XSSFFont;
public class Hyperlinks {
    public void addLink(HSSFWorkbook wb, HSSFSheet sheet1, int i, String data, OutputStream src)  {
        HSSFCell cell;

        CreationHelper helper = wb.getCreationHelper();
        HSSFCellStyle linkStyle = wb.createCellStyle();
        HSSFFont linkFont = wb.createFont();

//        linkFont.setUnderline(XSSFFont.U_SINGLE);
//        linkFont.setColor(XSSFFont.COLOR_RED);
//        linkStyle.setFont(linkFont);

        cell = sheet1.getRow(i).createCell(4);
        cell.setCellValue(data);

        File file = new File("C:\\\\Users\\\\Dimmer\\Desktop\\Warranty(copy)\\csvFiles\\" + data + ".csv");

        HSSFHyperlink link = (HSSFHyperlink)helper.createHyperlink(HyperlinkType.FILE);
        String path = "C:\\Users\\Dimmer\\Desktop\\GUI\\Warranty(copy)\\csvFiles\\" + data + ".csv";

        link.setAddress(file.toURI().toString());
        cell.setHyperlink(link);
        cell.setCellStyle(linkStyle);

    }

}
