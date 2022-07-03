package org.selenium.pom.base;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.selenium.pom.utils.ConfigLoader;



import java.time.Duration;
import java.util.NoSuchElementException;


public class BasePage {
    protected WebDriver driver;
    protected Wait<WebDriver> waitLong;
    protected Wait<WebDriver> waitShort;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        waitLong = new FluentWait<WebDriver> (driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        waitShort = new FluentWait<WebDriver> (driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(NoSuchElementException.class);
    }

    public void loadPage(String endPoint) {
        driver.get(ConfigLoader.getInstance().getBaseUrl() + endPoint);
    }

}
