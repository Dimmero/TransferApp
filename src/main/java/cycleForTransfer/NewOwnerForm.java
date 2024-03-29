package cycleForTransfer;

import entities.Company;
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
    By companyNameInputID = By.id("txtNewOwnerCompanyName");

    @FindBy(id = "txtNewOwnerEmail")
    public WebElement emailInput;
    By emailInputID = By.id("txtNewOwnerEmail");

    @FindBy(id = "txtNewOwnerStreetAddress1")
    public WebElement streetAddress1Input;
    By streetAddress1InputID = By.id("txtNewOwnerStreetAddress1");

    @FindBy(id = "txtNewOwnerCity")
    public WebElement cityInput;
    By cityInputID = By.id("txtNewOwnerCity");

    @FindBy(id = "txtNewOwnerZipCode")
    public WebElement zipCodeInput;
    By zipCodeInputID = By.id("txtNewOwnerZipCode");

    @FindBy(id = "txtNewOwnerAreaCode")
    public WebElement areaCodeInput;
    By areaCodeInputID = By.id("txtNewOwnerAreaCode");

    @FindBy(id = "txtNewOwnerPhone")
    public WebElement phoneNumberInput;
    By phoneNumberInputID = By.id("txtNewOwnerPhone");

    @FindBy(id = "retailOT_btnContinue")
    public WebElement submitNewOwner;
    By submitNewOwnerID = By.id("retailOT_btnContinue");

    @FindBy(id = "btnNewOwnerModelContinue")
    public WebElement btnNewOwnerModelContinue;
    By btnNewOwnerModelContinueID = By.id("btnNewOwnerModelContinue");

    @FindBy(xpath = "//label[@for='chkAgree']")
    public WebElement checkboxAgree;
    By checkboxAgreeXpath = By.xpath("//label[@for='chkAgree']");

    @FindBy(id = "retailOT_spanSubmitButton")
    public WebElement submitButtonFinal;
    By submitButtonFinalID = By.id("retailOT_spanSubmitButton");

    @FindBy(id = "retailOT_btnStartAgain")
    public WebElement submitAgain;
    By submitAgainID = By.id("retailOT_btnStartAgain");
    public NewOwnerForm() {
        PageFactory.initElements(driver.getDriver(), this);
    }

    public void fillForm(Company company) {
        driver.sleepForSomeTime(2);
        select(selectUsage, USAGE);
        driver.getLongWait35().until(ExpectedConditions.elementToBeClickable(companyNameInputID));
        companyNameInput.sendKeys(company.getName());
        driver.getLongWait35().until(ExpectedConditions.elementToBeClickable(emailInputID));
        emailInput.sendKeys(company.getEmail());
        driver.sleepForSomeTime(2);
        select(selectCountry, company.getCountry());
        driver.getLongWait35().until(ExpectedConditions.elementToBeClickable(streetAddress1InputID));
        streetAddress1Input.sendKeys(company.getAddress());
        driver.getLongWait35().until(ExpectedConditions.elementToBeClickable(cityInputID));
        cityInput.sendKeys(company.getCity());
        driver.sleepForSomeTime(2);
        select(selectState, company.getState());
        driver.getLongWait35().until(ExpectedConditions.elementToBeClickable(zipCodeInputID));
        zipCodeInput.sendKeys(company.getZipCode());
        driver.getLongWait35().until(ExpectedConditions.elementToBeClickable(areaCodeInputID));
        areaCodeInput.sendKeys(company.getPrefixNumber());
        driver.getLongWait35().until(ExpectedConditions.elementToBeClickable(phoneNumberInputID));
        phoneNumberInput.sendKeys(company.getTelNumber());
        driver.getDriver().findElement(By.tagName(BODY_TAG)).sendKeys(Keys.CONTROL, Keys.ENTER);
    }

    private void select(WebElement element, String value) {
        driver.getLongWait35().until(ExpectedConditions.elementToBeClickable(selectUsage));
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void submitForm() {
        driver.getLongWait35().until(ExpectedConditions.elementToBeClickable(submitNewOwnerID));
        submitNewOwner.click();
        driver.getLongWait35().until(ExpectedConditions.visibilityOfElementLocated(btnNewOwnerModelContinueID));
        btnNewOwnerModelContinue.click();
        driver.getLongWait35().until(ExpectedConditions.elementToBeClickable(checkboxAgreeXpath));
        checkboxAgree.click();
        driver.getLongWait35().until(ExpectedConditions.elementToBeClickable(submitButtonFinalID));
        submitButtonFinal.click();
        driver.getLongWait35().until(ExpectedConditions.elementToBeClickable(submitAgainID));
        submitAgain.click();
    }
}



