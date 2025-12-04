package com.raghavendra.lambdatest.tests;

import com.raghavendra.lambdatest.base.DriverFactory;
import com.raghavendra.lambdatest.listeners.RetryAnalyzer;
import com.raghavendra.lambdatest.pages.SpeedTestPage;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CombinedTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyCombinedSpeed() {
        AppiumDriver driver = DriverFactory.getDriver();
        SpeedTestPage page = new SpeedTestPage(driver);

        page.openBrowser();
        page.enterUrlAndSearch();
        page.startInternetTest();

        String speedText = page.runSpeedTest("COMBINED");
        System.out.println("COMBINED speed: " + speedText);

        Assert.assertNotNull(speedText, "Combined speed should not be null");
        Assert.assertTrue(speedText.toLowerCase().contains("mbps"),
                "Combined speed text should contain 'Mbps'");
    }
}
