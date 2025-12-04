package com.raghavendra.lambdatest.listeners;

import com.raghavendra.lambdatest.base.DriverFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("[TEST STARTED] " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("[TEST PASSED] " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("[TEST FAILED] " + result.getName());
        takeScreenshot(result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("[TEST SKIPPED] " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("=== TEST SUITE STARTED: " + context.getName() + " ===");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("=== TEST SUITE FINISHED: " + context.getName() + " ===");
    }

    private void takeScreenshot(String testName) {
        try {
            AppiumDriver driver = DriverFactory.getDriver();
            if (driver == null) {
                System.out.println("Driver is null, cannot capture screenshot.");
                return;
            }

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path destDir = Paths.get("screenshots");
            if (!Files.exists(destDir)) {
                Files.createDirectories(destDir);
            }
            Path destPath = destDir.resolve(testName + "_" + System.currentTimeMillis() + ".png");
            Files.copy(srcFile.toPath(), destPath);
            System.out.println("Screenshot saved at: " + destPath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }
}
