package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage {
    private final By productName = By.cssSelector("[data-title=Product]");
    private final By buttonCheckout = By.cssSelector(".checkout-button");


    public CartPage (WebDriver driver) {
        super(driver);
    }

    public String getNameProduct() {
        return waitLong.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
    }

    public CheckOutPage clickToButtonCheckout() {
        waitLong.until(ExpectedConditions.visibilityOfElementLocated(buttonCheckout)).click();
        return new CheckOutPage(driver);
    }




}
