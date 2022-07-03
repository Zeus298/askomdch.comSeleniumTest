package org.selenium.pom.factory;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.selenium.pom.constants.MultiBrowsers;

public class DriverManagerFactory {
    public static DriverManagerInterface getManager(MultiBrowsers browsers) {
            switch (browsers) {
                case CHROME -> {
                    return new ChromeDriverManager();
                }
                case FIREFOX -> {
                    return new FirefoxDriverManager();
                }

                default -> throw new IllegalStateException("Driver " + browsers + "is invalid");

            }
    };

}
