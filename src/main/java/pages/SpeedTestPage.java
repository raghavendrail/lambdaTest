package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class SpeedTestPage {

    AndroidDriver driver;

    public SpeedTestPage(AndroidDriver driver) {
        this.driver = driver;
    }

    By browserIcon = By.id("com.lambdatest.proverbial:id/browser");
    By urlField = By.id("com.lambdatest.proverbial:id/url");
    By findButton = By.id("com.lambdatest.proverbial:id/find");

    By testMyInternet = By.xpath("//*[@text='TEST MY INTERNET']");
    By downloadBtn = By.xpath("//*[@text='DOWNLOAD']");
    By uploadBtn = By.xpath("//*[@text='UPLOAD']");
    By combinedBtn = By.xpath("//*[@text='COMBINED']");
    By latencyBtn = By.xpath("//*[@text='LATENCY']");

    By speedValue = By.xpath("//*[@resource-id='speed-value' or contains(@text,'Mbps')]");

    public void openBrowser() {
        driver.findElement(browserIcon).click();
    }

    public void enterUrl() {
        driver.findElement(urlField).sendKeys("https://testmy.net/");
    }

    public void clickFind() {
        driver.findElement(findButton).click();
    }

    public void clickTestMyInternet() {
        driver.findElement(testMyInternet).click();
    }

    public void clickDownload() {
        driver.findElement(downloadBtn).click();
    }

    public void clickUpload() {
        driver.findElement(uploadBtn).click();
    }

    public void clickCombined() {
        driver.findElement(combinedBtn).click();
    }

    public void clickLatency() {
        driver.findElement(latencyBtn).click();
    }

    public String getSpeed() {
        return driver.findElement(speedValue).getText();
    }
}
