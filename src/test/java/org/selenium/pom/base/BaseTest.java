package org.selenium.pom.base;

import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.constants.MultiBrowsers;
import org.selenium.pom.factory.DriverManager;
import org.selenium.pom.factory.DriverManagerFactory;
import org.selenium.pom.factory.abstractFactory.DriverManagerAbstract;
import org.selenium.pom.factory.abstractFactory.DriverManagerFactoryAbstract;
import org.selenium.pom.utils.CookiesUtils;


import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;



import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseTest {
    //protected WebDriver driver;
    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    protected WebDriver getDriver() {
        return this.driver.get();
    }

    protected ThreadLocal<DriverManagerAbstract> driverManagerAbstract = new ThreadLocal<>();

    protected void setDriverManagerAbstract(DriverManagerAbstract driverManagerAbstract) {
        this.driverManagerAbstract.set(driverManagerAbstract);
    }

    protected  DriverManagerAbstract getDriverManagerAbstract() {
        return driverManagerAbstract.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public synchronized void  startDriver(@Optional("CHROME") String browser) {
        //driver = new DriverManager().initDriver(browser);


        //setDriver(new DriverManager().initDriver(browser));


        //Set driver using factory pattern by driver manager static class
        //setDriver(DriverManagerFactory.getManager(MultiBrowsers.valueOf(browser)).createDriverMethod());

        //Set driver using factory pattern by driver manager abstract class
        setDriverManagerAbstract(DriverManagerFactoryAbstract.getManager(MultiBrowsers.valueOf(browser)));
        setDriver(getDriverManagerAbstract().getDriverUsingAbstract());

        System.out.println("Current thread: "+ Thread.currentThread().getId() + ", Driver = " + getDriver());
    }

    @Parameters("browsers")
    @AfterMethod
    public  synchronized void quitDriver(@Optional String browsers, ITestResult result) throws InterruptedException, IOException {
        //driver.quit();
        Thread.sleep(100);
        System.out.println("Current thread: "+ Thread.currentThread().getId() + ", Driver = " + getDriver());
        //getDriver().quit();
        if (result.getStatus() == ITestResult.FAILURE) {
            File destFile = new File("srcScreenshot" + File.separator + browsers + File.separator + result.getTestClass().getRealClass().getSimpleName()
                    + "_" + result.getMethod().getMethodName() + "_" + result.getEndMillis() +".png");
            takeScreenShot(destFile);
        }
        getDriverManagerAbstract().stopDriver();
    }

    public void injectCookiesToBrowsers(Cookies cookies) {
        List<Cookie> seleniumCookies = new CookiesUtils().coventCookiesOfRestAssuredToCookiesOfSelenium(cookies);
        for (Cookie cookie : seleniumCookies) {
            getDriver().manage().addCookie(cookie);
        }
    }

    private void takeScreenShot(File destFile) throws IOException {
        TakesScreenshot screenshot =  (TakesScreenshot) getDriver();
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, destFile);
    }

}
