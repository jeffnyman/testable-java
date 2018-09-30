package com.testerstories;

import com.testerstories.pages.Authentication;
import com.testerstories.pages.HomePage;
import com.testerstories.pages.LandingPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicTest extends DriverBase {
    private void login(final String user, final String pass) {
        WebDriver driver = DriverBase.getDriver();

        driver.get("https://veilus.herokuapp.com");

        Authentication authentication = new Authentication(driver);
        authentication.login_as(user, pass, driver);

        LandingPage landingPage = new LandingPage(driver);
        String loggedIn = landingPage.confirmLogin(driver);
        assertThat(loggedIn).isEqualTo("You are now logged in as " + user + ".");
    }

    private void logout() {
        WebDriver driver = DriverBase.getDriver();

        Authentication authentication = new Authentication(driver);
        authentication.logout(driver);

        HomePage homePage = new HomePage(driver);
        String loggedOut = homePage.confirmLogout(driver);
        assertThat(loggedOut).isEqualTo("You have been logged out.");
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
