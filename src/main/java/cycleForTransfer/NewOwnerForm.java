package cycleForTransfer;

import entities.Company;
import core.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class NewOwnerForm extends TransferWarrantyPage {
    public final String USAGE = "2_Office";
    @FindBy(id = "ddlIntendedUse")
    public WebElement selectUsage;

    @FindBy(id = "ddlNewOwnerLocation")
    public WebElement selectCountry;

    @FindBy(id = "ddlNewOwnerState")
    public WebElement selectState;

    @FindBy(id = "txtNewOwnerCompanyName")
    public WebElement companyNameInput;

    @FindBy(id = "txtNewOwnerEmail")
    public WebElement emailInput;

    @FindBy(id = "txtNewOwnerStreetAddress1")
    public WebElement streetAddress1Input;

    @FindBy(id = "txtNewOwnerCity")
    public WebElement cityInput;

    @FindBy(id = "txtNewOwnerZipCode")
    public WebElement zipCodeInput;

    @FindBy(id = "txtNewOwnerAreaCode")
    public WebElement areaCodeInput;

    @FindBy(id = "txtNewOwnerPhone")
    public WebElement phoneNumberInput;

    @FindBy(id = "retailOT_btnContinue")
    public WebElement submitNewOwner;

    @FindBy(id = "btnNewOwnerModelContinue")
    public WebElement btnNewOwnerModelContinue;

    @FindBy(xpath = "//label[@for='chkAgree']")
    public WebElement checkboxAgree;

    @FindBy(id = "retailOT_spanSubmitButton")
    public WebElement submitButton;

    @FindBy(id = "retailOT_btnStartAgain")
    public WebElement submitAgain;

    public NewOwnerForm() {
        PageFactory.initElements(driver.getDriver(), this);
    }

    public void fillForm(Company company) {
        select(selectUsage, USAGE);
        driver.getWait().until(ExpectedConditions.elementToBeClickable(companyNameInput)).sendKeys(company.getName());
        driver.getWait().until(ExpectedConditions.elementToBeClickable(emailInput)).sendKeys(company.getEmail());
        select(selectCountry, company.getCountry());
        driver.getWait().until(ExpectedConditions.elementToBeClickable(streetAddress1Input)).sendKeys(company.getAddress());
        driver.getWait().until(ExpectedConditions.elementToBeClickable(cityInput)).sendKeys(company.getCity());
        select(selectState, company.getState());
        driver.getWait().until(ExpectedConditions.elementToBeClickable(zipCodeInput)).sendKeys(company.getZipCode());
        driver.getWait().until(ExpectedConditions.elementToBeClickable(areaCodeInput)).sendKeys(company.getPrefixNumber());
        driver.getWait().until(ExpectedConditions.elementToBeClickable(phoneNumberInput)).sendKeys(company.getTelNumber());
        driver.getDriver().findElement(By.tagName(BODY_TAG)).sendKeys(Keys.CONTROL, Keys.ENTER);
        submitForm();
    }

    private void select(WebElement element, String value) {
        driver.getWait().until(ExpectedConditions.elementToBeClickable(selectUsage));
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void submitForm() {
        driver.getWait().until(ExpectedConditions.elementToBeClickable(submitNewOwner)).click();
        driver.getWait().until(ExpectedConditions.elementToBeClickable(btnNewOwnerModelContinue)).click();
        checkboxAgree.click();
        driver.getWait().until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        driver.getWait().until(ExpectedConditions.elementToBeClickable(submitAgain)).click();
    }
}



