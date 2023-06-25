package com.qpros;

import com.qpros.utils.JsonDataReader;
import io.qameta.allure.Description;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import static com.qpros.utils.Utils.getRandomNumber;

public class CartTests extends BaseTests{
    private JsonDataReader jsonDataReaderObj;

    @Test(description = "Test Remove Item From Cart After Adding It Randomly")
    @Description("Test Remove Item From Cart After Adding It Randomly")
    public void testRemoveItemFromCart(){
        log.info("Test Remove Item From Cart After Adding It Randomly");
        log.info("Login By User Credentials Which Saved in userCredentials.properties");
        homePage.userLogin(userName, userPassword);
        var listCount = homePage.getCategoriesItemsList().size();
        int randomItemIndex = getRandomNumber(listCount);
        var itemDetailsPage = homePage.openItemDetailsPage(randomItemIndex);
        itemDetailsPage.clickOnAddItemToCart();
        itemDetailsPage.acceptAlert();
        var cartPage = homePage.openCartPage();
        cartPage.deleteCartItem();
        String EmptyTotalPrice = cartPage.getTotalValue();
        Assert.assertEquals(EmptyTotalPrice, "", "Incorrect item value");
    }

    @Test(description = "Test Place Order")
    @Description("Test Place Order")
    public void testPlaceOrderWithValidData() throws InterruptedException, IOException, ParseException {
        log.info("Test Place Order");
        jsonDataReaderObj = new JsonDataReader();
        jsonDataReaderObj.jsonReader();
        log.info("Login By User Credentials Which Saved in userCredentials.properties");
        homePage.userLogin(userName, userPassword);
        var listCount = homePage.getCategoriesItemsList().size();
        int randomItemIndex = getRandomNumber(listCount);
        var itemDetailsPage = homePage.openItemDetailsPage(randomItemIndex);
        itemDetailsPage.clickOnAddItemToCart();
        itemDetailsPage.acceptAlert();
        var cartPage = homePage.openCartPage();
        log.info("Place Order With User Info Which Saved in PlaceOrderUserData.json file");
        cartPage.placeOrderWithValidData(jsonDataReaderObj.name, jsonDataReaderObj.country, jsonDataReaderObj.city, jsonDataReaderObj.creditCard, jsonDataReaderObj.month, jsonDataReaderObj.year);
        String thankYouMessage = cartPage.getHomePageURL();
        Assert.assertEquals(thankYouMessage, "https://www.demoblaze.com/index.html", "Incorrect page url");
    }
}
