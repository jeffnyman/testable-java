package com.testerstories.pages;

import com.testerstories.helpers.Element;
import org.openqa.selenium.By;

public class LandingPage extends BasePage {
    private Element notice = new Element(By.className("notice"), driver);

    public String confirmLogin() {
        selenium.waitForPresent(notice.locator());
        return notice.locate().getText();
    }
}
