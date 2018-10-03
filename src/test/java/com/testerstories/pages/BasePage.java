package com.testerstories.pages;

import com.testerstories.DriverBase;
import com.testerstories.helpers.Selenium;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract class BasePage {
    RemoteWebDriver driver;
    Selenium selenium;

    BasePage() {
        driver = DriverBase.getDriver();
        selenium = new Selenium(driver);
    }
}
