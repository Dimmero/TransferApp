package cycleForStats;

import baseElements.BaseAbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static core.SeleniumDriver.tabs;

public class DellLoginPage extends BaseAbstractPage {
    public final String DELL_LOGIN_PAGE_TITLE = "Support | Dell Vietnam";
    public final String DELL_WARRANTY_PAGE_TITLE = "Support for";

    @FindBy(id = "inpEntrySelection")
    public WebElement inputServiceTag;
    By inputServiceTagID = By.id("inpEntrySelection");

//    @FindBy(id = "homemfe-dropdown-input")
//    public WebElement inputServiceTag;
//    By inputServiceTagID = By.id("inpEntrySelection");


    @FindBy(id = "sec-text-container")
    public WebElement processingRequestMessage;

    @FindBy(xpath = "//div[@id='sec-container']//a")
    public WebElement clickToContinue;

    @FindBy(xpath = "//span[@id='txtSearchEs']")
    public WebElement buttonSearch;


//    @FindBy(id = "btnSubmit")
//    public WebElement buttonSearch;


    @FindBy(id = "sec-cpt-if")
    public WebElement iframe;

    public DellLoginPage() {
        PageFactory.initElements(driver.getDriver(), this);
    }

    public int provideTagWithValidation(String tag) {
        try {
            driver.getDriver().switchTo().window(tabs.get(2));
            driver.getDriver().get(URL_STATS);
            passServiceTagAndGoToTheNextPage(tag);
            driver.sleepForSomeTime(5000);
            if (matchFound("^.*(products)$", driver.getDriver().getCurrentUrl())) {
                waitAndClickSearch();
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    public void passServiceTagAndGoToTheNextPage(String serviceTag) {
        driver.getWait().until(ExpectedConditions.visibilityOfElementLocated(inputServiceTagID));
        inputServiceTag.sendKeys(serviceTag, Keys.ENTER);
    }

    public void waitAndClickSearch() {
        driver.getWait().pollingEvery(Duration.ofMillis(500)).until(ExpectedConditions.invisibilityOf(iframe));
        buttonSearch.click();
    }
}
