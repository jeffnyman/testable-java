package com.testerstories.pages;

import com.testerstories.DriverBase;
import com.testerstories.helpers.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Authentication extends BasePage {
    private Element openLogin = new Element(By.id("open"), driver);
    private Element username = new Element(By.id("username"), driver);
    private Element password = new Element(By.id("password"), driver);
    private Element loginButton = new Element(By.id("login-button"), driver);
    private Element logoutButton = new Element(By.linkText("Logout"), driver);

    public void login_as(String user, String pass) {
        WebDriverWait wait = new WebDriverWait(DriverBase.getDriver(), 10, 500);

        openLogin.locate().click();

        wait.until(ExpectedConditions.visibilityOf(username.locate()));

        username.locate().sendKeys(user);
        password.locate().sendKeys(pass);
        loginButton.locate().submit();
    }

    public void logout() {
        WebDriverWait wait = new WebDriverWait(DriverBase.getDriver(), 10, 500);

        openLogin.locate().click();

        wait.until(ExpectedConditions.elementToBeClickable(logoutButton.locator()));

        logoutButton.locate().click();
    }
}
