package com.raghavendra.lambdatest.pages;

import com.raghavendra.lambdatest.enums.TestType;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SpeedTestPage {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    // TODO: replace these with real IDs from LambdaTest inspector
    private final By browserIcon      = By.id("REPLACE_BROWSER_ICON_ID");
    private final By enterWebNameBox  = By.id("REPLACE_ENTER_WEB_NAME_ID");
    private final By findButton       = By.id("REPLACE_FIND_BUTTON_ID");
    private final By testMyInternetBtn= By.id("REPLACE_TEST_MY_INTERNET_ID");

    private By testTypeButton(TestType type) {
        String text = type.name(); // DOWNLOAD / UPLOAD / COMBINED / LATENCY
        return By.xpath("//*[contains(translate(., 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'"
                + text + "')]");
    }

    private final By resultValue = By.xpath("//*[contains(@class,'result') or contains(.,'Mbps') or contains(.,'ms')]");

    public SpeedTestPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void openBrowser() {
        wait.until(ExpectedConditions.elementToBeClickable(browserIcon)).click();
    }

    public void enterUrlAndSearch() {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(enterWebNameBox));
        input.click();
        input.clear();
        input.sendKeys("https://testmy.net/");
        wait.until(ExpectedConditions.elementToBeClickable(findButton)).click();

        try {
            driver.hideKeyboard();
        } catch (Exception ignored) {
        }
    }

    public void startInternetTest() {
        wait.until(ExpectedConditions.elementToBeClickable(testMyInternetBtn)).click();
    }

    public String runSpeedTest(TestType type) {
        wait.until(ExpectedConditions.elementToBeClickable(testTypeButton(type))).click();
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(resultValue));
        String text = result.getText();
        System.out.println(type.name() + " result: " + text);
        return text;
    }
}
