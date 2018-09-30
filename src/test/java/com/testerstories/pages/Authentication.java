package com.testerstories.pages;

import com.testerstories.DriverBase;
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

    public Authentication() {
        PageFactory.initElements(DriverBase.getDriver(), this);
    }

    public void login_as(String user, String pass) {
        WebDriverWait wait = new WebDriverWait(DriverBase.getDriver(), 10, 500);

        openLogin.click();

        wait.until(ExpectedConditions.visibilityOf(username));

        username.sendKeys(user);
        password.sendKeys(pass);
        loginButton.submit();
    }

    public void logout() {
        WebDriverWait wait = new WebDriverWait(DriverBase.getDriver(), 10, 500);

        openLogin.click();

        wait.until(ExpectedConditions.visibilityOf(logoutButton));
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));

        logoutButton.click();
    }
}
