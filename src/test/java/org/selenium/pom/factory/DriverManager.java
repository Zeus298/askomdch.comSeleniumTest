package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.selenium.pom.constants.MultiBrowsers;

public class DriverManager {
    public WebDriver initDriver(String browser) {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver();

        WebDriver driver;

        //Case not to use parameter from testng.xml
        //String browser = System.getProperty("browser", "CHROME");

        switch(MultiBrowsers.valueOf(browser)) {
            case CHROME -> {
                WebDriverManager.chromedriver().cachePath("Drivers").setup();
                driver = new ChromeDriver();
                break;
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().cachePath("Drivers").setup();
                driver = new FirefoxDriver();
                break;
            }
            default -> {
                throw new IllegalArgumentException("please check spell again. Browsers name must be CHROME or FIREFOX !");
            }

        }
        driver.manage().window().maximize();
        return  driver;
    }
}
