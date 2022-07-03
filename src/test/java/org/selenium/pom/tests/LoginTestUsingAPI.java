package org.selenium.pom.tests;

import org.checkerframework.checker.units.qual.C;
import org.selenium.pom.api.actions.AddToCartAPI;
import org.selenium.pom.api.actions.SignUpAPI;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.UserLogin;
import org.selenium.pom.pages.CheckOutPage;
import org.selenium.pom.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestUsingAPI extends BaseTest {

    @Test
    public void loginDuringCheckOut() throws IOException, InterruptedException {
        UserLogin user = new UserLogin();
        FakerUtils faker = new FakerUtils();
        SignUpAPI signUpApi = new SignUpAPI();
        Product product = new Product(1215);

        user.setUserName("chien1234" + faker.generateRandomNumber());
        user.setPassWord("chien1234");
        user.setGmail( user.getUserName() + "@gmail.com");
        signUpApi.register(user);

        AddToCartAPI addToCartAPI =  new AddToCartAPI(signUpApi.getCookies());
        addToCartAPI.addProductToCartUsingAPI(product.getId(), 1);

        CheckOutPage checkOutPage = new CheckOutPage(getDriver()).load();

        Thread.sleep(3000);


        injectCookiesToBrowsers(addToCartAPI.getCookies());

        //CheckOutPage checkOutPage = new CheckOutPage(getDriver()).load();

        Thread.sleep(3000);

        checkOutPage.load()
                    .clickToShowLogin()
                    .enterUserName(user.getUserName())
                    .enterPassword(user.getPassWord())
                    .clickLogin();

        Assert.assertTrue(checkOutPage.getProductName().contains(product.getName()));


    }
}
