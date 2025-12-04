package com.raghavendra.lambdatest.tests;

import com.raghavendra.lambdatest.base.DriverFactory;
import com.raghavendra.lambdatest.listeners.RetryAnalyzer;
import com.raghavendra.lambdatest.pages.SpeedTestPage;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UploadTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyUploadSpeed() {
        AppiumDriver driver = DriverFactory.getDriver();
        SpeedTestPage page = new SpeedTestPage(driver);

        page.openBrowser();
        page.enterUrlAndSearch();
        page.startInternetTest();

        String speedText = page.runSpeedTest("UPLOAD");
        System.out.println("UPLOAD speed: " + speedText);

        Assert.assertNotNull(speedText, "Upload speed should not be null");
        Assert.assertTrue(speedText.toLowerCase().contains("mbps"),
                "Upload speed text should contain 'Mbps'");
    }
}
