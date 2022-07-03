package org.selenium.pom.pages.compositions;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.StorePage;

public class MyHeader extends BasePage {
    private final By storeMenuLink = By.cssSelector("#menu-item-1227 > a");

    public MyHeader(WebDriver driver) {
        super(driver);
    }
    public StorePage navigateToStorePageUsingMenu() {
        waitLong.until(ExpectedConditions.visibilityOfElementLocated(storeMenuLink)).click();
        return new StorePage(driver);
    }

}
