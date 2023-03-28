package cycleForStats;

import BaseElements.BaseAbstractPage;
import core.OutputToExcel;
import core.SeleniumDriver;
import cycleForTransfer.Cookies;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.ArrayList;

public class CycleForStats extends BaseAbstractPage {
    private final OutputToExcel outputToExcel;
    private final DellLoginPage dellLoginPage;
    private final WarrantyInfoPage warrantyInfoPage;
    public Boolean stats = true;

    public CycleForStats(String company) {
        this.dellLoginPage = new DellLoginPage();
        this.outputToExcel = new OutputToExcel(company);
        this.warrantyInfoPage = new WarrantyInfoPage();
    }

    public SeleniumDriver getDriver() {
        return driver;
    }

    public void getCycleForStatistics(ArrayList<String> list) {
        list.forEach(tag -> {
            while (stats) {
                driver.openNewTab(URL_STATS, 1);
                dellLoginPage.passServiceTagAndGoToTheNextPage(tag);
                driver.sleepForSomeTime(2000);
                if (driver.getDriver().getCurrentUrl().length() < 62) {
                    dellLoginPage.waitAndClickSearch();
                }
                stats = false;
            }
            outputToExcel.getStatistics(list.indexOf(tag), tag, warrantyInfoPage);
            driver.closeTabForStats();
            stats = true;
        });
        outputToExcel.writeToFile();
        driver.quitDriver();
    }
}




