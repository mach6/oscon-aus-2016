package com.mycompany.myproject.sample.simple;

import com.paypal.selion.annotations.WebTest;
import com.paypal.selion.platform.grid.Grid;
import com.paypal.selion.reports.runtime.SeLionReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;

public class DemoWebTest {
    @Test
    public void seleniumDemo() throws Exception {
        // Hard coded to firefox
        DesiredCapabilities caps = DesiredCapabilities.firefox();
        // Hard coded selenium host
        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), caps);
        driver.get("http://google.com");

        String source = driver.getPageSource();
        File file = driver.getScreenshotAs(OutputType.FILE);

        //TODO log the screenshot and source to a report

        // don't forget to close the WebDriver connection
        driver.quit();
    }

    @Test(threadPoolSize = 50, invocationCount = 50)
    @WebTest
    public void selionDemo() {
        // browser determined by config or supplied @ runtime
        RemoteWebDriver driver = Grid.driver();
        driver.get("http://google.com");

        // take screenshot, capture source code, put in report
        SeLionReporter.log("landed", true, true);
    }
}
