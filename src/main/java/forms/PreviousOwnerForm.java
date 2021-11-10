package forms;

import entities.Company;
import core.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PreviousOwnerForm {
    private final String COUNTRY_POLAND = "PL";

    @FindBy(id = "ddlLocation")
    private WebElement select;

    @FindBy(id = "txtCompanyName")
    public WebElement companyName;

    @FindBy(id = "txtZipcode")
    public WebElement companyZipCode;

    @FindBy(id = "retailOT_spanContinueButton")
    public WebElement submitButton;

    public PreviousOwnerForm() {
        PageFactory.initElements(SeleniumDriver.driver, this);
    }

    public void fillForm(Company company) {
        SeleniumDriver.waitForElement(By.id("ddlLocation"));
        if (checkIfAlreadyPoland(select)) {
            return;
        }
//        JavascriptExecutor js = (JavascriptExecutor) SeleniumDriver.driver;
//        String name = (String) js.executeScript("return document.getElementById('txtCompanyName').value");
//        if (!name.isEmpty()) {
//            submitButton.click();
//            return;
//        }
        companyName.sendKeys(company.getName());
        companyZipCode.sendKeys(company.getZipCode());
        submitButton.click();
    }

    private boolean checkIfAlreadyPoland(WebElement select) {
        JavascriptExecutor js = (JavascriptExecutor) SeleniumDriver.driver;
        String name = (String) js.executeScript("return document.getElementById('ddlLocation').value");
        return name.contains(COUNTRY_POLAND);
    }

}


