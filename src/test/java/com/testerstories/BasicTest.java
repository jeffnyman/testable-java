package com.testerstories;

import com.testerstories.pages.Authentication;
import com.testerstories.pages.HomePage;
import com.testerstories.pages.LandingPage;
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

        WebDriverWait wait = new WebDriverWait(driver, 10, 500);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("site-image")));

        assertThat(HomePage.siteLogo).isNotNull();

        WebElement openLogin = driver.findElement(Authentication.openLogin);
        openLogin.click();

        wait = new WebDriverWait(driver, 10, 500);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

        WebElement username = driver.findElement(Authentication.username);
        username.sendKeys(user);

        WebElement password = driver.findElement(Authentication.password);
        password.sendKeys(pass);

        WebElement login = driver.findElement(Authentication.login);
        login.submit();

        wait = new WebDriverWait(driver, 10, 500);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notice")));

        WebElement notice = driver.findElement(LandingPage.notice);

        assertThat(notice.getText()).isEqualTo("You are now logged in as " + user + ".");
    }

    private void logout() {
        WebDriver driver = DriverBase.getDriver();

        WebElement openLogin = driver.findElement(Authentication.openLogin);
        openLogin.click();

        WebElement logout = driver.findElement(Authentication.logout);

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
