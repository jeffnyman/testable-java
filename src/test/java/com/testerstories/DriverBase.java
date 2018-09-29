package com.testerstories;

import com.testerstories.config.DriverFactory;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DriverBase {
    private static List<DriverFactory> threadPool = Collections.synchronizedList(new ArrayList<DriverFactory>());
    private static ThreadLocal<DriverFactory> driverThread;

    @BeforeSuite(alwaysRun = true)
    public static void establishDriver() {
        driverThread = new ThreadLocal<DriverFactory>() {
            protected DriverFactory initialValue() {
                // return new DriverFactory();
                DriverFactory thread = new DriverFactory();
                threadPool.add(thread);
                return thread;
            }
        };
    }

    static RemoteWebDriver getDriver() {
        return driverThread.get().getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public static void clearCookies() {
        getDriver().manage().deleteAllCookies();
    }

    /*@AfterMethod(alwaysRun = true)
    public static void quitDriver() {
        driverThread.get().quitDriver();
    }*/

    @AfterSuite(alwaysRun = true)
    public static void quitDrivers() {
        for (DriverFactory thread : threadPool) {
            thread.quitDriver();
        }
    }
}
