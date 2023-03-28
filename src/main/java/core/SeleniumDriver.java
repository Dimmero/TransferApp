package core;

import BaseElements.BaseObject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SeleniumDriver extends BaseObject {
    private WebDriver driver;
    private WebDriverWait wait;
    public static ArrayList<String> tabs;
    protected final String HEADLESS_CHROME = "headless";
    protected final String CHROMEDRIVER_PATH = "/usr/local/bin/chromedriver";

    public SeleniumDriver() {
        runChromeDriver(false);
        this.wait = new WebDriverWait(this.driver, DEFAULT_TIMEOUT);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return this.wait;
    }

    public void quitDriver() {
        driver.quit();
    }

    public void runChromeDriver(boolean headless) {
        System.setProperty(HEADLESS_CHROME, "false");
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--" + HEADLESS_CHROME);
        }
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void openNewTab(String url, int tab) {
        String URL_TRANSFER = "'" + url + "'";
        String jsScript = "window.open(" + URL_TRANSFER + ", '_blank');";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(jsScript);
        tabs = new ArrayList<>(driver.getWindowHandles());
        this.driver.switchTo().window(tabs.get(tab));
    }

    public String waitForElementAndGetText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getAttribute("innerText");
    }

    public void closeTabForStats() {
        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }

    public void sleepForSomeTime(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
