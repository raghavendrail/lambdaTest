package base;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.HashMap;

public class BaseDriver {

    public static AndroidDriver driver;

    public void startDriver(String testName) throws Exception {

        System.out.println("STEP 1: Preparing Appium LT:Options");

        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("username", "lt_test");
        ltOptions.put("accessKey", "LT_MJlsT9lwKlSm4goMrrEWPfeEpGMsBKpradF1mto704q1Hlf");

        ltOptions.put("w3c", true);
        ltOptions.put("platformName", "Android");
        ltOptions.put("deviceName", "Pixel 7");
        ltOptions.put("platformVersion", "13");
        ltOptions.put("isRealMobile", true);

        ltOptions.put("app", "lt://APP1016021681764779038077987");

        ltOptions.put("build", "Speed Test Assignment");
        ltOptions.put("name", testName);

        ltOptions.put("network", true);
        ltOptions.put("visual", true);
        ltOptions.put("devicelog", true);

        System.out.println("STEP 2: Attaching LT:Options to DesiredCapabilities");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("LT:Options", ltOptions);

        System.out.println("STEP 3: Creating Appium session...");

        driver = new AndroidDriver(
                new URL("https://mobile-hub.lambdatest.com/wd/hub"),
                caps
        );

        System.out.println("SESSION CREATED!");
        System.out.println("Session ID = " + driver.getSessionId());
    }

    public void quitDriver() {
        if (driver != null) {
            System.out.println("STEP 4: Closing session...");
            driver.quit();
            System.out.println("SESSION CLOSED!");
        }
    }
}
