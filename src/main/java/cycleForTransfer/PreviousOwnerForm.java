package cycleForTransfer;

import entities.Company;
import core.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PreviousOwnerForm extends TransferWarrantyPage {
    public final String COUNTRY_POLAND = "PL";
    public By countryLabelID = By.id("ddlLocation");

    @FindBy(id = "txtCompanyName")
    public WebElement companyName;
    public By companyNameID = By.id("txtCompanyName");

    @FindBy(id = "txtZipcode")
    public WebElement companyZipCode;
    public By companyZipCodeID = By.id("txtZipcode");

    @FindBy(id = "retailOT_spanContinueButton")
    public WebElement submitButton;
    public By submitButtonID = By.id("retailOT_spanContinueButton");

    public PreviousOwnerForm(SeleniumDriver driver) {
        super(driver);
    }

    public void fillForm(Company company) {
        driver.waitForElementVisibility(companyNameID);
        companyName.sendKeys(company.getName());
        driver.waitForElementVisibility(companyZipCodeID);
        companyZipCode.sendKeys(company.getZipCode());
        driver.waitForElementVisibility(submitButtonID);
        submitButton.click();
    }

    public boolean tagIsAlreadyTransferred() {
        driver.waitForElementVisibility(countryLabelID);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String option = (String)((JavascriptExecutor) driver.getDriver()).executeScript("return document.getElementById('ddlLocation').value");
        return option.contains(COUNTRY_POLAND);
    }

}


