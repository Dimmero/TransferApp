package cycleForStats;

import BaseElements.BaseAbstractPage;
import core.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static core.SeleniumDriver.tabs;

public class DellLoginPage extends BaseAbstractPage {
    @FindBy(id = "inpEntrySelection")
    public WebElement inputServiceTag;
    public By inputServiceTagXpath = By.xpath("//input[@id='inpEntrySelection']");

    @FindBy(id = "sec-text-container")
    public WebElement processingRequestMessage;

    @FindBy(xpath = "//div[@id='sec-container']//a")
    public WebElement clickToContinue;

    @FindBy(xpath = "//span[@id='txtSearchEs']")
    public WebElement buttonSearch;

    public DellLoginPage() {
        PageFactory.initElements(driver.getDriver(), this);
    }

    public void passServiceTagAndGoToTheNextPage(String serviceTag) {
        driver.getWait().until(ExpectedConditions.elementToBeClickable(inputServiceTagXpath)).sendKeys(serviceTag, Keys.ENTER);;
    }

    public void waitAndClickSearch() {
        driver.getWait().pollingEvery(Duration.ofMillis(500)).until(ExpectedConditions.invisibilityOf(processingRequestMessage));
//        driver.sleepForSomeTime(23000);
        buttonSearch.click();
    }

    public void waitForValidationModal() {
        if (driver.getDriver().getCurrentUrl().length() < 75) {
            waitAndClickSearch();
        }
    }

    public void provideTagValid(String tag) {
        passServiceTagAndGoToTheNextPage(tag);
//        driver.sleepForSomeTime(2000);
        if (driver.getDriver().getCurrentUrl().length() < 45) {
            waitAndClickSearch();
        }
    }

    public void closeTransferAndStatTab() {
        driver.getDriver().close();
        driver.getDriver().switchTo().window(tabs.get(1));
        closeStatTabAndOpenAgain();
    }

    public void closeStatTabAndOpenAgain() {
        driver.getDriver().close();
        driver.getDriver().switchTo().window(tabs.get(0));
        driver.openNewTab(CycleForStats.URL_STATS, 1);
    }
}
