package com.raghavendra.lambdatest.base;

import io.appium.java_client.AppiumDriver;

public class SpeedTestPage {

    private AppiumDriver driver;

    public SpeedTestPage(AppiumDriver driver) {
        this.driver = driver;
    }

    public void openBrowser() {
        // TODO: click Browser icon
    }

    public void enterUrlAndSearch() {
        // TODO: type https://testmy.net/ and click FIND
    }

    public void startInternetTest() {
        // TODO: click TEST MY INTERNET
    }

    public String runSpeedTest(String type) {
        // TODO: click DOWNLOAD/UPLOAD/COMBINED/LATENCY
        // TODO: wait, capture speed text, return value
        return null;
    }
}
