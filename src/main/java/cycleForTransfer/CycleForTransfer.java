package cycleForTransfer;

import BaseElements.BaseAbstractPage;
import core.OutputToExcel;
import core.SeleniumDriver;
import cycleForStats.DellLoginPage;
import entities.Company;

import java.util.ArrayList;

import static core.SeleniumDriver.tabs;

public class CycleForTransfer extends BaseAbstractPage {
    private TransferWarrantyPage transferWarrantyPage;
    private PreviousOwnerForm previousOwnerForm;
    private NewOwnerForm newOwnerForm;
    private final OutputToExcel outputToExcel;
    private DellLoginPage dellLoginPage;
    boolean toggle = true;
    Boolean transfer = true;

    public CycleForTransfer(String company) {
        driver = new SeleniumDriver();
        this.transferWarrantyPage = new TransferWarrantyPage();
        this.previousOwnerForm = new PreviousOwnerForm();
        this.newOwnerForm = new NewOwnerForm();
        this.outputToExcel = new OutputToExcel(company);
        this.dellLoginPage = new DellLoginPage();
    }

    public void getCycle(ArrayList<String> list, Company fromCompany, Company toCompany) {
        list.forEach(tag -> {
            //if modal with validation appears
            if (toggle) {
                toggle = false;
                driver.openNewTab(URL_STATS, 1);
                dellLoginPage.provideTagWithValidation(tag);
            }
            while (transfer) {
                driver.openNewTab(URL_TRANSFER, 2);
                transferWarrantyPage.passServiceTagAndGoToTheNextPage(tag);
                //if doesn't take service tag go to stats tab and provide tag until validation appears
                driver.sleepForSomeTime(2000);
                if (driver.getDriver().getCurrentUrl().length() < 51) {
                    provideTagUntilOk(list);
                } else {
                    transfer = false;
                }
            }
            String country = previousOwnerForm.grabPreviousOwnerCountryInfo();
            if (!country.contains(COUNTRY_POLAND)) {
                outputToExcel.getStatisticsTransfer(list.indexOf(tag), tag, country);
                previousOwnerForm.fillForm(fromCompany);
                newOwnerForm.fillForm(toCompany);
                newOwnerForm.submitForm();
            } else {
                outputToExcel.getStatisticsTransfer(list.indexOf(tag), tag, country);
            }
            driver.getDriver().close();
            driver.getDriver().switchTo().window(tabs.get(1));
            transfer = true;
        });
        outputToExcel.writeToFile();
        driver.quitDriver();
    }

    private void provideTagUntilOk(ArrayList<String> list) {
        dellLoginPage.closeTransferAndStatTab();
        for (String serial : list) {
            dellLoginPage.passServiceTagAndGoToTheNextPage(serial);
            driver.sleepForSomeTime(2000);
            waitForPageLoad();
            if (matchFound("^.*(en-vn)$", driver.getDriver().getCurrentUrl())) {
                dellLoginPage.waitForValidationModal();
                break;
            } else {
                dellLoginPage.closeStatTabAndOpenAgain();
            }
        }
    }
}

