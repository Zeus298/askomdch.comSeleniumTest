package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager implements DriverManagerInterface {

    @Override
    public WebDriver createDriverMethod() {
        WebDriverManager.firefoxdriver().cachePath("Drivers").setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }


}
