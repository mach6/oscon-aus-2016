package com.mycompany.myproject.sample.simple;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import com.paypal.selion.annotations.MobileTest;
import com.paypal.selion.platform.grid.Grid;
import com.paypal.selion.platform.utilities.WebDriverWaitUtils;
import com.paypal.selion.reports.runtime.SeLionReporter;

import static org.testng.Assert.*;

public class DemoMobileTest {
    private static final String TEST_APP_PATH =
            "src/test/resources/apps/selendroid-test-app-0.15.0.apk";

    @Test(enabled=false)
    @MobileTest(appName = "browser", device = "android:4.4.4",
            deviceType = "Android Emulator")
    public void selionDemoAppiumAndroid() {
        RemoteWebDriver driver = Grid.driver();
        driver.get("http://selion.io");

        // take screenshot, capture source code, put in report
        SeLionReporter.log("landed", false, false);
    }

    @Test
    @MobileTest(appPath = TEST_APP_PATH, device = "android:19")
    public void testNative() {
        RemoteWebDriver driver = Grid.driver();
        WebDriverWaitUtils.waitUntilElementIsVisible("io.selendroid.testapp:id/my_text_field");
        SeLionReporter.log("Main page", true, true);
        WebElement textField = driver.findElement(By.id("io.selendroid.testapp:id/my_text_field"));
        assertEquals("true", textField.getAttribute("enabled"));
        textField.sendKeys("Appium Android Native Test");
        SeLionReporter.log("Entered text", true, true);
        assertEquals("Appium Android Native Test", textField.getText());
    }

    @Test
    @MobileTest(appName = "safari", device = "iphone:8.1",
            deviceType = "iPhone Simulator")
    public void selionDemoAppiumIOS() {
        RemoteWebDriver driver = Grid.driver();
        driver.get("http://selion.io");

        // take screenshot, capture source code, put in report
        SeLionReporter.log("landed", true, true);
    }
}
