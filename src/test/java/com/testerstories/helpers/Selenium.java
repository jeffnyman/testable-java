package com.testerstories.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium {
    private RemoteWebDriver driver;

    public Selenium(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void waitForElement(WebElement identifier) {
        WebDriverWait wait = new WebDriverWait(driver, 15, 100);
        wait.until(ExpectedConditions.visibilityOf(identifier));
    }

    public void waitForReady(By identifier) {
        WebDriverWait wait = new WebDriverWait(driver, 15, 100);
        wait.until(ExpectedConditions.elementToBeClickable(identifier));
    }

    public void waitForPresent(By identifier) {
        WebDriverWait wait = new WebDriverWait(driver, 15, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
    }

    public void waitForAJAX() {
        WebDriverWait wait = new WebDriverWait(driver, 15, 100);
        wait.until(jQueryAJAXCallsHaveCompleted());
    }

    private static ExpectedCondition<Boolean> jQueryAJAXCallsHaveCompleted() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver).
                        executeScript("return (window.jQuery != null && (jQuery.active === 0);");
            }
        };
    }
}
