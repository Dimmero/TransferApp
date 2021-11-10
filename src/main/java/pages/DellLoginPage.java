package pages;

import core.SeleniumDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DellLoginPage extends TransferWarrantyPage  {
    @FindBy(id = "inpEntrySelection")
    public WebElement inputServiceTag;

    @FindBy(id = "ps-inlineWarranty")
    public WebElement inlineWarranty;

    @FindBy(xpath = "//div[@id='warranty-card']//p[contains(@class,'h5 mt-lg-1')]")
    public WebElement warrantyInfo;

    @FindBy(xpath = "//p[@class='mb-0 ml-3 ']//a")
    public WebElement expandWarrantyInfo;

    @FindBy(id = "countryLabel")
    public WebElement countryLabel;

    public DellLoginPage() {
        PageFactory.initElements(SeleniumDriver.driver, this);
    }

    @Override
    public void setServiceTag(WebElement element, String tag) {
        element.sendKeys(tag);
    }

    @Override
    public void passServiceTagAndGoToTheNextPage(String serviceTag) {
        setServiceTag(inputServiceTag, serviceTag);
        inputServiceTag.sendKeys(Keys.ENTER);;
    }
}
