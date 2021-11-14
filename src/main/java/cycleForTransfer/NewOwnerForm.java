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

    @FindBy(id = "txtNewOwnerCompanyName")
    public WebElement newOwnerName;
    public By newOwnerNameID = By.id("txtNewOwnerCompanyName");

    @FindBy(id = "txtNewOwnerEmail")
    public WebElement newOwnerEmail;
    public By newOwnerEmailID = By.id("txtNewOwnerEmail");

    @FindBy(id = "txtNewOwnerStreetAddress1")
    public WebElement newOwnerAddress;
    public By newOwnerAddressID = By.id("txtNewOwnerStreetAddress1");

    @FindBy(id = "txtNewOwnerCity")
    public WebElement newOwnerCity;
    public By newOwnerCityID = By.id("txtNewOwnerCity");

    @FindBy(id = "txtNewOwnerZipCode")
    public WebElement newOwnerZipCode;
    public By newOwnerZipCodeID = By.id("txtNewOwnerZipCode");

    @FindBy(id = "txtNewOwnerAreaCode")
    public WebElement newOwnerAreaCode;
    public By newOwnerAreaCodeID = By.id("txtNewOwnerAreaCode");

    @FindBy(id = "txtNewOwnerPhone")
    public WebElement newOwnerTelNumber;
    public By newOwnerTelNumberID = By.id("txtNewOwnerPhone");

    @FindBy(id = "retailOT_btnContinue")
    public WebElement submitNewOwner;
    public By submitNewOwnerID = By.id("retailOT_btnContinue");

    @FindBy(id = "btnNewOwnerModelContinue")
    public WebElement continueForm;
    public By continueFormID = By.id("btnNewOwnerModelContinue");

    @FindBy(css = "div.custom-control:nth-child(3) > label:nth-child(2)")
    public WebElement submitCheckbox;
    public By submitCheckboxCSS = By.cssSelector("div.custom-control:nth-child(3) > label:nth-child(2)");

    @FindBy(id = "retailOT_spanSubmitButton")
    public WebElement submitButton;
    public By submitButtonID = By.id("retailOT_spanSubmitButton");

    public NewOwnerForm(SeleniumDriver driver) {
        super(driver);
    }

    public void fillForm(Company company) {
        driver.waitForSelect();
        select(selectUsage, USAGE);
        driver.waitForElementVisibility(newOwnerNameID);
        newOwnerName.sendKeys(company.getName());
        driver.waitForElementVisibility(newOwnerEmailID);
        newOwnerEmail.sendKeys(company.getEmail());
        driver.waitForElementVisibility(selectCountryID);
        select(selectCountry, company.getCountry());
        driver.waitForElementVisibility(newOwnerAddressID);
        newOwnerAddress.sendKeys(company.getAddress());
        driver.waitForElementVisibility(newOwnerCityID);
        newOwnerCity.sendKeys(company.getCity());
        driver.waitForElementVisibility(selectStateID);
        select(selectState, company.getState());
        driver.waitForElementVisibility(newOwnerZipCodeID);
        newOwnerZipCode.sendKeys(company.getZipCode());
        driver.waitForElementVisibility(newOwnerAreaCodeID);
        newOwnerAreaCode.sendKeys(company.getPrefixNumber());
        driver.waitForElementVisibility(newOwnerTelNumberID);
        newOwnerTelNumber.sendKeys(company.getTelNumber());
        submitForm();
    }

    private void select(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void submitForm() {
        driver.waitForElementVisibility(submitNewOwnerID);
        submitNewOwner.click();
        driver.waitForElementVisibility(continueFormID);
        continueForm.click();
        driver.waitForElementVisibility(submitCheckboxCSS);
        submitCheckbox.click();
        driver.waitForElementVisibility(submitButtonID);
        submitButton.click();
    }
}



