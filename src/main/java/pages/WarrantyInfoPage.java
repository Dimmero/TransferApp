package pages;

import core.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WarrantyInfoPage extends DellLoginPage {
    @FindBy(id = "ps-inlineWarranty")
    public WebElement inlineWarranty;

    @FindBy(xpath = "//div[@id='warranty-card']//p[contains(@class,'h5 mt-lg-1')]")
    public WebElement warrantyInfo;

    @FindBy(xpath = "//p[@class='mb-0 ml-3 ']//a")
    public WebElement expandInfo;

    @FindBy(id = "countryLabel")
    public WebElement countryLabel;

    public WarrantyInfoPage(SeleniumDriver driver) {
        super(driver);
    }

    public String getInlineWarrantyText() {
        driver.waitForElementToBeClickable(By.id("ps-inlineWarranty"));
        return inlineWarranty.getText();
    }

    public String getWarrantyInfo() {
        driver.waitForElementToBeClickable(By.xpath("//div[@id='warranty-card']//p[contains(@class,'h5 mt-lg-1')]"));
        return warrantyInfo.getText();
    }

    public String getCountryLabel() {
        expandInfo.click();
        driver.waitForElementToBeClickable(By.id("countryLabel"));
        return countryLabel.getText();
    }
}
