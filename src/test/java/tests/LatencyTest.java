package tests;

import org.testng.annotations.Test;
import pages.SpeedTestPage;
import base.BaseDriver;

public class LatencyTest extends BaseDriver {

    @Test
    public void latencyCheck() throws Exception {

        startDriver("Latency Test");

        SpeedTestPage page = new SpeedTestPage(driver);

        page.openBrowser();
        page.enterUrl();
        page.clickFind();
        page.clickTestMyInternet();

        // PING / LATENCY SECTION
        Thread.sleep(8000);  // wait for values to load

        String latency = page.getSpeed();
        System.out.println("LATENCY = " + latency);

        quitDriver();
    }
}
