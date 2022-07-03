package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import org.selenium.pom.api.ApiRequest;
import org.selenium.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class AddToCartAPI {
    private Cookies cookies;

    public Cookies getCookies() {
        return cookies;
    }
    public AddToCartAPI() {}

    public AddToCartAPI(Cookies cookies) {
        this.cookies = cookies;
    }

    public Response addProductToCartUsingAPI(int productId, int quantity) {

        Header header = new Header("content-type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);

        HashMap<String, Object> formToRegister = new HashMap<>();

        formToRegister.put("product_sku", "");
        formToRegister.put("product_id", productId);
        formToRegister.put("quantity", quantity);

        if (cookies == null) {
            cookies = new Cookies();
        }

        Response response = ApiRequest.postRequest("/?wc-ajax=add_to_cart", headers, formToRegister, cookies);

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch account, HTTPS status code is " + response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }
}
