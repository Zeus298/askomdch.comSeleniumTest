package org.selenium.pom.pages.compositions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.CartPage;

public class ProductThumbNail extends BasePage {

    private final By viewCartLink = By.cssSelector("a[title='View cart']");

    public ProductThumbNail(WebDriver driver) {
        super(driver);
    }

    private By findAddToCartBtn (String productName) {
        return By.cssSelector("a[aria-label=\"Add “" + productName + "” to your cart\"]");
    }

    public ProductThumbNail clickAddToCartButton(String productName) {
        By addToCartBtn = findAddToCartBtn(productName);
        waitLong.until(ExpectedConditions.visibilityOfElementLocated(addToCartBtn)).click();
        return this;
    }

    public CartPage clickViewCart() {
        waitLong.until(ExpectedConditions.visibilityOfElementLocated( viewCartLink)).click();
        return new CartPage(driver);
    }


}
