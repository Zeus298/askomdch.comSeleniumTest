package org.selenium.pom.api.actions;

import io.restassured.http.*;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.selenium.pom.api.ApiRequest;
import org.selenium.pom.objects.UserLogin;
import org.selenium.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class SignUpAPI {
    private Cookies cookies;

    public Cookies getCookies() {
        return cookies;
    }

    public Response getAccount() {
         Cookies cookies = new Cookies();

        return ApiRequest.getHTML("/account", cookies);
    }


    public String fetchRegisterNonceValueUsingGroovyAndGPath() {
        Response response = getAccount();

        //Use groovy + query language GPath
        return response.htmlPath().getString("**.findAll { it.@name == 'woocommerce-register-nonce'}.@value");

    }


    public String fetchRegisterNonceValueUsingJsoupAndCSSSelector() {
        Response response = getAccount();

        Document doc = Jsoup.parse(response.body().prettyPrint());
        Element element = doc.selectFirst("#woocommerce-register-nonce");
        return  element.attr("value");
    }

    public Response register(UserLogin userLogin) {
        Cookies cookies = new Cookies();
        Header header = new Header("content-type", "application/x-www-form-urlencoded");

        Headers headers = new Headers(header);

        HashMap <String, Object> formToRegister = new HashMap<>();
        formToRegister.put("username", userLogin.getUserName());
        formToRegister.put("password", userLogin.getPassWord());
        formToRegister.put("email", userLogin.getGmail());
        formToRegister.put("woocommerce-register-nonce", fetchRegisterNonceValueUsingJsoupAndCSSSelector());
        formToRegister.put("register", "Register");

        Response response = ApiRequest.postRequest("/account", headers, formToRegister, cookies);

        if (response.getStatusCode() != 302) {
            throw new RuntimeException("Failed to fetch account, HTTPS status code is " + response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }

}
