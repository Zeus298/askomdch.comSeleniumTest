package org.selenium.pom.objects;

public class BillingInfo {

    public BillingInfo() {}

    public BillingInfo(String firstName, String lastName, String city, String address1, String zipCode, String gmail, String countryCode, String state) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.address1 = address1;
        this.zipCode = zipCode;
        this.gmail = gmail;
        this.countryCode = countryCode;
        this.state = state;
    }

    public BillingInfo(String firstName, String lastName, String city, String address1, String gmail, String countryCode, String state) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.address1 = address1;
        this.gmail = gmail;
        this.countryCode = countryCode;
        this.state = state;
    }

    private String firstName;
    private String lastName;
    private String city;
    private String address1;
    private String zipCode;
    private String gmail;

    private String countryCode;
    private String state;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



    public String getFirstName() {
        return firstName;
    }

    public BillingInfo setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BillingInfo setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCity() {
        return city;
    }

    public BillingInfo setCity(String city) {
        this.city = city;
        return this;
    }

    public String getAddress1() {
        return address1;
    }

    public BillingInfo setAddress1(String address1) {
        this.address1 = address1;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public BillingInfo setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getGmail() {
        return gmail;
    }

    public BillingInfo setGmail(String gmail) {
        this.gmail = gmail;
        return this;
    }


}
