package org.selenium.pom.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import org.selenium.pom.objects.BillingInfo;

import java.io.IOException;
import java.io.InputStream;

public class JacksonUtils {

 //   Use 1 - Quick find file path using input stream
//    public static BillingInfo coventStringToObjectJson(InputStream is, BillingInfo billingInfo) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.readValue(is, billingInfo.getClass());
//    }

    //  Use 2 - Take a time to def link file path in project
//    public static BillingInfo coventStringToObjectJson(String filePath, BillingInfo billingInfo) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.readValue(filePath, billingInfo.getClass());
//    }

    //Make function covent json to object more generic
    public static <T>T coventStringToObjectJson(String fileName, Class<T> T) throws IOException {
        InputStream inputStream = JacksonUtils.class.getClassLoader().getResourceAsStream(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(inputStream, T);
    }
}
