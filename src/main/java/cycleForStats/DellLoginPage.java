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

    @FindBy(id = "homemfe-dropdown-input")
//    @FindBy(id = "inpEntrySelection")
    public WebElement inputServiceTag;
//    By inputServiceTagID = By.id("inpEntrySelection");
    By inputServiceTagID = By.id("homemfe-dropdown-input");

    @FindBy(xpath = "//div[@id='sec-container']//a")
    public WebElement clickToContinue;

    @FindBy(xpath = "//span[@id='txtSearchEs']")
    public WebElement buttonSearch;

    @FindBy(id = "sec-cpt-if")
    public WebElement iframe;

    public DellLoginPage() {
        PageFactory.initElements(driver.getDriver(), this);
    }

    public void passServiceTagAndGoToTheNextPage(String serviceTag) {
        driver.getLongWait35().until(ExpectedConditions.visibilityOfElementLocated(inputServiceTagID));
        inputServiceTag.sendKeys(serviceTag, Keys.ENTER);
    }
}
