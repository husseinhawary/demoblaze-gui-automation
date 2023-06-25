package com.qpros;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.qpros.utils.Utils.getRandomNumber;

public class ItemTests extends BaseTests{

    @Test(description = "Test Add Random Item To Cart")
    @Description("Test Add Random Item To Cart")
    public void testAddRandomItemToCart(){
        log.info("Test Add Random Item To Cart");
        log.info("Login By User Credentials Which Saved in userCredentials.properties");
        homePage.userLogin(userName, userPassword);
        var listCount = homePage.getCategoriesItemsList().size();
        int randomItemIndex = getRandomNumber(listCount);
        var itemDetailsPage = homePage.openItemDetailsPage(randomItemIndex);
        itemDetailsPage.clickOnAddItemToCart();
        String actualResult = itemDetailsPage.getAlertText();
        itemDetailsPage.acceptAlert();
        Assert.assertTrue(actualResult.contains("Product added"), "Add product text is incorrect");
    }
}
