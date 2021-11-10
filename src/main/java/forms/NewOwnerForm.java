package forms;

import entities.Company;
import core.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class NewOwnerForm {
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

    @FindBy(id = "txtNewOwnerStreetAddress1")
    public WebElement companyAddress;

    @FindBy(id = "txtNewOwnerZipCode")
    public WebElement newOwnerZipCode;

    @FindBy(id = "txtNewOwnerAreaCode")
    public WebElement newOwnerAreaCode;

    @FindBy(id = "txtNewOwnerPhone")
    public WebElement newOwnerTelNumber;

    @FindBy(id = "retailOT_spanContinueButton")
    public WebElement submitNewOwner;

    @FindBy(id = "btnNewOwnerModelContinue")
    public WebElement continueForm;

    @FindBy(css = "div.custom-control:nth-child(3) > label:nth-child(2)")
    public WebElement submitCheckbox;

    @FindBy(id = "retailOT_spanSubmitButton")
    public WebElement submitButton;

    public NewOwnerForm() {
        PageFactory.initElements(SeleniumDriver.driver, this);
    }

    public void fillForm(Company company) {
        SeleniumDriver.waitForElement(By.id("ddlIntendedUse"));
        SeleniumDriver.waitForSelect();
        select(selectUsage, USAGE);
        SeleniumDriver.waitForElement(By.id("txtNewOwnerCompanyName"));
//        JavascriptExecutor js = (JavascriptExecutor) SeleniumDriver.driver;
//        String newName = (String) js.executeScript("return document.getElementById('txtNewOwnerCompanyName').value");
//        if (!newName.isEmpty()) {
//            submitForm();
//            return;
//        }
        newOwnerName.sendKeys(company.getName());
        SeleniumDriver.waitForElement(By.id("txtNewOwnerEmail"));
        newOwnerEmail.sendKeys(company.getEmail());
        SeleniumDriver.waitForElement(By.id("txtNewOwnerStreetAddress1"));
        newOwnerAddress.sendKeys(company.getAddress());
        SeleniumDriver.waitForElement(By.id("ddlNewOwnerLocation"));
        select(selectCountry, company.getCountry());
        SeleniumDriver.waitForElement(By.id("txtNewOwnerCity"));
        newOwnerCity.sendKeys(company.getCity());
        SeleniumDriver.waitForElement(By.id("ddlNewOwnerState"));
        select(selectState, company.getState());
        SeleniumDriver.waitForElement(By.id("txtNewOwnerZipCode"));
        newOwnerZipCode.sendKeys(company.getZipCode());
        SeleniumDriver.waitForElement(By.id("txtNewOwnerAreaCode"));
        newOwnerAreaCode.sendKeys(company.getPrefixNumber());
        SeleniumDriver.waitForElement(By.id("txtNewOwnerPhone"));
        newOwnerTelNumber.sendKeys(company.getTelNumber());
        submitForm();
    }

    private void select(WebElement select, String value) {
        Select usage = new Select(select);
        usage.selectByValue(value);
    }

    public void submitForm() {
        SeleniumDriver.waitForElement(By.id("retailOT_spanContinueButton"));
        submitNewOwner.click();
        SeleniumDriver.wait.until(ExpectedConditions.elementToBeClickable(By.id("btnNewOwnerModelContinue")));
        continueForm.click();
        submitCheckbox.click();
        SeleniumDriver.wait.until(ExpectedConditions.elementToBeClickable(By.id("retailOT_spanSubmitButton")));
        submitButton.click();
    }
}



