package core;

import baseElements.BaseObject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;


public class SeleniumDriver extends BaseObject {
    private WebDriver driver;
    private final WebDriverWait longWait35;
    private final WebDriverWait shortWait10;
    public static List<String> tabs;
    protected final String HEADLESS_CHROME = "headless";
    protected final String CHROMEDRIVER_PATH = "/usr/local/bin/chromedriver";

    public SeleniumDriver() {
        runChromeDriver(false);
        this.longWait35 = new WebDriverWait(this.driver, DEFAULT_TIMEOUT);
        this.shortWait10 = new WebDriverWait(this.driver, 10);
        tabs = new ArrayList<>(driver.getWindowHandles());
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public WebDriverWait getLongWait35() {
        return this.longWait35;
    }
    public WebDriverWait getShortWait10() {
        return this.shortWait10;
    }


    public void quitDriver() {
        driver.quit();
        driver = null;
    }

    public void runChromeDriver(boolean headless) {
        System.setProperty(HEADLESS_CHROME, "false");
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--" + HEADLESS_CHROME);
        }
//        WebDriverManager.chromedriver().setup();
//        this.driver = new ChromeDriver(options);
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
//        this.driver.manage().timeouts().implicitlyWait(24, TimeUnit.SECONDS);
    }

    public void openNewTab(String url) {
        String URL = "'" + url + "'";
        String jsScript = "window.open(" + URL + ", '_blank');";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(jsScript);
        longWait35.until(ExpectedConditions.numberOfWindowsToBe(tabs.size() + 1));
        tabs.add(driver.getWindowHandles().stream().skip(tabs.size()).findFirst().orElseThrow());
    }

    public void openTabsWithUrl(String[] urls) {
        for (String url: urls) {
            openNewTab(url);
        }
    }

    public String waitForElementAndGetText(By element) {
        longWait35.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getAttribute("innerText");
    }

    public void sleepForSomeTime(int i) {
        try {
            Thread.sleep(i * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
