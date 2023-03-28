package cycleForTransfer;

import entities.Company;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class PreviousOwnerForm extends TransferWarrantyPage {
    protected final String TITLE = "Ownership Transfer | Dell Vietnam";
    @FindBy(id = "ddlLocation")
    public WebElement countryInput;

    @FindBy(id = "txtCompanyName")
    public WebElement companyNameInput;

    @FindBy(id = "txtZipcode")
    public WebElement companyZipCodeInput;

    @FindBy(id = "retailOT_btnContinue")
    public WebElement submitButtonInput;

    public PreviousOwnerForm() {
        PageFactory.initElements(driver.getDriver(), this);
    }

    public void fillForm(Company company) {
        driver.getWait().until(ExpectedConditions.elementToBeClickable(companyNameInput)).sendKeys(company.getName());
        driver.getWait().until(ExpectedConditions.elementToBeClickable(companyZipCodeInput)).sendKeys(company.getZipCode());
        submitButtonInput.click();
    }

    public String grabPreviousOwnerCountryInfo() {
        driver.getWait().until(ExpectedConditions.visibilityOf(countryInput));
        driver.getWait().pollingEvery(Duration.ofMillis(500)).until(ExpectedConditions.visibilityOf(countryInput));
        return countryInput.getAttribute("value");
    }

}


