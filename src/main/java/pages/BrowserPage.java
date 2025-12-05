package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserPage {

    private RemoteWebDriver driver;

    public BrowserPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    By browserIcon = By.id("com.lambdatest.proverbial:id/browser");
    By urlField = By.id("com.lambdatest.proverbial:id/url");
    By findBtn = By.id("com.lambdatest.proverbial:id/find");
    By testMyInternetBtn = By.xpath("//*[@text='TEST MY INTERNET']");
    By downloadBtn = By.xpath("//*[@text='DOWNLOAD']");
    By speedValue = By.id("com.android.chrome:id/infobar_icon");

    public void openBrowser() {
        driver.findElement(browserIcon).click();
    }

    public void enterUrl(String url) {
        driver.findElement(urlField).click();
        driver.findElement(urlField).sendKeys(url);
    }

    public void clickFind() {
        driver.findElement(findBtn).click();
    }

    public void clickTestMyInternet() {
        driver.findElement(testMyInternetBtn).click();
    }

    public void clickDownload() {
        driver.findElement(downloadBtn).click();
    }

    public String getSpeedValue() {
        return driver.findElement(speedValue).getText();
    }
}
