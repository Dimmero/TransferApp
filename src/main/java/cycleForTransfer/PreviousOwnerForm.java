package cycleForTransfer;

import entities.Company;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PreviousOwnerForm extends TransferWarrantyPage {
    @FindBy(id = "ddlLocation")
    public WebElement countryInput;

    @FindBy(id = "txtCompanyName")
    public WebElement companyNameInput;
    By companyNameInputID = By.id("txtCompanyName");

    @FindBy(id = "txtZipcode")
    public WebElement companyZipCodeInput;
    By companyZipCodeInputID = By.id("txtZipcode");

    @FindBy(id = "retailOT_btnContinue")
    public WebElement submitButtonInput;
    By submitButtonInputID = By.id("retailOT_btnContinue");

    public PreviousOwnerForm() {
        PageFactory.initElements(driver.getDriver(), this);
    }

    public void fillForm(Company company) {
        driver.sleepForSomeTime(1);
        driver.getLongWait35().until(ExpectedConditions.elementToBeClickable(companyNameInputID));
        companyNameInput.sendKeys(company.getName());
        driver.getLongWait35().until(ExpectedConditions.elementToBeClickable(companyZipCodeInputID));
        companyZipCodeInput.sendKeys(company.getZipCode());
        driver.getLongWait35().until(ExpectedConditions.elementToBeClickable(submitButtonInputID));
        submitButtonInput.click();
    }

    public String grabPreviousOwnerCountryInfo() {
        driver.getLongWait35().until(ExpectedConditions.visibilityOf(countryInput));
        driver.sleepForSomeTime(3);
        return (String)((JavascriptExecutor) driver.getDriver()).executeScript("return document.getElementById('ddlLocation').value");
    }

}


