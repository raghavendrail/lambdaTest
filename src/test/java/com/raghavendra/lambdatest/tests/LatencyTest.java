package com.raghavendra.lambdatest.tests;

import com.raghavendra.lambdatest.base.DriverFactory;
import com.raghavendra.lambdatest.listeners.RetryAnalyzer;
import com.raghavendra.lambdatest.pages.SpeedTestPage;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LatencyTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyLatency() {
        AppiumDriver driver = DriverFactory.getDriver();
        SpeedTestPage page = new SpeedTestPage(driver);

        page.openBrowser();
        page.enterUrlAndSearch();
        page.startInternetTest();

        String speedText = page.runSpeedTest("LATENCY");
        System.out.println("LATENCY: " + speedText);

        Assert.assertNotNull(speedText, "Latency result should not be null");
        // Might be ms instead of Mbps, so no strict check here
    }
}
