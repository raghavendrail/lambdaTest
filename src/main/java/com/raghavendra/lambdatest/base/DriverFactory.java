package com.raghavendra.lambdatest.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.MutableCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static void initDriver() {

        // Fetch from system environment
        String username = System.getenv("LT_USERNAME");
        String accessKey = System.getenv("LT_ACCESS_KEY");
        String appId = System.getenv("LT_APP_ID");
        String platformName = System.getenv("LT_PLATFORM");
        String deviceName = System.getenv("LT_DEVICE");
        String platformVersion = System.getenv("LT_PLATFORM_VERSION");

        // Validate required fields
        if (username == null || accessKey == null || appId == null) {
            throw new RuntimeException("❌ Required env vars missing. Please set: LT_USERNAME, LT_ACCESS_KEY, LT_APP_ID");
        }

        // LambdaTest Hub URL
        String hubUrl = "https://" + username + ":" + accessKey + "@mobile-hub.lambdatest.com/wd/hub";

        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", platformName != null ? platformName : "Android");
        caps.setCapability("deviceName", deviceName != null ? deviceName : "Galaxy S21");
        caps.setCapability("platformVersion", platformVersion != null ? platformVersion : "12");
        caps.setCapability("isRealMobile", true);
        caps.setCapability("app", appId);

        // Tags (professional touch)
        caps.setCapability("build", "LambdaTest Mobile Assignment");
        caps.setCapability("name", "SpeedTest Automation");
        caps.setCapability("project", "Mobile Speed Test Suite");

        try {
            AndroidDriver androidDriver = new AndroidDriver(new URL(hubUrl), caps);
            driver.set(androidDriver);
            System.out.println("🚀 Driver initialized successfully with LambdaTest cloud device.");
        } catch (MalformedURLException e) {
            throw new RuntimeException("❌ Invalid LambdaTest hub URL", e);
        }
    }

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
            System.out.println("🧹 Driver session cleaned up.");
        }
    }
}
