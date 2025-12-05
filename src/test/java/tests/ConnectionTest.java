package tests;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.HashMap;

public class ConnectionTest {

    @Test
    public void testConnection() throws Exception {

        System.out.println("STEP 1: Preparing Appium lt:options");

        // lt:options map
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("username", "lt_test");
        ltOptions.put("accessKey", "LT_MJlsT9lwKlSm4goMrrEWPfeEpGMsBKpradF1mto704q1Hlf");

        ltOptions.put("w3c", true);
        ltOptions.put("platformName", "Android");
        ltOptions.put("deviceName", "Pixel 7");
        ltOptions.put("platformVersion", "13");
        ltOptions.put("isRealMobile", true);

        ltOptions.put("app", "lt://APP1016021681764779038077987");

        ltOptions.put("build", "Mobile Connection Build");
        ltOptions.put("name", "ConnectionTest");

        ltOptions.put("network", true);
        ltOptions.put("visual", true);
        ltOptions.put("devicelog", true);

        // Top-level caps
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("LT:Options", ltOptions);

        System.out.println("STEP 2: Creating session...");

        AndroidDriver driver = new AndroidDriver(
                new URL("https://mobile-hub.lambdatest.com/wd/hub"),
                caps
        );

        System.out.println("SESSION CREATED!");
        System.out.println("Session ID = " + driver.getSessionId());

        Thread.sleep(5000);
        driver.quit();
    }
}
