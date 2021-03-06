package cycleForStats;

import core.OutputToExcel;
import core.SeleniumDriver;
import cycleForTransfer.Cookies;
import org.openqa.selenium.By;

import java.util.ArrayList;

public class CycleForStats {
    public final String URL_STATS = "https://www.dell.com/support/home/pl-pl?app=products";
    public SeleniumDriver driver;
    private final OutputToExcel outputToExcel;
    private final DellLoginPage dellLoginPage;
    private final WarrantyInfoPage warrantyInfoPage;
    private final Cookies cookies;

    public CycleForStats(String company) {
        this.driver = new SeleniumDriver();
        driver.initDriver();
        this.dellLoginPage = new DellLoginPage(driver);
        this.outputToExcel = new OutputToExcel(company);
        this.warrantyInfoPage = new WarrantyInfoPage(driver);
        this.cookies = new Cookies(driver);
    }

    public SeleniumDriver getDriver() {
        return driver;
    }

    public void getCycleForStatistics(ArrayList<String> list) {
        list.forEach(tag -> {
            driver.openNewTab(URL_STATS);
            dellLoginPage.passServiceTagAndGoToTheNextPage(tag);
            if (list.indexOf(tag) == 0) {
            cookies.turnOffCookies();
            }
            outputToExcel.getStatistics(list.indexOf(tag), tag, warrantyInfoPage);
            driver.closeDriver();
        });
        outputToExcel.writeToFile();
        driver.quitDriver();
    }
}


//Model name output
//                baseElements.core.SeleniumDriver.waitForElement(By.xpath("//div[contains(@class,'product-info')]//h1"));
//                String modelName = baseElements.core.SeleniumDriver.driver.findElement(By.xpath("//div[contains(@class,'product-info')]//h1")).getText();
//                sheet1.getRow(i).createCell(3).setCellValue(modelName);
//
//                Download serviceTag.csv
//                String cssInfoProcessor = "html body#top div#site-wrapper.site-wrapper.supportpage-initialized div.site-canvas.site-canvas-mob.mh-sim-canvas div.site-canvas-mob.min-height-body div div.ips-tab-main-container.tab-overview-bg-gray div.tab-content div#tab_overview.tab-pane.active div.tab-overview-bg-gray div#overview-tag.container.ov-container.pt-10.pt-md-12.border-0 div.row div#quickLinkContainer.col-lg-3.col-xs-12 div.col-md-12.card.col-xs-12.mb-7.px-6 div#overview-quicklink-1.text-lg-left.text-center.pb-3 a#quicklink-sysconfig";
//                WebElement configButton = baseElements.core.SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssInfoProcessor)));
//                configButton.click();
//                WebElement download = baseElements.core.SeleniumDriver.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("current-config-export")));
//                download.click();
//
////                hyperlinks.Hyperlinks
//                hyperlinks = new hyperlinks.Hyperlinks();
//                hyperlinks.addLink(wb, sheet1, i, data0, src);
////                Reading data from file
//                Thread.sleep(300);
//                File fileCSV = new File("C:\\Users\\Dimmer\\Desktop\\Warranty(copy)\\csvFiles\\" + data0 + ".csv");
//                try {
//                    BufferedReader csvReader = new BufferedReader(new FileReader(fileCSV));
//                    String row;
//                    while ((row = csvReader.readLine()) != null) {
//                        Pattern pattern = Pattern.compile("(i\\d-.+?)[, ]", Pattern.CASE_INSENSITIVE);
//                        Matcher matcher = pattern.matcher(row);
//                        boolean found = matcher.find();
//                        if (found) {
//                            sheet1.getRow(i).createCell(5).setCellValue(matcher.group(1));
//                        }
//                    }
//                    csvReader.close();
//                } catch (Exception e) {
//                    System.out.println("Fuck");
//                }
//                String currentURL = baseElements.core.SeleniumDriver.driver.getCurrentUrl();
//                hyperlinkURL = new hyperlinks.HyperlinkURL();
//                hyperlinkURL.addLinkURL(wb, sheet1, i, data0, currentURL);
//            }
//        } catch (Exception e) {
//            System.out.println("Something went wrong");
//        }
//    }




