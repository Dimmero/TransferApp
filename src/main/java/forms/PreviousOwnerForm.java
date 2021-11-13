package forms;

import entities.Company;
import core.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.TransferWarrantyPage;

public class PreviousOwnerForm extends TransferWarrantyPage {
    private final String COUNTRY_POLAND = "PL";

    @FindBy(id = "ddlLocation")
    public WebElement selectValue;

    public Select getSelectOptions() {
        driver.waitForElementVisibility(By.id("ddlLocation"));
        return new Select(selectValue);
    }

    @FindBy(id = "txtCompanyName")
    public WebElement companyName;

    @FindBy(id = "txtZipcode")
    public WebElement companyZipCode;

    @FindBy(id = "retailOT_spanContinueButton")
    public WebElement submitButton;

    public PreviousOwnerForm(SeleniumDriver driver) {
        super(driver);
    }

    public void fillForm(Company company) {
        companyName.sendKeys(company.getName());
        companyZipCode.sendKeys(company.getZipCode());
        submitButton.click();
    }

    public boolean tagIsAlreadyTransferred() {
        JavascriptExecutor js = (JavascriptExecutor) driver.getDriver();
        String option = (String) js.executeScript("return document.getElementById('ddlLocation').value");
        return option.contains(COUNTRY_POLAND);
    }

}


