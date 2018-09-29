package com.testerstories.config;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.testerstories.config.DriverType.FIREFOX;
import static com.testerstories.config.DriverType.valueOf;

public class DriverFactory {
    private RemoteWebDriver driver;
    private DriverType selectedDriver;

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
        System.out.println("Current thread: " + Thread.currentThread().getId());

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
        driver = driverType.getWebDriver(desiredCapabilities);
    }
}
