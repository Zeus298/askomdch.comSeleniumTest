package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.selenium.pom.api.ApiRequest;
import org.selenium.pom.objects.BillingInfo;
import org.selenium.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class EditBillingInfoAPI {
    private Cookies cookies;

    public EditBillingInfoAPI() {}
    public EditBillingInfoAPI(Cookies cookies) {
        this.cookies = cookies;
    }

    public Cookies getCookies() {
        return cookies;
    }

    public Response getBillingInfo() {

        Response response = given().baseUri(ConfigLoader.getInstance().getBaseUrl()).cookies(cookies).log().all()
                .when().get("/account/edit-address/billing/")
                .then().log().all().extract().response();
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch billing ìno, HTTPS status code is " + response.getStatusCode());
        }
        return response;
    }

    public String fetchRegisterNonceValueUsingJsoupAndCSSSelector() {
        Response response = getBillingInfo();

        Document doc = Jsoup.parse(response.body().prettyPrint());
        Element element = doc.selectFirst("#woocommerce-edit-address-nonce");
        return  element.attr("value");
    }

    public Response editUsingAPI(BillingInfo billingInfo) {

        Header header = new Header("content-type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);

        if (cookies == null ) {
            cookies = new Cookies();
        }

        HashMap<String, Object> formBillingInfoAPI = new HashMap<>();
        formBillingInfoAPI.put("billing_first_name", billingInfo.getFirstName());
        formBillingInfoAPI.put("billing_last_name", billingInfo.getLastName());
        formBillingInfoAPI.put("billing_country", billingInfo.getCountryCode());
        formBillingInfoAPI.put("billing_state", billingInfo.getState());
        formBillingInfoAPI.put("billing_postcode", billingInfo.getZipCode());
        formBillingInfoAPI.put("billing_city", billingInfo.getCity());
        formBillingInfoAPI.put("billing_address_1", billingInfo.getAddress1());
        formBillingInfoAPI.put("billing_email", billingInfo.getGmail());
        formBillingInfoAPI.put("woocommerce-edit-address-nonce", fetchRegisterNonceValueUsingJsoupAndCSSSelector());
        formBillingInfoAPI.put("save_address", "Save address");
        formBillingInfoAPI.put("action", "edit_address");
        formBillingInfoAPI.put("_wp_http_referer", "/account/edit-address/billing/");

        Response response = ApiRequest.postRequest("/account/edit-address/billing", headers, formBillingInfoAPI, cookies);

        if (response.getStatusCode() != 302) {
            throw new RuntimeException("Failed to fetch billing, HTTPS status code is " + response.getStatusCode());
        }

        return response;

    }




}
