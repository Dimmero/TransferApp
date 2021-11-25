package cycleForTransfer;

import entities.Company;
import core.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PreviousOwnerForm extends TransferWarrantyPage {
    public final String COUNTRY_POLAND = "PL";
    private final By countryLabelID = By.id("ddlLocation");
    private final By companyNameID = By.id("txtCompanyName");
    private final By companyZipCodeID = By.id("txtZipcode");
    private final By submitButtonID = By.id("retailOT_spanContinueButton");

    public PreviousOwnerForm(SeleniumDriver driver) {
        super(driver);
    }

    public void fillForm(Company company) {
        driver.waitForElementAndSendKeys(companyNameID, company.getName());
        driver.waitForElementAndSendKeys(companyZipCodeID, company.getZipCode());
        driver.waitForElementAndClick(submitButtonID);
    }

    public boolean tagIsAlreadyTransferred() {
        driver.waitForElementVisibility(countryLabelID);
        driver.waitForCountryCheck();
        String option = (String)((JavascriptExecutor) driver.getDriver()).executeScript("return document.getElementById('ddlLocation').value");
        return option.contains(COUNTRY_POLAND);
    }

}


