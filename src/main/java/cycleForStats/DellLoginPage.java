package cycleForStats;

import core.SeleniumDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DellLoginPage  {
    public SeleniumDriver driver;

    @FindBy(id = "inpEntrySelection")
    public WebElement inputServiceTag;

    public DellLoginPage(SeleniumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver.getDriver(), this);
    }

    public void passServiceTagAndGoToTheNextPage(String serviceTag) {
        inputServiceTag.sendKeys(serviceTag, Keys.ENTER);
    }
}
