package org.selenium.pom.factory.abstractFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManagetAbstract extends DriverManagerAbstract {
    @Override
    public void startDriver() {
        WebDriverManager.firefoxdriver().cachePath("Drivers").setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }
}
