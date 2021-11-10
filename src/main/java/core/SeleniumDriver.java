package core;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SeleniumDriver {
    public static WebDriver driver = new ChromeDriver();
    public static WebDriverWait wait;

    public static void initDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        driver.manage().window().setSize(new Dimension(500, 800));
        wait = new WebDriverWait(driver, 5);
    }

    public static void openNewTab(String url) {
        String URL_TRANSFER = "'" + url + "'";
        String jsScript = "window.open(" + URL_TRANSFER + ", '_blank');";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript( jsScript);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public static void waitForSelect() {
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
    }

    public static void waitForElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void closeDriver() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }
}
