package cycleForTransfer;

import BaseElements.BaseAbstractPage;
import core.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import cycleForStats.DellLoginPage;
import org.openqa.selenium.support.PageFactory;

public class Cookies extends BaseAbstractPage {
    @FindBy(xpath = "//a[@aria-label='dismiss cookie message']")
    public WebElement notAllowCookies;

    public Cookies() {
        PageFactory.initElements(driver.getDriver(), this);
    }

    public void turnOffCookies() {
        try {
            notAllowCookies.click();
        } catch (Exception o) {
            System.out.println("no stupid cookies, go further");
        }
    }
}
