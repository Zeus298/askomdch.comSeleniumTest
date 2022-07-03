package org.selenium.pom.api.actions;

//import com.beust.ah.A;
import org.selenium.pom.objects.UserLogin;
import org.selenium.pom.utils.FakerUtils;

public class DummyClass {
    public static void main(String[] args) {
        //System.out.println(new SignUpAPI().fetchRegisterNonceValueUsingJsoupAndCSSSelector());
        UserLogin user = new UserLogin();
        FakerUtils faker = new FakerUtils();
        SignUpAPI signUpApi = new SignUpAPI();

        user.setUserName("chien1234" + faker.generateRandomNumber());
        user.setPassWord("chien1234");
        user.setGmail( user.getUserName() + "@gmail.com");
        signUpApi.register(user);

        AddToCartAPI addToCartAPI =  new AddToCartAPI(signUpApi.getCookies());
        addToCartAPI.addProductToCartUsingAPI(1215, 1);
        System.out.println(addToCartAPI.getCookies());
    }
}
