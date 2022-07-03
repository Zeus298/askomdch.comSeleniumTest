package org.selenium.pom.dataprovider;

import org.selenium.pom.objects.BillingInfo;
import org.selenium.pom.objects.Product;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyDataProvider {

    @DataProvider(name = "getDataFeaturedProducts", parallel=false)
    // TestNG can't return Object[] type so solve this in link https://stackoverflow.com/questions/40023679/dataprovider-returning-object-does-not-match-argument-type
    public Iterator<Object[]> getDataFeaturedProducts() throws IOException {
        //return new Object[][] { JacksonUtils.coventStringToObjectJson("product.json", Product[].class) };
        Product[] products = JacksonUtils.coventStringToObjectJson("product.json", Product[].class);
        List<Object[]> objects = new ArrayList<>();
        for (Product product : products) {
            objects.add(new Object[]{product});
        }
        return objects.iterator();

    }

    @DataProvider(name = "getDataBillingInfo", parallel=false)
    public Iterator<Object[]> getDataBillingInfo() throws IOException {
        //return new Object[][] { JacksonUtils.coventStringToObjectJson("product.json", Product[].class) };
        BillingInfo[] billingInfos = JacksonUtils.coventStringToObjectJson("allBillingInfo.json", BillingInfo[].class);
        List<Object[]> objects = new ArrayList<>();
        for (BillingInfo billingInfo : billingInfos) {
            objects.add(new Object[]{billingInfo});
        }
        return objects.iterator();

    }


}
