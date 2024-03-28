package cycleForTransfer;

import baseElements.BaseAbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TransferWarrantyPage  extends BaseAbstractPage {
    @FindBy(id = "OTST_txtSTag1")
    public WebElement inputServiceTag;
    By inputServiceTagID = By.id("OTST_txtSTag1");

    @FindBy(id = "OTST_clearproduct1")
    public WebElement clearServiceTag;
    By clearServiceTagID = By.id("OTST_clearproduct1");

    private Cookies cookies;
    private boolean cookies2Off = true;


    public TransferWarrantyPage() {
        PageFactory.initElements(driver.getDriver(), this);
        this.cookies = new Cookies();
    }

    public int passServiceTagAndGoToTheNextPage(String serviceTag) {
        driver.getLongWait35().until(ExpectedConditions.elementToBeClickable(inputServiceTagID));
        if (cookies2Off) {
            cookies.turnOffCookies();
            cookies2Off = false;
        }
        inputServiceTag.sendKeys(serviceTag, Keys.ENTER);
        try {
            WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(15));
            wait.pollingEvery(Duration.ofMillis(500)).until(ExpectedConditions.urlContains("currentowner"));
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public void clearServiceTag() {
        driver.getLongWait35().until(ExpectedConditions.elementToBeClickable(clearServiceTagID));
        clearServiceTag.click();
    }

}
