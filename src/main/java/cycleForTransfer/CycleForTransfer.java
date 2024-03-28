package cycleForTransfer;

import baseElements.BaseAbstractPage;
import core.OutputToExcel;
import core.SeleniumDriver;
import cycleForStats.DellLoginPage;
import entities.Company;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.ArrayList;

import static core.SeleniumDriver.tabs;

public class CycleForTransfer extends BaseAbstractPage {
    private final TransferWarrantyPage TRANSFER_WARRANTY_PAGE;
    private final PreviousOwnerForm PREVIOUS_OWNER_PAGE;
    private final NewOwnerForm NEW_OWNER_FORM;
    private final OutputToExcel OUTPUT_TO_EXCEL;
    private final DellLoginPage DELL_LOGIN_PAGE;
    private final Cookies COOKIES;
    private Company previousOwner;
    private Company newOwner;
    private boolean cookies1Off = true;

    public CycleForTransfer(String company, Company previousOwner, Company newOwner, boolean headless) {
        driver = new SeleniumDriver(headless);
        this.COOKIES = new Cookies();
        this.TRANSFER_WARRANTY_PAGE = new TransferWarrantyPage();
        this.PREVIOUS_OWNER_PAGE = new PreviousOwnerForm();
        this.NEW_OWNER_FORM = new NewOwnerForm();
        this.OUTPUT_TO_EXCEL = new OutputToExcel(company);
        this.DELL_LOGIN_PAGE = new DellLoginPage();
        this.previousOwner = previousOwner;
        this.newOwner = newOwner;
    }

    public void runCycle(ArrayList<String> list) throws TimeoutException {
        String[] urls = {URL_TRANSFER, URL_STATS};
        driver.openTabsWithUrl(urls);
        driver.getDriver().switchTo().window(tabs.get(1));
        list.forEach(tag -> {
            try {
                while (TRANSFER_WARRANTY_PAGE.passServiceTagAndGoToTheNextPage(tag) != 1) {
                    provideTagsUntilAccessPositive(list);
                }
                checkIfTransferredAndDoTheRest(list.indexOf(tag), tag, previousOwner, newOwner);
            } catch (Exception e) {
                throw new TimeoutException();
            }
        });
        OUTPUT_TO_EXCEL.writeToFile();
        driver.quitDriver();
    }

    private void checkIfTransferredAndDoTheRest(int tagCounter, String tag, Company fromCompany, Company toCompany) throws Exception {
        String country = PREVIOUS_OWNER_PAGE.grabPreviousOwnerCountryInfo();
        if (country.contains(COUNTRY_POLAND)) {
            writeToFile(tagCounter, tag, country);
        } else {
            fillFormsAndSubmitTransfer(tagCounter, tag, country, fromCompany, toCompany);
        }
    }

    private void writeToFile(int tagCounter, String tag, String country) {
        OUTPUT_TO_EXCEL.getStatisticsTransfer(tagCounter, tag, country);
        driver.getDriver().get(URL_TRANSFER);
        TRANSFER_WARRANTY_PAGE.clearServiceTag();
    }

    private void fillFormsAndSubmitTransfer(int tagCounter, String tag, String country, Company fromCompany, Company toCompany) throws Exception {
        try {
            OUTPUT_TO_EXCEL.getStatisticsTransfer(tagCounter, tag, country);
            PREVIOUS_OWNER_PAGE.fillForm(fromCompany);
            NEW_OWNER_FORM.fillForm(toCompany);
            NEW_OWNER_FORM.submitForm();
        } catch (Exception ignored) {
            throw new Exception("Not transferred " + tag);
        }
    }

    private void provideTagsUntilAccessPositive(ArrayList<String> list) {
        for (String serial : list) {
            if (provideTagStats(serial) == 1) {
                driver.getDriver().switchTo().window(tabs.get(1));
                driver.getDriver().get(URL_TRANSFER);
                TRANSFER_WARRANTY_PAGE.inputServiceTag.clear();
                break;
            }
        }
    }

    private int provideTagStats(String tag) {
        driver.getDriver().switchTo().window(tabs.get(2));
        driver.getDriver().get(URL_STATS);
        if (cookies1Off) {
            COOKIES.turnOffCookies();
            cookies1Off = false;
        }
        driver.getLongWait35().until(ExpectedConditions.elementToBeClickable(DELL_LOGIN_PAGE.inputServiceTag));
        DELL_LOGIN_PAGE.inputServiceTag.clear();
        DELL_LOGIN_PAGE.passServiceTagAndGoToTheNextPage(tag);
        try {
            driver.getLongWait35().pollingEvery(Duration.ofMillis(500)).until(ExpectedConditions.urlContains("product-support"));
        } catch (Exception e) {
            return 1;
        }
        return 0;
    }
}

