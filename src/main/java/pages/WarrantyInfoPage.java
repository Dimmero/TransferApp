package pages;

import core.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WarrantyInfoPage {
    @FindBy(id = "ps-inlineWarranty")
    public WebElement inlineWarranty;

    @FindBy(xpath = "//div[@id='warranty-card']//p[contains(@class,'h5 mt-lg-1')]")
    public WebElement warrantyInfo;

    @FindBy(xpath = "//p[@class='mb-0 ml-3 ']//a")
    public WebElement expandInfo;

    @FindBy(id = "countryLabel")
    public WebElement countryLabel;

    public WarrantyInfoPage() {
        PageFactory.initElements(SeleniumDriver.driver, this);
    }

    public String getInlineWarrantyText() {
        SeleniumDriver.waitForElement(By.id("ps-inlineWarranty"));
        return inlineWarranty.getText();
    }

    public String getWarrantyInfo() {
        SeleniumDriver.waitForElement(By.xpath("//div[@id='warranty-card']//p[contains(@class,'h5 mt-lg-1')]"));
        return warrantyInfo.getText();
    }

    public String getCountryLabel() {
        expandInfo.click();
        SeleniumDriver.waitForElement(By.id("countryLabel"));
        return countryLabel.getText();
    }
}
