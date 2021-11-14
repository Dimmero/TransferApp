package cycleForTransfer;

import core.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import cycleForStats.DellLoginPage;

public class Cookies extends DellLoginPage {
    @FindBy(xpath = "//a[@aria-label='allow cookies']")
    public WebElement cookies;
    public By cookiesXpath = By.xpath("//a[@aria-label='allow cookies']");

    public Cookies(SeleniumDriver driver) {
        super(driver);
    }

    public void turnOffCookies() {
        try {
            driver.waitForElementVisibility(cookiesXpath);
            cookies.click();
        } catch (Exception o) {
            System.out.println("no stupid cookies, go further");
        }
    }
}
