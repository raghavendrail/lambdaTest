package tests;

import base.BaseDriver;
import pages.SpeedTestPage;
import org.testng.annotations.Test;

public class DownloadTest extends BaseDriver {

    @Test
    public void downloadSpeedTest() throws Exception {

        startDriver("Download Test");

        SpeedTestPage page = new SpeedTestPage(driver);

        page.openBrowser();
        page.enterUrl();
        page.clickFind();
        page.clickTestMyInternet();
        page.clickDownload();

        Thread.sleep(10000);

        String speed = page.getSpeed();
        System.out.println("DOWNLOAD SPEED = " + speed);

        quitDriver();
    }
}
