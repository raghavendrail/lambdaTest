package com.raghavendra.lambdatest.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.MutableCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static void initDriver() {

        // 🔴 TEMP: hardcoded creds for development ONLY
        String username = "lt_test";
        String accessKey = "LT_MJlsT9lwKlSm4goMrrEWPfeEpGMsBKpradF1mto704q1Hlf";

        String hubUrl = "https://" + username + ":" + accessKey + "@mobile-hub.lambdatest.com/wd/hub";

        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "Android");	
        caps.setCapability("deviceName", "Galaxy S11");
        caps.setCapability("platformVersion", "12");
        caps.setCapability("isRealMobile", true);

        // Use app public URL (from email)
        caps.setCapability("app", "lt://APP1016060671764797351937344");

        caps.setCapability("build", "LambdaTest Mobile Assignment");
        caps.setCapability("name", "SpeedTest Connection Test");

        try {
            AndroidDriver androidDriver = new AndroidDriver(new URL(hubUrl), caps);
            driver.set(androidDriver);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid LambdaTest hub URL", e);
        }
    }

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
