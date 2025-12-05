package tests;

import org.testng.annotations.Test;
import pages.SpeedTestPage;
import base.BaseDriver;

public class CombinedTest extends BaseDriver {

    @Test
    public void combinedSpeedTest() throws Exception {

        startDriver("Combined Test");

        SpeedTestPage page = new SpeedTestPage(driver);

        page.openBrowser();
        page.enterUrl();
        page.clickFind();
        page.clickTestMyInternet();

        // ---- DOWNLOAD TEST ----
        page.clickDownload();
        Thread.sleep(10000);
        String downloadSpeed = page.getSpeed();
        System.out.println("DOWNLOAD SPEED = " + downloadSpeed);

        // ---- UPLOAD TEST ----
        page.clickUpload();
        Thread.sleep(10000);
        String uploadSpeed = page.getSpeed();
        System.out.println("UPLOAD SPEED = " + uploadSpeed);

        quitDriver();
    }
}
