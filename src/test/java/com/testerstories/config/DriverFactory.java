package com.testerstories.config;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.testerstories.config.DriverType.FIREFOX;
import static com.testerstories.config.DriverType.valueOf;

public class DriverFactory {
    private RemoteWebDriver driver;
    private DriverType selectedDriver;
    private final boolean useRemote = Boolean.getBoolean("remote");

    public DriverFactory() {
        DriverType driverType = FIREFOX;
        String browser = System.getProperty("browser", driverType.toString()).toUpperCase();

        try {
            driverType = valueOf(browser);
        } catch (IllegalArgumentException ignored) {
            System.err.println("Unknown driver specified. Defaulting to '" + driverType + "'.");
        } catch (NullPointerException ignored) {
            System.err.println("No driver specified. Defaulting to '" + driverType + "'.");
        }

        selectedDriver = driverType;
    }

    public RemoteWebDriver getDriver() {
        if (driver == null) {
            establishDriver(selectedDriver);
        }
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private void establishDriver(DriverType driverType) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        URL gridURL = null;

        if (useRemote) {
            try {
                gridURL = new URL(System.getProperty("gridURL"));
            } catch (MalformedURLException ignored) {
                System.err.println("Invalid grid URL provided.");
            }

            String gridPlatform = System.getProperty("gridPlatform");
            String gridBrowserVersion = System.getProperty("gridBrowserVersion");

            if (gridPlatform != null && !gridPlatform.isEmpty()) {
                desiredCapabilities.setPlatform(Platform.valueOf(gridPlatform.toUpperCase()));
            }

            if (gridBrowserVersion != null && !gridBrowserVersion.isEmpty()) {
                desiredCapabilities.setVersion(gridBrowserVersion);
            }

            desiredCapabilities.setBrowserName(selectedDriver.toString());
            driver = new RemoteWebDriver(gridURL, desiredCapabilities);
        } else {
            driver = driverType.getWebDriver(desiredCapabilities);
        }
    }
}
