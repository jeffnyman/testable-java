package com.testerstories.pages;

import com.testerstories.DriverBase;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract class BasePage {
    RemoteWebDriver driver;

    BasePage() {
        driver = DriverBase.getDriver();
    }
}
