package org.selenium.pom.factory.abstractFactory;

import org.selenium.pom.constants.MultiBrowsers;

public class DriverManagerFactoryAbstract {

    public static DriverManagerAbstract getManager(MultiBrowsers browsers) {
        switch(browsers) {
            case CHROME -> {
                return new ChromeDriverManagerAbstract();
            }
            case FIREFOX -> {
                return new FirefoxDriverManagetAbstract();
            }
            default -> {
                throw new IllegalStateException("Driver " + browsers + " is invalid");
            }
        }
    }
}
