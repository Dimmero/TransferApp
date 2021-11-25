package cycleForStats;

import core.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WarrantyInfoPage extends DellLoginPage {
    @FindBy(xpath = "//p[@class='mb-0 ml-3 ']//a")
    public WebElement expandInfo;
    public By expandInfoXpath = By.xpath("//p[@class='mb-0 ml-3 ']//a");

    @FindBy(xpath = "//div[@id='countryLabel']//div")
    public WebElement countryLabel;
    public By countryLabelXpath = By.xpath("//div[@id='countryLabel']//div");

    @FindBy(xpath = "//div[contains(@class, \"px-0 col-md-3\")]//*[last()]")
    public WebElement warrantyExpiresLabel;
    public By warrantyExpiresLabelXpath = By.xpath("//div[contains(@class, 'px-0 col-md-3')]//*[last()]");

    public WarrantyInfoPage(SeleniumDriver driver) {
        super(driver);
    }

    public String getCountryLabel() {
        driver.waitForElementVisibility(expandInfoXpath);
        expandInfo.click();
        return driver.waitForElementAndGetText(countryLabelXpath);
    }

    public String getWarrantyExpiresInfo() {
        return driver.waitForElementAndGetText(warrantyExpiresLabelXpath);
    }

    public String getCountryName() {
        String scriptForCountryName = "return document.evaluate(\"//div[@id='countryLabel']//div\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.innerText";
        ((JavascriptExecutor) driver.getDriver()).executeScript("$(\"a:contains('Wyświetl szczegóły')\").trigger(\"click\")");
        driver.waitForElementVisibility(countryLabelXpath);
        return (String)((JavascriptExecutor) driver.getDriver()).executeScript(scriptForCountryName);
    }
}
