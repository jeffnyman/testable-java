package com.testerstories.pages;

import com.testerstories.DriverBase;
import com.testerstories.helpers.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage extends BasePage {
    private Element notice = new Element(By.className("notice"), driver);

    public String confirmLogin() {
        WebDriverWait wait = new WebDriverWait(DriverBase.getDriver(), 10, 500);
        wait.until(ExpectedConditions.visibilityOfElementLocated(notice.locator()));
        return notice.locate().getText();
    }
}
