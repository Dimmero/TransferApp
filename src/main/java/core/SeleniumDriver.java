package core;

import baseElements.BaseAbstractPage;
import baseElements.BaseObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;


public class SeleniumDriver extends BaseObject {
    private WebDriver driver;
    private final WebDriverWait wait;
    public static List<String> tabs;
    protected final String HEADLESS_CHROME = "headless";
    protected final String CHROMEDRIVER_PATH = "/usr/local/bin/chromedriver";

    public SeleniumDriver() {
        runChromeDriver(false);
        this.wait = new WebDriverWait(this.driver, DEFAULT_TIMEOUT);
        tabs = new ArrayList<>(driver.getWindowHandles());
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public WebDriverWait getWait() {
        return this.wait;
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

    public void openNewTab(String url, int tab) {
        String URL = "'" + url + "'";
        String jsScript = "window.open(" + URL + ", '_blank');";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(jsScript);
        wait.until(ExpectedConditions.numberOfWindowsToBe(tabs.size() + 1));
        tabs.add(driver.getWindowHandles().stream().skip(tabs.size()).findFirst().orElseThrow());
//        this.driver.switchTo().window(tabs.get(tab));
    }

    public void openTransferAndStatsTabs() {
        openNewTab(BaseAbstractPage.URL_TRANSFER, 1);
        openNewTab(BaseAbstractPage.URL_STATS, 2);
    }

    public String waitForElementAndGetText(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getAttribute("innerText");
    }

    public void sleepForSomeTime(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
