package core;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SeleniumDriver {
    private WebDriver driver;
    private WebDriverWait wait;

    public SeleniumDriver() {
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, 5);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void quitDriver() {
        driver.quit();
    }

    public void initDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        driver.manage().window().setSize(new Dimension(500, 800));
    }

    public void openNewTab(String url) {
        String URL_TRANSFER = "'" + url + "'";
        String jsScript = "window.open(" + URL_TRANSFER + ", '_blank');";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript( jsScript);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public void waitForSelect() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    public void waitForElementVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void closeDriver() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }
}
