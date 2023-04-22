package cycleForTransfer;

import BaseElements.BaseAbstractPage;
import core.OutputToExcel;
import core.SeleniumDriver;
import cycleForStats.DellLoginPage;
import entities.Company;

import java.util.ArrayList;

import static core.SeleniumDriver.tabs;

public class CycleForTransfer extends BaseAbstractPage {
    private final TransferWarrantyPage TRANSFER_WARRANTY_PAGE;
    private final PreviousOwnerForm PREVIOUS_OWNER_PAGE;
    private final NewOwnerForm NEW_OWNER_FORM;
    private final OutputToExcel OUTPUT_TO_EXCEL;
    private final DellLoginPage DELL_LOGIN_PAGE;
    private Company previousOwner;
    private Company newOwner;

    public CycleForTransfer(String company, Company previousOwner, Company newOwner) {
        driver = new SeleniumDriver();
        this.TRANSFER_WARRANTY_PAGE = new TransferWarrantyPage();
        this.PREVIOUS_OWNER_PAGE = new PreviousOwnerForm();
        this.NEW_OWNER_FORM = new NewOwnerForm();
        this.OUTPUT_TO_EXCEL = new OutputToExcel(company);
        this.DELL_LOGIN_PAGE = new DellLoginPage();
        this.previousOwner = previousOwner;
        this.newOwner = newOwner;
    }

    public void getCycle(ArrayList<String> list) {
        driver.openTransferAndStatsTabs();
        driver.getDriver().switchTo().window(tabs.get(1));
        list.forEach(tag -> {
            TRANSFER_WARRANTY_PAGE.passServiceTagAndGoToTheNextPage(tag);
            if (!checkIfOnPreviousOwnerPage()) {
                goThroughValidationAndProvideTag(list, tag);
            }
            checkIfTransferredAndDoTheRest(list.indexOf(tag), tag, previousOwner, newOwner);
        });
        OUTPUT_TO_EXCEL.writeToFile();
        driver.quitDriver();
    }

    private void checkIfTransferredAndDoTheRest(int tagCounter, String tag, Company fromCompany, Company toCompany) {
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

    private void fillFormsAndSubmitTransfer(int tagCounter, String tag, String country, Company fromCompany, Company toCompany) {
        try {
            OUTPUT_TO_EXCEL.getStatisticsTransfer(tagCounter, tag, country);
            PREVIOUS_OWNER_PAGE.fillForm(fromCompany);
            NEW_OWNER_FORM.fillForm(toCompany);
            NEW_OWNER_FORM.submitForm();
        } catch (Exception ignored) {
            System.out.println(tag + " is not transferred. Something went wrong");
        }
    }

    private void goThroughValidationAndProvideTag(ArrayList<String> list, String tag) {
        for (String serial : list) {
            if (DELL_LOGIN_PAGE.provideTagWithValidation(serial) == 1) {
                driver.getDriver().switchTo().window(tabs.get(1));
                TRANSFER_WARRANTY_PAGE.inputServiceTag.clear();
                TRANSFER_WARRANTY_PAGE.passServiceTagAndGoToTheNextPage(tag);
                break;
            } else {
                driver.getDriver().get(URL_STATS);
            }
        }
    }

    private boolean checkIfOnPreviousOwnerPage() {
        return matchFound("^.*(currentowner)$", driver.getDriver().getCurrentUrl());
    }
}

