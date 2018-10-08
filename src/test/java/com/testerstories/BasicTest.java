package com.testerstories;

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

        HomePage homePage = new HomePage();
        assertThat(homePage.siteLogoDisplayed()).isEqualTo(true);

        LandingPage landingPage = homePage.authentication.login_as(user, pass);

        String loggedIn = landingPage.confirmLogin();
        assertThat(loggedIn).isEqualTo("You are now logged in as " + user + ".");
    }

    private void logout() {
        LandingPage landingPage = new LandingPage();
        HomePage homePage = landingPage.authentication.logout();

        String loggedOut = homePage.confirmLogout();
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
