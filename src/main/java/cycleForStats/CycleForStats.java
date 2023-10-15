package cycleForStats;

import baseElements.BaseAbstractPage;
import core.OutputToExcel;
import core.SeleniumDriver;
import cycleForTransfer.Cookies;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.ArrayList;

import static core.SeleniumDriver.tabs;

public class CycleForStats extends BaseAbstractPage  {

    private final OutputToExcel OUTPUT_TO_EXCEL;
    private final DellLoginPage DELL_LOGIN_PAGE;
    private final WarrantyInfoPage WARRANTY_INFO_PAGE;
    private Cookies cookies;
    private boolean cookiesOff = true;

    public CycleForStats(String company) {
        driver = new SeleniumDriver();
        this.DELL_LOGIN_PAGE = new DellLoginPage();
        this.OUTPUT_TO_EXCEL = new OutputToExcel(company);
        this.WARRANTY_INFO_PAGE = new WarrantyInfoPage();
        this.cookies = new Cookies();
    }

    public void getCycleForStatistics(ArrayList<String> listOfTags) {
        String[] urls = {URL_STATS};
        driver.openTabsWithUrl(urls);
        listOfTags.forEach(tag -> {
            try {
                provideTagsUntilAccessPositive(listOfTags);
                OUTPUT_TO_EXCEL.getStatistics(listOfTags.indexOf(tag), tag, WARRANTY_INFO_PAGE);
            } catch (Exception e) {
                System.out.println("Something went wrong with " + tag);
            }
        });
        OUTPUT_TO_EXCEL.writeToFile();
        driver.quitDriver();
    }

    private void provideTagsUntilAccessPositive(ArrayList<String> listOfTags) {
        for (String tag : listOfTags) {
            if (provideTagStats(tag) == 1) {
                break;
            }
        }
    }

    public int provideTagStats(String tag) {
        driver.getDriver().switchTo().window(tabs.get(1));
        driver.getDriver().get(URL_STATS);
        if (cookiesOff) {
            cookies.turnOffCookies();
            cookiesOff = false;
        }
        DELL_LOGIN_PAGE.inputServiceTag.clear();
        DELL_LOGIN_PAGE.passServiceTagAndGoToTheNextPage(tag);
        try {
            driver.getShortWait10().pollingEvery(Duration.ofMillis(500)).until(ExpectedConditions.urlContains("product-support"));
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }
}




