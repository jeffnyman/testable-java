package com.testerstories.pages;

import com.testerstories.helpers.Element;
import org.openqa.selenium.By;

public class HomePage extends BasePage {
    private Element siteLogo = new Element(By.id("site-image"), driver);
    private Element notice = new Element(By.className("notice"), driver);

    public Authentication authentication = new Authentication();

    public boolean siteLogoDisplayed() {
        return siteLogo.locateAll().size() == 1;
    }

    public String confirmLogout() {
        selenium.waitForPresent(notice.locator());
        return notice.locate().getText();
    }
}
