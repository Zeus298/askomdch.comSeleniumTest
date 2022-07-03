package org.selenium.pom.tests;

import org.checkerframework.checker.units.qual.C;
import org.selenium.pom.api.actions.AddToCartAPI;
import org.selenium.pom.api.actions.EditBillingInfoAPI;
import org.selenium.pom.api.actions.SignUpAPI;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataprovider.MyDataProvider;
import org.selenium.pom.objects.BillingInfo;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.UserLogin;
import org.selenium.pom.pages.CheckOutPage;
import org.selenium.pom.utils.FakerUtils;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckOutTestUsingAPI extends BaseTest  {

    @Test(dataProvider="getDataBillingInfo", dataProviderClass = MyDataProvider.class)
    public void guestCheckOutUsingDirectBankTransfer(BillingInfo billingInfo) throws IOException, InterruptedException {
        AddToCartAPI addToCartAPI = new AddToCartAPI();
        CheckOutPage checkOutPage = new CheckOutPage(getDriver());

        //BillingInfo[] billingInfos = JacksonUtils.coventStringToObjectJson("allBillingInfo.json", BillingInfo[].class);

        checkOutPage.load();

        Thread.sleep(3000);

        addToCartAPI.addProductToCartUsingAPI(1215, 1);
        injectCookiesToBrowsers(addToCartAPI.getCookies());

        Thread.sleep(3000);

        checkOutPage.load();
        //checkOutPage.enterBillingInfor(billingInfos[0]);

        checkOutPage.enterBillingInfor(billingInfo);
        checkOutPage.chooseDirectBankTrans();
        checkOutPage.clickOrder();
        Assert.assertEquals( checkOutPage.getSuccessMess(), "Thank you. Your order has been received.");
    }

    @Test
    public void loginAndCheckoutUsingBillingInfoFromAPI() throws IOException, InterruptedException {
        UserLogin user = new UserLogin();
        FakerUtils faker = new FakerUtils();
        FakerUtils faker2 = new FakerUtils();
        SignUpAPI signUpApi = new SignUpAPI();
        Product product = new Product(1215);
        BillingInfo[] billingInfo = JacksonUtils.coventStringToObjectJson("allBillingInfo.json", BillingInfo[].class);

        user.setUserName("chien1234" + faker.generateRandomNumber() + faker2.generateRandomNumber());
        user.setPassWord("chien1234");
        user.setGmail( user.getUserName() + "@gmail.com");
        signUpApi.register(user);


        EditBillingInfoAPI editBillingInfoAPI = new EditBillingInfoAPI(signUpApi.getCookies());
        editBillingInfoAPI.editUsingAPI(billingInfo[0]);

        CheckOutPage checkOutPage = new CheckOutPage(getDriver()).load();

        Thread.sleep(3000);
        AddToCartAPI addToCartAPI =  new AddToCartAPI(editBillingInfoAPI.getCookies());
        addToCartAPI.addProductToCartUsingAPI(product.getId(), 1);

        injectCookiesToBrowsers(addToCartAPI.getCookies());

        Thread.sleep(3000);
        checkOutPage.load();

        Thread.sleep(3000);
        checkOutPage.load()
                    .clickToShowLogin()
                .enterUserName(user.getUserName())
                .enterPassword(user.getPassWord())
                .clickLogin();

        Assert.assertTrue(checkOutPage.getProductName().contains(product.getName()));
        Thread.sleep(1000);
        checkOutPage.clickOrder();
        Assert.assertEquals( checkOutPage.getSuccessMess(), "Thank you. Your order has been received.");

        //CheckOutPage checkOutPage = new CheckOutPage(getDriver()).load();




    }
}
