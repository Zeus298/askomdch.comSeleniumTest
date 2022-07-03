package org.selenium.pom.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingInfo;

import static java.lang.Thread.sleep;

public class CheckOutPage extends BasePage {
    private final By firstNameInput = By.cssSelector("#billing_first_name");
    private final By lastNameInput = By.cssSelector("#billing_last_name");
    private final By addressInput = By.cssSelector("#billing_address_1");
    private final By cityInput = By.cssSelector("#billing_city");
    private final By postCodeInput = By.cssSelector("#billing_postcode");
    private final By gmailInput = By.cssSelector("#billing_email");
    private final By orderSuccessText = By.cssSelector(".woocommerce-thankyou-order-received");
    private final By orderButton = By.cssSelector("#place_order");

    private final By userNameInput = By.cssSelector("#username");

    private final By passWordInput = By.cssSelector("#password");

    private final By loginButton = By.cssSelector(".woocommerce-form-login__submit");

    private final By loginForm = By.cssSelector(".showlogin");

    private final By countrySelect = By.cssSelector("#billing_country");

    private final By stateSelect = By.cssSelector("#billing_state");

    private final By directBankTransRadio = By.cssSelector("#payment_method_bacs");

    private final By cashOnDeliveryRadio = By.cssSelector("#payment_method_cod");

    private final By spinIcon = By.cssSelector(".blockOverlay");

    private final By countrySelectFireFox = By.cssSelector("#select2-billing_country-container");

    private final By stateSelectFireFox = By.cssSelector("#select2-billing_state-container");

    private final By productNameText = By.cssSelector(".cart_item .product-name");
    private JavascriptExecutor js = (JavascriptExecutor) driver;

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    public CheckOutPage load() {
        loadPage("/checkout/");
        return this;
    }
    public CheckOutPage enterFirstName(String name) {
        waitLong.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).clear();
        driver.findElement(firstNameInput).sendKeys(name);
        return this;
    }

    public CheckOutPage enterLastName(String name) {
        waitLong.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput)).clear();
        driver.findElement(lastNameInput).sendKeys(name);
        return this;
    }

    public CheckOutPage enterAddress(String address) {
        waitLong.until(ExpectedConditions.visibilityOfElementLocated(addressInput)).clear();
        driver.findElement(addressInput).sendKeys(address);
        return this;
    }

    public CheckOutPage enterCity(String city) {
        waitLong.until(ExpectedConditions.visibilityOfElementLocated(cityInput)).clear();
        driver.findElement(cityInput).sendKeys(city);
        return this;
    }

    public CheckOutPage enterPostCode(String postCode) {

        waitLong.until(ExpectedConditions.visibilityOfElementLocated(postCodeInput)).clear();
        driver.findElement(postCodeInput).sendKeys(postCode);
        return this;
    }

    public CheckOutPage enterGmail(String gmail) {
        waitLong.until(ExpectedConditions.visibilityOfElementLocated(gmailInput)).clear();
        driver.findElement(gmailInput).sendKeys(gmail);
        return this;
    }

    public CheckOutPage selectCountry(String countryCode) {
        //Chrome version
//        WebElement selectElement = waitLong.until(ExpectedConditions.elementToBeClickable(countrySelect));
//        Select selectCountry = new Select(selectElement);
//        selectCountry.selectByValue(countryCode);

         waitLong.until(ExpectedConditions.elementToBeClickable(countrySelectFireFox)).click();

        WebElement selectElement = waitLong.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[id*="+ countryCode + "]")));
        js.executeScript("arguments[0].scrollIntoView(true)", selectElement);
        selectElement.click();


        return this;
    }

    public CheckOutPage selectState(String state) {
//        Chrome Version
//        WebElement selectElement = waitLong.until(ExpectedConditions.elementToBeClickable(stateSelect));
//        Select selectState = new Select(selectElement);
//        waitLong.until(ExpectedConditions.elementToBeClickable(stateSelectOption));
//        selectState.selectByValue(state);
        try {

            waitShort.until(ExpectedConditions.elementToBeClickable(stateSelectFireFox)).click();
            waitShort.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".select2-results__options")));
            WebElement selectElement = waitShort.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[id*=" + state + "]")));
            js.executeScript("arguments[0].scrollIntoView(true);", selectElement);
            selectElement.click();
        } catch (WebDriverException ex) {
            System.out.println(ex.toString());
        }

        return this;
    }

    public CheckOutPage enterBillingInfor(BillingInfo billingInfo) {
        return enterFirstName(billingInfo.getFirstName())
                .enterLastName(billingInfo.getLastName())
                .enterAddress(billingInfo.getAddress1())
                .enterPostCode(billingInfo.getZipCode())
                .enterGmail(billingInfo.getGmail())
                .enterCity(billingInfo.getCity())
                .selectCountry(billingInfo.getCountryCode())
                .selectState(billingInfo.getState());

    }

    public CheckOutPage clickOrder() {
        waitLong.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
        return this;
    }

    public String getSuccessMess() {
        return waitLong.until(ExpectedConditions.visibilityOfElementLocated(orderSuccessText)).getText();
    }

    public CheckOutPage clickToShowLogin() {
        waitLong.until(ExpectedConditions.visibilityOfElementLocated(loginForm)).click();
        return this;
    }

    public CheckOutPage enterUserName(String userName) {
        waitLong.until(ExpectedConditions.visibilityOfElementLocated(userNameInput)).sendKeys(userName);
        return this;
    }

    public CheckOutPage enterPassword(String password) {
        waitLong.until(ExpectedConditions.visibilityOfElementLocated(passWordInput)).sendKeys(password);
        return this;
    }

    public CheckOutPage clickLogin() {
        waitLong.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).click();
        return this;
    }


    public CheckOutPage chooseDirectBankTrans() {
        waitLong.until(ExpectedConditions.invisibilityOfElementLocated(spinIcon));
        WebElement directTransElement = waitLong.until(ExpectedConditions.visibilityOfElementLocated(directBankTransRadio));
        js.executeScript("arguments[0].scrollIntoView", directTransElement);
        if (!directTransElement.isSelected()) {
            directTransElement.click();
        }
        return this;
    }

    public CheckOutPage chooseCashDelivery() {
        waitLong.until(ExpectedConditions.invisibilityOfElementLocated(spinIcon));
        WebElement cashDeliveryElement = waitLong.until(ExpectedConditions.visibilityOfElementLocated(cashOnDeliveryRadio));
        js.executeScript("arguments[0].scrollIntoView", cashDeliveryElement);
        if (!cashDeliveryElement.isSelected()) {
            cashDeliveryElement.click();
        }
        return this;
    }

    public String getProductName() {
        return waitLong.until(ExpectedConditions.visibilityOfElementLocated(productNameText)).getText();
    }
}
