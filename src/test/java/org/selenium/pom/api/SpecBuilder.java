package org.selenium.pom.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.asynchttpclient.RequestBuilder;
import org.selenium.pom.utils.ConfigLoader;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder().setBaseUri(ConfigLoader.getInstance().getBaseUrl())
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification getResponseSpec() {
        return  new ResponseSpecBuilder()
                .log(LogDetail.METHOD)
                .log(LogDetail.HEADERS)
                .log(LogDetail.PARAMS)
                .log(LogDetail.STATUS)
                .log(LogDetail.URI)
                .log(LogDetail.COOKIES)
                .build();
    }

}
