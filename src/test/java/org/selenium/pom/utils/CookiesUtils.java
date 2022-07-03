package org.selenium.pom.utils;

import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;

public class CookiesUtils {

    //Covert cookies of restassured to cookie of selenium
    public List<Cookie> coventCookiesOfRestAssuredToCookiesOfSelenium(Cookies cookies) {
        List<io.restassured.http.Cookie> restAssuredCookies;

        restAssuredCookies = cookies.asList();

        List<Cookie> seleniumCookie = new ArrayList<>();

        for (io.restassured.http.Cookie restAssuredCookie : restAssuredCookies) {
            seleniumCookie.add(new Cookie(restAssuredCookie.getName(), restAssuredCookie.getValue(), restAssuredCookie.getDomain(), restAssuredCookie.getPath(), restAssuredCookie.getExpiryDate(), restAssuredCookie.isSecured(), restAssuredCookie.isHttpOnly(), restAssuredCookie.getSameSite()));
        }

        return seleniumCookie;
    }
}
