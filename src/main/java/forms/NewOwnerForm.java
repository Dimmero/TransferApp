package forms;

import entities.Company;
import core.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.TransferWarrantyPage;

public class NewOwnerForm extends TransferWarrantyPage {
    private final String USAGE = "2_Office";

    @FindBy(id = "ddlIntendedUse")
    private WebElement selectUsage;

    @FindBy(id = "ddlNewOwnerLocation")
    private WebElement selectCountry;

    @FindBy(id = "ddlNewOwnerState")
    private WebElement selectState;

    @FindBy(id = "txtNewOwnerCompanyName")
    public WebElement newOwnerName;

    @FindBy(id = "txtNewOwnerEmail")
    public WebElement newOwnerEmail;

    @FindBy(id = "txtNewOwnerStreetAddress1")
    public WebElement newOwnerAddress;

    @FindBy(id = "txtNewOwnerCity")
    public WebElement newOwnerCity;

    @FindBy(id = "txtNewOwnerZipCode")
    public WebElement newOwnerZipCode;

    @FindBy(id = "txtNewOwnerAreaCode")
    public WebElement newOwnerAreaCode;

    @FindBy(id = "txtNewOwnerPhone")
    public WebElement newOwnerTelNumber;

    @FindBy(id = "retailOT_btnContinue")
    public WebElement submitNewOwner;

    @FindBy(id = "btnNewOwnerModelContinue")
    public WebElement continueForm;

    @FindBy(css = "div.custom-control:nth-child(3) > label:nth-child(2)")
    public WebElement submitCheckbox;

    @FindBy(id = "retailOT_spanSubmitButton")
    public WebElement submitButton;

    public NewOwnerForm(SeleniumDriver driver) {
        super(driver);
    }

    public void fillForm(Company company) {
        driver.waitForSelect();
        select(selectUsage, USAGE);
        driver.waitForElementVisibility(By.id("txtNewOwnerCompanyName"));
        newOwnerName.sendKeys(company.getName());
        driver.waitForElementVisibility(By.id("txtNewOwnerEmail"));
        newOwnerEmail.sendKeys(company.getEmail());
        driver.waitForElementVisibility(By.id("ddlNewOwnerLocation"));
        select(selectCountry, company.getCountry());
        driver.waitForElementVisibility(By.id("txtNewOwnerStreetAddress1"));
        newOwnerAddress.sendKeys(company.getAddress());
        driver.waitForElementVisibility(By.id("txtNewOwnerCity"));
        newOwnerCity.sendKeys(company.getCity());
        driver.waitForElementVisibility(By.id("ddlNewOwnerState"));
        select(selectState, company.getState());
        driver.waitForElementVisibility(By.id("txtNewOwnerZipCode"));
        newOwnerZipCode.sendKeys(company.getZipCode());
        driver.waitForElementVisibility(By.id("txtNewOwnerAreaCode"));
        newOwnerAreaCode.sendKeys(company.getPrefixNumber());
        driver.waitForElementVisibility(By.id("txtNewOwnerPhone"));
        newOwnerTelNumber.sendKeys(company.getTelNumber());
        submitForm();
    }

    private void select(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void submitForm() {
        driver.waitForElementVisibility(By.id("retailOT_btnContinue"));
        submitNewOwner.click();
        driver.waitForElementVisibility(By.id("btnNewOwnerModelContinue"));
        continueForm.click();
        submitCheckbox.click();
        driver.waitForElementVisibility(By.id("retailOT_spanSubmitButton"));
        submitButton.click();
    }
}



