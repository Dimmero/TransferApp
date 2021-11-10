package hyperlinks;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CreationHelper;

public class HyperlinkURL {
    public void addLinkURL(HSSFWorkbook wb, HSSFSheet sheet1, int i, String data, String url) {
        HSSFCell cell;

        CreationHelper helper = wb.getCreationHelper();
        HSSFCellStyle linkStyle = wb.createCellStyle();
        HSSFFont linkFont = wb.createFont();

        linkFont.setUnderline(HSSFFont.U_SINGLE);
        linkFont.setColor(HSSFFont.COLOR_RED);
        linkStyle.setFont(linkFont);

        cell = sheet1.getRow(i).createCell(6);
        cell.setCellValue(data);
        HSSFHyperlink link = (HSSFHyperlink) helper.createHyperlink(HyperlinkType.URL);

        link.setAddress(url);
        cell.setHyperlink(link);
        cell.setCellStyle(linkStyle);

    }
}
