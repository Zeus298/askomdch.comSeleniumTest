package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.compositions.MyHeader;
import org.selenium.pom.pages.compositions.ProductThumbNail;

public class HomePage extends BasePage {
    //Normal - Not using composition
    //private final By storeMenuLink = By.cssSelector("#menu-item-1227 > a");
    //private final By viewCartLink = By.cssSelector(".added_to_cart");

    //Using composition
    private ProductThumbNail productThumbNail;
    private MyHeader myHeader;


    public HomePage (WebDriver driver) {
        super(driver);
        productThumbNail = new ProductThumbNail(driver);
        myHeader = new MyHeader(driver);

    }

    public HomePage load() {
        loadPage("/");
        return this;
    }

    public ProductThumbNail getProductThumbNail() {
        return productThumbNail;
    }

    public MyHeader getMyHeader() {
        return myHeader;
    }

    //Normal - Not using composition
//    public StorePage navigateToStorePageUsingMenu() {
//        waitLong.until(ExpectedConditions.visibilityOfElementLocated(storeMenuLink)).click();
//        return new StorePage(driver);
//    }

//    private By findAddToCartBtn (String productName) {
//        return By.cssSelector("a[aria-label=\"Add “" + productName + "” to your cart\"]");
//    }
//
//    public HomePage clickAddToCartButton(String productName) {
//        By addToCartBtn = findAddToCartBtn(productName);
//        waitLong.until(ExpectedConditions.visibilityOfElementLocated(addToCartBtn)).click();
//        return this;
//    }
//
//    public CartPage clickViewCart() {
//        waitLong.until(ExpectedConditions.visibilityOfElementLocated(viewCartLink)).click();
//        return new CartPage(driver);
//    }


}
