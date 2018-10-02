package com.testerstories.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Element {
    private RemoteWebDriver driver;
    private final By defaultLocator;

    public Element(By defaultLocator, RemoteWebDriver driver) {
        if (defaultLocator == null) {
            throw new NullPointerException("Element locator cannot be null.");
        }
        this.defaultLocator = defaultLocator;
        initializeElement(driver);
    }

    private void initializeElement(RemoteWebDriver driver) {
        this.driver = driver;
    }

    /**
     * Returns a WebElement object if a valid WebElement could be found with
     * the supplied locator.
     *
     * @return WebElement
     */
    public WebElement locate() {
        return driver.findElement(locator());
    }

    /**
     * Returns the locator that is currently associated with the driver object.
     *
     * @return By
     */
    public By locator() {
        checkForDriver();
        return defaultLocator;
    }

    private void checkForDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver not set. You must call 'Element.initializeElement(driver);'");
        }
    }
}
