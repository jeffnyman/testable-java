package com.testerstories.pages;

import com.testerstories.DriverBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage {
    @FindBy(className = "notice")
    private WebElement notice;

    public LandingPage() {
        PageFactory.initElements(DriverBase.getDriver(), this);
    }

    public String confirmLogin() {
        WebDriverWait wait = new WebDriverWait(DriverBase.getDriver(), 10, 500);
        wait.until(ExpectedConditions.visibilityOf(notice));
        return notice.getText();
    }
}
