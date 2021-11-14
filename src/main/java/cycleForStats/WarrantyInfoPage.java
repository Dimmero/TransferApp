package cycleForStats;

import core.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WarrantyInfoPage extends DellLoginPage {
    @FindBy(id = "ps-inlineWarranty")
    public WebElement inlineWarranty;

    @FindBy(xpath = "//div[@id='warranty-card']//p[contains(@class,'h5 mt-lg-1')]")
    public WebElement warrantyInfo;
    public By warrantyInfoXpath = By.xpath("//div[@id='warranty-card']//p[contains(@class,'h5 mt-lg-1')]");

    @FindBy(xpath = "//p[@class='mb-0 ml-3 ']//a")
    public WebElement expandInfo;

    @FindBy(id = "countryLabel")
    public WebElement countryLabel;
    public By countryLabelID = By.id("countryLabel");

    public WarrantyInfoPage(SeleniumDriver driver) {
        super(driver);
    }

    public String getInlineWarrantyText() {
        driver.waitForElementVisibility(By.id("ps-inlineWarranty"));
        return inlineWarranty.getText();
    }

    public String getWarrantyInfo() {
        driver.waitForElementVisibility(warrantyInfoXpath);
        return warrantyInfo.getText();
    }

    public String getCountryLabel() {
        expandInfo.click();
        driver.waitForElementVisibility(countryLabelID);
        return countryLabel.getText();
    }
}
