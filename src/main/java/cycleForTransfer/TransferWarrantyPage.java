package cycleForTransfer;

import BaseElements.BaseAbstractPage;
import core.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.prefs.BackingStoreException;

public class TransferWarrantyPage  extends BaseAbstractPage {
    @FindBy(id = "OTST_txtSTag1")
    public WebElement inputServiceTag;
    By inputServiceTagID = By.id("OTST_txtSTag1");

    @FindBy(id = "OTST_clearproduct1")
    public WebElement clearServiceTag;
    By clearServiceTagID = By.id("OTST_clearproduct1");

    public TransferWarrantyPage() {
        PageFactory.initElements(driver.getDriver(), this);
    }

    public void passServiceTagAndGoToTheNextPage(String serviceTag) {
        driver.getWait().until(ExpectedConditions.elementToBeClickable(inputServiceTagID));
        inputServiceTag.sendKeys(serviceTag, Keys.ENTER);
        driver.sleepForSomeTime(3000);
    }

    public void clearServiceTag() {
        driver.getWait().until(ExpectedConditions.elementToBeClickable(clearServiceTagID));
        clearServiceTag.click();
    }

}
