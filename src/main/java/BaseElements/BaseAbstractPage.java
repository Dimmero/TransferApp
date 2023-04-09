package BaseElements;

import core.SeleniumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseAbstractPage extends BaseObject {
    public static SeleniumDriver driver;
    public final String URL_TRANSFER = "https://www.dell.com/support/assets-transfer/en-vn";
    public static String URL_STATS = "https://www.dell.com/support/home/en-vn";
    public final String COUNTRY_POLAND = "PL";
    public final String BODY_TAG = "body";

    public void waitImplicitlyFor(int milli, String title) {
        driver.getWait().pollingEvery(Duration.ofMillis(milli)).until(ExpectedConditions.titleIs(title));
    }

    public Boolean matchFound(String patternValue, String value) {
        Pattern pattern = Pattern.compile(patternValue);
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }

    public void waitForPageLoad() {
        ((JavascriptExecutor) driver.getDriver()).executeScript("return document.readyState").equals("complete");
    }
}
