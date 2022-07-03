package org.selenium.pom.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.compositions.ProductThumbNail;

import java.util.regex.Pattern;

public class StorePage extends BasePage {
    private final By searchInput = By.cssSelector("#woocommerce-product-search-field-0");
    private final By searchButton = By.cssSelector("button[value=Search]");
    private final By searchResultTitle = By.cssSelector(".woocommerce-products-header__title");

    private final By viewCartLink = By.cssSelector("a[title='View cart']");

    private ProductThumbNail productThumbNail;

    public ProductThumbNail getProductThumbNail () {
        return productThumbNail;
    }
    private final By titleUniqueOfSearchPge = By.cssSelector(".page-title");
    public StorePage(WebDriver driver) {
        super(driver);
        productThumbNail = new ProductThumbNail(driver);
    }

    public StorePage enterSearchKeyWord(String keyWord) {
        waitLong.until(ExpectedConditions.visibilityOfElementLocated(searchInput)).sendKeys("blue");
        return this;
    }

    public StorePage clickButtonSearch() {
        waitLong.until(ExpectedConditions.visibilityOfElementLocated(searchButton)).click();
        return this;
    }

    public String getTitle() {
        waitLong.until(ExpectedConditions.textMatches(searchResultTitle, Pattern.compile("[Ss]earch")));
        return driver.findElement(searchResultTitle).getText();
    }

    //Using composition instead
//    private By findAddToCartBtn (String productName) {
//        return By.cssSelector("a[aria-label=\"Add “" + productName + "” to your cart\"]");
//    }


//    public StorePage clickAddToCartButton(String productName) {
//        By addToCartBtn = findAddToCartBtn(productName);
//        waitLong.until(ExpectedConditions.visibilityOfElementLocated(addToCartBtn)).click();
//        return this;
//    }
//
//    public CartPage clickViewCart() {
//        waitLong.until(ExpectedConditions.visibilityOfElementLocated( viewCartLink)).click();
//        return new CartPage(driver);
//    }
//
    public String getTitleUniquePage() {
        return waitLong.until(ExpectedConditions.visibilityOfElementLocated(titleUniqueOfSearchPge)).getText();
    }

    public StorePage load() {
        loadPage("/store");
        return this;
    }

}
