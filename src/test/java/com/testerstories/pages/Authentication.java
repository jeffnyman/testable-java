package com.testerstories.pages;

import com.testerstories.helpers.Element;
import org.openqa.selenium.By;

public class Authentication extends BasePage {
    private Element openLogin = new Element(By.id("open"), driver);
    private Element username = new Element(By.id("username"), driver);
    private Element password = new Element(By.id("password"), driver);
    private Element loginButton = new Element(By.id("login-button"), driver);
    private Element logoutButton = new Element(By.linkText("Logout"), driver);

    public LandingPage login_as(String user, String pass) {
        openLogin.locate().click();

        selenium.waitForPresent(username.locator());

        username.locate().sendKeys(user);
        password.locate().sendKeys(pass);
        loginButton.locate().submit();
        return new LandingPage();
    }

    public HomePage logout() {
        selenium.waitForReady(openLogin.locator());

        openLogin.locate().click();

        selenium.waitForReady(logoutButton.locator());

        logoutButton.locate().click();

        return new HomePage();
    }
}
