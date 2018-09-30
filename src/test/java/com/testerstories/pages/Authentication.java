package com.testerstories.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Authentication {
    @FindBy(id = "open")
    private WebElement openLogin;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(linkText = "Logout")
    private WebElement logoutButton;

    public Authentication(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void login_as(String user, String pass, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10, 500);

        openLogin.click();

        wait.until(ExpectedConditions.visibilityOf(username));

        username.sendKeys(user);
        password.sendKeys(pass);
        loginButton.submit();
    }

    public void logout(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10, 500);

        openLogin.click();

        wait.until(ExpectedConditions.visibilityOf(logoutButton));

        logoutButton.click();
    }
}
