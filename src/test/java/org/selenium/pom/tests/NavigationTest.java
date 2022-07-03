package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class NavigationTest extends BaseTest {

        @Test
        public void NavigateFromHomeToStoreUsingMenu () {
            HomePage homePage = new HomePage(getDriver()).load();
            //Not using components
            //StorePage storePage = homePage.navigateToStorePageUsingMenu();

            //Using components
            StorePage storePage = homePage.getMyHeader().navigateToStorePageUsingMenu();
            Assert.assertEquals(storePage.getTitleUniquePage(), "Store");
        }

}
