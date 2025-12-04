// ConnectionTest.java
package com.raghavendra.lambdatest.tests;

import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.raghavendra.lambdatest.base.DriverFactory;

public class ConnectionTest extends BaseTest {

    @Test
    public void verifyAppLaunchesOnLambdaTest() {
        AppiumDriver driver = DriverFactory.getDriver();
        System.out.println("Session ID: " + driver.getSessionId());
        Assert.assertNotNull(driver.getSessionId(), "Driver session should not be null");
    }
}
