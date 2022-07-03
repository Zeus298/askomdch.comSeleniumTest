package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataprovider.MyDataProvider;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class AddToCart extends BaseTest {
    @Test (dataProvider = "getDataFeaturedProducts", dataProviderClass = MyDataProvider.class)
    public void addToCartFromStorePage(Product product) throws IOException {

        //Product product = new Product(1215);
        StorePage storePage = new StorePage(getDriver()).load();

        // Not using compositions
        //storePage.clickAddToCartButton(product.getName());
        //CartPage cartPage = storePage.clickViewCart();

        //Using compositions

        // Using compositions
        CartPage cartPage = storePage.getProductThumbNail().clickAddToCartButton(product.getName()).clickViewCart();
        Assert.assertEquals(cartPage.getNameProduct(), product.getName());
    }

//    @DataProvider(name = "getDataFeaturedProducts", parallel=true)
//    // TestNG can't return Object[] type so solve this in link https://stackoverflow.com/questions/40023679/dataprovider-returning-object-does-not-match-argument-type
//    public Iterator<Object[]> getDataFeaturedProducts() throws IOException {
//        //return new Object[][] { JacksonUtils.coventStringToObjectJson("product.json", Product[].class) };
//        Product[] products = JacksonUtils.coventStringToObjectJson("product.json", Product[].class);
//        List<Object[]> objects = new ArrayList<>();
//        for (Product product : products) {
//            objects.add(new Object[]{product});
//        }
//        return objects.iterator();
//
//    }


    @Test(dataProvider = "getDataFeaturedProducts", dataProviderClass = MyDataProvider.class)
    public void addToCartFeatureProductFromHomePage(Product product) throws IOException {
        //Product product = new Product(1198);
        HomePage homePage = new HomePage(getDriver()).load();
        //Not using components
//        homePage.clickAddToCartButton(product.getName());
//
//        CartPage cartPage = homePage.clickViewCart();

        //Using components
        CartPage cartPage = homePage.getProductThumbNail().clickAddToCartButton(product.getName()).clickViewCart();
        Assert.assertEquals(cartPage.getNameProduct(), product.getName());

    }

//    @DataProvider(name = "getFeaturedProducts")
//    public Product[] getFeatureProduct() { //If we keep this, throw error Data provider must return Object[][] or Iterator<Object[]>
//        return JacksonUtils.coventStringToObjectJson("product.json", Product[].class)
//    }



}
