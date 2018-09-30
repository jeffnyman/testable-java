package com.testerstories.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage {
    private static By notice = By.className("notice");

    public static String confirmLogin(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10, 500);
        wait.until(ExpectedConditions.visibilityOfElementLocated(notice));
        return driver.findElement(notice).getText();
    }
}
