package org.selenium.pom.api;

import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.selenium.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiRequest extends SpecBuilder{

    public static Response postRequest(String endPointURL, Headers headers, HashMap<String, Object> form, Cookies cookies) {
        Response response = given(getRequestSpec())
                .headers(headers)
                .params(form)
                .cookies(cookies)

                .when().post(endPointURL)

                .then().spec(getResponseSpec()).extract().response();

        return  response;
    }

    public static Response getHTML(String endPointURL, Cookies cookies) {
        Response response = given(getRequestSpec())
                .cookies(cookies)

                .when().post(endPointURL)

                .then().spec(getResponseSpec()).extract().response();
        return  response;
    }

}
