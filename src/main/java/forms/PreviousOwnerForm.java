package forms;

import entities.Company;
import core.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PreviousOwnerForm {
    private final String COUNTRY_POLAND = "PL";

    @FindBy(id = "ddlLocation")
    public WebElement selectValue;

    public Select getSelectOptions() {
        SeleniumDriver.waitForElement(By.id("ddlLocation"));
        return new Select(selectValue);
    }

    @FindBy(id = "txtCompanyName")
    public WebElement companyName;

    @FindBy(id = "txtZipcode")
    public WebElement companyZipCode;

    @FindBy(id = "retailOT_spanContinueButton")
    public WebElement submitButton;

    public PreviousOwnerForm() {
        PageFactory.initElements(SeleniumDriver.driver, this);
    }

    public boolean fillForm(Company company) {
        if (tagIsAlreadyTransferred()) {
            return true;
        } else {
            companyName.sendKeys(company.getName());
            companyZipCode.sendKeys(company.getZipCode());
            submitButton.click();
        }
        return false;
    }

    private boolean tagIsAlreadyTransferred() {
        String option = getSelectOptions().getFirstSelectedOption().getAttribute("value");
        return option.contains(COUNTRY_POLAND);
    }

}


