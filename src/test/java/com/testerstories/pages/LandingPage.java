package com.testerstories.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage {
    @FindBy(className = "notice")
    private WebElement notice;

    public LandingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String confirmLogin(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10, 500);
        wait.until(ExpectedConditions.visibilityOf(notice));
        return notice.getText();
    }
}
