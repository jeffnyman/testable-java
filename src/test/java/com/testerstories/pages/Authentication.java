package com.testerstories.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Authentication {
    private static By openLogin = By.id("open");
    private static By username = By.id("username");
    private static By password = By.id("password");
    private static By login = By.id("login-button");
    private static By logOut = By.linkText("Logout");

    public static void login_as(String user, String pass, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10, 500);

        driver.findElement(openLogin).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(username));

        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(login).submit();
    }

    public static void logout(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10, 500);

        driver.findElement(openLogin).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(logOut));

        driver.findElement(logOut).click();
    }
}
