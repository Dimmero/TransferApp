package cycleForTransfer;

import entities.Company;
import core.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class NewOwnerForm extends TransferWarrantyPage {
    public final String USAGE = "2_Office";

    @FindBy(id = "ddlIntendedUse")
    public WebElement selectUsage;
    public By selectUsageID = By.id("ddlIntendedUse");

    @FindBy(id = "ddlNewOwnerLocation")
    public WebElement selectCountry;
    public By selectCountryID = By.id("ddlNewOwnerLocation");

    @FindBy(id = "ddlNewOwnerState")
    public WebElement selectState;
    public final By selectStateID = By.id("ddlNewOwnerState");

    public By newOwnerNameID = By.id("txtNewOwnerCompanyName");
    public By newOwnerEmailID = By.id("txtNewOwnerEmail");
    public By newOwnerAddressID = By.id("txtNewOwnerStreetAddress1");
    public By newOwnerCityID = By.id("txtNewOwnerCity");
    public By newOwnerZipCodeID = By.id("txtNewOwnerZipCode");
    public By newOwnerAreaCodeID = By.id("txtNewOwnerAreaCode");
    public By newOwnerTelNumberID = By.id("txtNewOwnerPhone");
    public By submitNewOwnerID = By.id("retailOT_btnContinue");
    public By continueFormID = By.id("btnNewOwnerModelContinue");
    public By submitCheckboxCSS = By.cssSelector("div.custom-control:nth-child(3) > label:nth-child(2)");
    public By submitButtonID = By.id("retailOT_spanSubmitButton");

    public NewOwnerForm(SeleniumDriver driver) {
        super(driver);
    }

    public void fillForm(Company company) {
        driver.waitForSelect();
        select(selectUsageID, selectUsage, USAGE);
        driver.waitForElementAndSendKeys(newOwnerNameID, company.getName());
        driver.waitForElementAndSendKeys(newOwnerEmailID, company.getEmail());
        select(selectCountryID, selectCountry, company.getCountry());
        driver.waitForElementAndSendKeys(newOwnerAddressID, company.getAddress());
        driver.waitForElementAndSendKeys(newOwnerCityID, company.getCity());
        select(selectStateID, selectState, company.getState());
        driver.waitForElementAndSendKeys(newOwnerZipCodeID, company.getZipCode());
        driver.waitForElementAndSendKeys(newOwnerAreaCodeID, company.getPrefixNumber());
        driver.waitForElementAndSendKeys(newOwnerTelNumberID, company.getTelNumber());
        submitForm();
    }

    private void select(By locator, WebElement element, String value) {
        driver.waitForElementVisibility(locator);
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void submitForm() {
        driver.waitForElementAndClick(submitNewOwnerID);
        driver.waitForElementAndClick(continueFormID);
        driver.waitForElementAndClick(submitCheckboxCSS);
        driver.waitForElementAndClick(submitButtonID);
    }
}



