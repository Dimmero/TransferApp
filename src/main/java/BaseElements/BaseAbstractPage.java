package BaseElements;

import core.SeleniumDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class BaseAbstractPage  extends BaseObject{
    public static SeleniumDriver driver;
    protected final String URL_TRANSFER = "https://www.dell.com/support/assets-transfer/en-vn";
    protected static String URL_STATS = "https://www.dell.com/support/home/en-vn";
    public final String COUNTRY_POLAND = "PL";
    public final String BODY_TAG = "body";

    public BaseAbstractPage() {
        if (driver == null)
        driver = new SeleniumDriver();
    }

    public void waitIxplisitlyFor(int milli, String title) {
        driver.getWait().pollingEvery(Duration.ofMillis(milli)).until(ExpectedConditions.titleIs(title));
    }
}
