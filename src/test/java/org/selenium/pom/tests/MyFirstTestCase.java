package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingInfo;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.UserLogin;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckOutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class MyFirstTestCase extends BaseTest {

  @Test
  public void guestCheckoutUsingDirectBankTransfer() throws IOException {

        //HomePage homePage = new HomePage(driver).loadPage();
        HomePage homePage = new HomePage(getDriver()).load();
        //Not using components
        //StorePage storePage = homePage.navigateToStorePageUsingMenu();
        StorePage storePage = homePage.getMyHeader().navigateToStorePageUsingMenu();
        storePage.enterSearchKeyWord("blue").clickButtonSearch();


        Assert.assertEquals(storePage.getTitle(), "Search results: “blue”");

        Product product = new Product(1215);

        //Not using components
        //storePage.clickAddToCartButton(product.getName());
        //storePage.clickAddToCartButton("Blue Shoes");
        //CartPage cartPage = storePage.clickViewCart();

        //Using components
        CartPage cartPage = storePage.getProductThumbNail().clickAddToCartButton(product.getName()).clickViewCart();
        Assert.assertEquals(cartPage.getNameProduct(), "Blue Shoes");

        CheckOutPage checkOutPage = cartPage.clickToButtonCheckout();

        //Use 1
        //BillingInfo billingInfo = new BillingInfo();
        //InputStream inputStream = getClass().getClassLoader().getResourceAsStream("allBillingInfo.json");
        //billingInfo = JacksonUtils.coventStringToObjectJson(inputStream, billingInfo);

        BillingInfo[] billingInfos = JacksonUtils.coventStringToObjectJson("allBillingInfo.json", BillingInfo[].class);
        checkOutPage.enterBillingInfor(billingInfos[0]);

        checkOutPage.chooseDirectBankTrans();

        checkOutPage.clickOrder();
        Assert.assertEquals( checkOutPage.getSuccessMess(), "Thank you. Your order has been received.");

  }

      @Test
      public void guestCheckoutUsingCashOnDeliveryWithLogin() throws IOException {

            HomePage homePage = new HomePage(getDriver()).load();

            //Not using components
            //StorePage storePage = homePage.navigateToStorePageUsingMenu();

            //Using components
            StorePage storePage = homePage.getMyHeader().navigateToStorePageUsingMenu();

            storePage.enterSearchKeyWord("blue").clickButtonSearch();
            Assert.assertEquals(storePage.getTitle(), "Search results: “blue”");

            Product product = new Product(1215);

            // Not using components
            //storePage.clickAddToCartButton(product.getName());
            //storePage.clickAddToCartButton("Blue Shoes");

            //CartPage cartPage = storePage.clickViewCart();

            CartPage cartPage = storePage.getProductThumbNail().clickAddToCartButton(product.getName()).clickViewCart();
            Assert.assertEquals(cartPage.getNameProduct(), "Blue Shoes");


            CheckOutPage checkOutPage = cartPage.clickToButtonCheckout();

            //Use 1
            //BillingInfo billingInfo = new BillingInfo();
            //InputStream inputStream = getClass().getClassLoader().getResourceAsStream("allBillingInfo.json");
            //billingInfo = JacksonUtils.coventStringToObjectJson(inputStream, billingInfo);

            UserLogin userLogin = new UserLogin(0);
            checkOutPage.clickToShowLogin();
            checkOutPage.enterUserName(userLogin.getUserName());
            checkOutPage.enterPassword(userLogin.getPassWord());
            checkOutPage.clickLogin();

            BillingInfo[] billingInfos = JacksonUtils.coventStringToObjectJson("allBillingInfo.json", BillingInfo[].class);
            checkOutPage.enterBillingInfor(billingInfos[0]);
            checkOutPage.chooseCashDelivery();
            checkOutPage.clickOrder();

            Assert.assertEquals( checkOutPage.getSuccessMess(), "Thank you. Your order has been received.");

      }
}
