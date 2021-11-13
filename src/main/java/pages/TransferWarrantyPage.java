package pages;

import core.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TransferWarrantyPage  {
    public SeleniumDriver driver;
    @FindBy(id = "OTST_txtSTag1")
    private WebElement inputServiceTag;

    public TransferWarrantyPage(SeleniumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver.getDriver(), this);
    }

    public void setServiceTag(WebElement element, String tag) {
        driver.waitForElementVisibility(By.id("OTST_txtSTag1"));
        element.sendKeys(tag);
    }

    public void passServiceTagAndGoToTheNextPage(String serviceTag) {
        setServiceTag(inputServiceTag, serviceTag);
        inputServiceTag.sendKeys(Keys.ENTER);
    }

}
