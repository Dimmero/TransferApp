package cycleForTransfer;

import core.SeleniumDriver;
import entities.Company;

import java.util.ArrayList;

public class CycleForTransfer {
    private final String URL_TRANSFER = "https://www.dell.com/support/assets-transfer/pl-pl";
    private SeleniumDriver driver;
    private TransferWarrantyPage transferWarrantyPage;
    private PreviousOwnerForm previousOwnerForm;
    private NewOwnerForm newOwnerForm;
    private Cookies cookies;

    public CycleForTransfer() {
        this.driver = new SeleniumDriver();
        driver.initDriver();
        this.transferWarrantyPage = new TransferWarrantyPage(driver);
        this.previousOwnerForm = new PreviousOwnerForm(driver);
        this.newOwnerForm = new NewOwnerForm(driver);
        this.cookies = new Cookies(driver);
    }

    public SeleniumDriver getDriver() {
        return driver;
    }

    public void getCycle(ArrayList<String> list, Company fromCompany, Company toCompany) {
        list.forEach(tag -> {
            driver.openNewTab(URL_TRANSFER);
            if (list.indexOf(tag) == 0) {
                cookies.turnOffCookies();
            }
            transferWarrantyPage.passServiceTagAndGoToTheNextPage(tag);
            if (!previousOwnerForm.tagIsAlreadyTransferred()) {
                previousOwnerForm.fillForm(fromCompany);
                newOwnerForm.fillForm(toCompany);
            }
            driver.closeDriver();
        });
        driver.quitDriver();
    }
}
