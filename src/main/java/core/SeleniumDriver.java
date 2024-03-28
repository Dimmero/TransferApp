package core;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;


public class SeleniumDriver {
    public WebDriver driver;
    public WebDriver driver1;
    private final WebDriverWait longWait35;
    private final WebDriverWait shortWait10;
    public static List<String> tabs;
    protected final String HEADLESS_CHROME = "headless";
    protected final String CHROMEDRIVER_PATH = "/usr/local/bin/chromedriver";
    protected final String GECKODRIVER_PATH = "/usr/local/bin/geckodriver";

    private String USERNAME = "a5d9d299b73bd404151de-zone-custom-region-ru";
    private String PASSWORD = "5be544928b7d2f6a69b216eef32389b0c64e9956";

    private String MANGOPROXY_DNS = "43.152.113.55";
    private int MANGOPROXY_PORT = 2334;

    public SeleniumDriver(boolean headless) {
        runChromeDriver(headless);
        this.longWait35 = new WebDriverWait(this.driver, Duration.ofSeconds(35));
        this.shortWait10 = new WebDriverWait(this.driver, Duration.ofSeconds(10));
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
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
        this.driver = new ChromeDriver(getChromeOptions(headless));
//        System.setProperty("webdriver.gecko.driver", GECKODRIVER_PATH);
//        FirefoxOptions options = new FirefoxOptions();
//        options.addPreference("general.useragent.override", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3");
//        driver = new FirefoxDriver(options);
//        this.driver1 = new FirefoxDriver();
        this.driver.manage().window().maximize();
//        this.driver1.manage().window().maximize();
    }

    private ChromeOptions getChromeOptions(boolean headless) {
//        Proxy proxy = new Proxy();
//        String proxyAuth = USERNAME + ":" + PASSWORD;
//        proxy.setProxyType(Proxy.ProxyType.MANUAL);
//        proxy.setHttpProxy(proxyAuth + "@" + MANGOPROXY_DNS + ":" + MANGOPROXY_PORT);
//        proxy.setSslProxy(proxyAuth + "@" + MANGOPROXY_DNS + ":" + MANGOPROXY_PORT);
//
        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setProxy(proxy);

        // Directly add arguments without creating a separate list
        chromeOptions.addArguments("--log-level=3"); // Correct log-level argument to set the verbosity
        chromeOptions.addArguments("useAutomationExtension=false"); // Correctly disable the automation extension
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-infobars"); // Note: This argument is deprecated and may not have an effect on newer versions
        if (headless) chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--disable-application-cache");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled"); // Corrected typo here
//        chromeOptions.addArguments("--remote-debugging-port=9222");

        // Return the configured ChromeOptions
        return chromeOptions;
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
        for (String url : urls) {
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
