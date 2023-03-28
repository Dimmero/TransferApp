package cycleForStats;

import BaseElements.BaseAbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WarrantyInfoPage extends BaseAbstractPage {
    public final String INLINE_ISSUE = "Issue with Support Services";

    @FindBy(xpath = "//a[@id='viewDetailsWarranty']")
    public WebElement expandInfo;

    @FindBy(id = "countryLabel")
    public WebElement countryLabel;
    public By countryLabelXpath = By.xpath("//div[@id='countryLabel']//div");

    @FindBy(id = "dsk-expirationDt")
    public WebElement warrantyExpiresLabel;

    @FindBy(id = "ps-inlineWarranty")
    public WebElement warrantyInlineInfo;

    public WarrantyInfoPage() {
        PageFactory.initElements(driver.getDriver(), this);

    }

    public String getCountryLabel() {
        driver.getWait().until(ExpectedConditions.elementToBeClickable(expandInfo)).click();
        return driver.waitForElementAndGetText(countryLabel);
    }

    public boolean checkIfIssue() {
        String warrantyInfo = driver.waitForElementAndGetText(warrantyInlineInfo);
        return warrantyInfo.contains(INLINE_ISSUE);
    }

    public String getWarrantyExpiresInfo() {
        return driver.waitForElementAndGetText(warrantyExpiresLabel);
    }

    public String getCountryName() {
        String scriptForCountryName = "return document.evaluate(\"//div[@id='countryLabel']//div\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.innerText";
        ((JavascriptExecutor) driver.getDriver()).executeScript("$(\"a:contains('Wyświetl szczegóły')\").trigger(\"click\")");
        driver.getWait().until(ExpectedConditions.visibilityOf(countryLabel));
        return (String)((JavascriptExecutor) driver.getDriver()).executeScript(scriptForCountryName);
    }
}
