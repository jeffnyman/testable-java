package com.testerstories;

import com.testerstories.config.DriverFactory;
import com.testerstories.listeners.ScreenshotListener;
import com.testerstories.pages.Authentication;
import com.testerstories.pages.HomePage;
import com.testerstories.pages.LandingPage;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Listeners(ScreenshotListener.class)
public class DriverBase {
    private static List<DriverFactory> threadPool = Collections.synchronizedList(new ArrayList<DriverFactory>());
    private static ThreadLocal<DriverFactory> driverThread;

    Authentication authentication;
    HomePage homePage;
    LandingPage landingPage;

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

    public static RemoteWebDriver getDriver() {
        return driverThread.get().getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public static void clearCookies() {
        try {
            getDriver().manage().deleteAllCookies();
        } catch (Exception ex) {
            System.err.println("Unable to clear cookies: " + ex.getCause());
        }
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
