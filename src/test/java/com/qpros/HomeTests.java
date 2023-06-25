package com.qpros;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import static com.qpros.utils.Utils.getCurrentDateTime;
import static org.testng.Assert.*;

public class HomeTests extends BaseTests{

    @Test(description = "Test User Registration With Unique User Name")
    @Description("Test User Registration With Unique User Name by Adding Current Date Time To The User Name Which Loaded From Property File In BaseTests")
    public void testUserSignupWithValidData(){
        log.info("Test User Registration With Unique User Name And Password Which in userCredentials.properties File");
        log.info("Get userName from userCredentials.properties file and append on it current date time to make it unique");
        String currentDateTime = getCurrentDateTime();
        String userEmail = userName + currentDateTime;
        homePage.userRegister(userEmail, userPassword);
        var alertText = homePage.getAlertText();
        homePage.acceptAlert();
        assertEquals(alertText, "Sign up successful.", "Alert text is incorrect");
    }

    @Test(description = "Test User Login After Registration")
    @Description("Test User Login After Registration And Call User Registration Here Before Login To Make Sure That The User Is Newly Added And Separating Between User Registration Test And User Login Test")
    public void testLoginWithValidDataAfterUserRegistration(){
        log.info("Test User Login After Registration");
        log.info("Get userName from userCredentials.properties file and append on it current date time to make it unique");
        String currentDateTime = getCurrentDateTime();
        String userEmail = userName + currentDateTime;
        homePage.userRegister(userEmail, userPassword);
        homePage.acceptAlert();
        homePage.userLogin(userEmail, userPassword);
        assertEquals(homePage.getLogoutLinkText(), "Log out", "Logout button text is incorrect");
    }

    @Test(description = "Verify Listed Categories Has Items")
    @Description("Verify Listed Categories Has Items")
    public void verifyCategoriesListHasItems(){
        log.info("Verify Listed Categories Has Items");
        var itemsCount = homePage.getCategoriesItemsList().size();
        assertTrue(itemsCount > 0, "Categories list items count is less than or equal zero");
    }
}
