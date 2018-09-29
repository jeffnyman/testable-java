package com.testerstories;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicTest extends DriverBase {
    private void login(final String user, final String pass) {
        WebDriver driver = DriverBase.getDriver();

        driver.get("https://veilus.herokuapp.com");

        WebElement openLogin = driver.findElement(By.id("open"));
        openLogin.click();

        WebDriverWait wait = new WebDriverWait(driver, 10, 500);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys(user);

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(pass);

        WebElement login = driver.findElement(By.id("login-button"));
        login.submit();

        wait = new WebDriverWait(driver, 10, 500);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notice")));

        WebElement notice = driver.findElement(By.className("notice"));

        assertThat(notice.getText()).isEqualTo("You are now logged in as " + user + ".");
    }

    private void logout() {
        WebDriver driver = DriverBase.getDriver();

        WebElement openLogin = driver.findElement(By.id("open"));
        openLogin.click();

        WebElement logout = driver.findElement(By.linkText("Logout"));
        logout.click();
    }

    @Test
    public void login_as_admin() {
        login("admin", "admin");
    }

    @Test
    public void login_as_tester() {
        login("tester", "testing");
    }

    @AfterMethod
    public void perform_logout() {
        logout();
    }
}
