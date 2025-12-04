// BaseTest.java
package com.raghavendra.lambdatest.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.raghavendra.lambdatest.base.DriverFactory;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
