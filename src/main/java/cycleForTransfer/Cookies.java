package cycleForTransfer;

import baseElements.BaseAbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Cookies extends BaseAbstractPage {
    @FindBy(xpath = "//a[@aria-label='dismiss cookie message']")
    public static WebElement notAllowCookies;

    @FindBy(xpath = "//div[@aria-label='cookieconsent']")
    public WebElement cookiesConsent;
    public By cookiesConsentXpath = By.xpath("//div[@aria-label='cookieconsent']");
    public Cookies() {
        PageFactory.initElements(driver.getDriver(), this);
    }

    public void turnOffCookies() {
        try {
            WebDriverWait wait = new WebDriverWait(driver.getDriver(), 10);
            wait.pollingEvery(Duration.ofMillis(500)).until(ExpectedConditions.elementToBeClickable(notAllowCookies));
            notAllowCookies.click();
        } catch (Exception o) {
            System.out.println("no stupid cookies, go further");
        }
    }
}
