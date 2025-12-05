package tests;

import org.testng.annotations.Test;
import pages.SpeedTestPage;
import base.BaseDriver;

public class UploadTest extends BaseDriver {

    @Test
    public void uploadSpeedTest() throws Exception {

        startDriver("Upload Test");

        SpeedTestPage page = new SpeedTestPage(driver);

        page.openBrowser();
        page.enterUrl();
        page.clickFind();
        page.clickTestMyInternet();
        page.clickUpload();

        Thread.sleep(10000);

        String speed = page.getSpeed();
        System.out.println("UPLOAD SPEED = " + speed);

        quitDriver();
    }
}
