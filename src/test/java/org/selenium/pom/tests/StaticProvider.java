package org.selenium.pom.tests;

import org.selenium.pom.objects.Product;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class StaticProvider {

    @DataProvider(name = "getDataFeaturedProducts")
    public static Object[][] getDataFeaturedProducts() throws IOException {
        Product[] products = JacksonUtils.coventStringToObjectJson("product.json", Product[].class);
        return new Object[][] { products };
    }

}
