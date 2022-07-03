package org.selenium.pom.factory.abstractFactory;

import org.openqa.selenium.WebDriver;

public abstract class DriverManagerAbstract {

    protected WebDriver driver;
    abstract void startDriver();

    public void stopDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }

    }

    public WebDriver getDriverUsingAbstract() {
        if (driver == null) {
            startDriver();
        }
        return driver;
    }

}
