package cycleForTransfer;

import core.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TransferWarrantyPage  {
    public SeleniumDriver driver;

    @FindBy(id = "OTST_txtSTag1")
    public WebElement inputServiceTag;
    public By inputServiceTagID = By.id("OTST_txtSTag1");

    public TransferWarrantyPage(SeleniumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver.getDriver(), this);
    }

    public void passServiceTagAndGoToTheNextPage(String serviceTag) {
        driver.waitForElementAndSendKeys(inputServiceTagID, serviceTag);
        inputServiceTag.sendKeys(Keys.ENTER);
    }
}
