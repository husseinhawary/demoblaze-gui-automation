package com.qpros.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

public class CartPage extends BasePage{
    private By productsTable = By.id("tbodyid");
    private By productTableItems = By.xpath("//tbody[@id='tbodyid']//tr");
    private By deleteBtn = By.linkText("Delete");
    private By totalValue = By.id("totalp");
    private By placeOrderBtn = By.xpath("//button[text()='Place Order']");
    private By placeOrderNameTxt = By.id("name");
    private By placeOrderCountryTxt = By.id("country");
    private By placeOrderCityTxt = By.id("city");
    private By placeCreditCardTxt = By.id("card");
    private By placeMonthTxt = By.id("month");
    private By placeYearTxt = By.id("year");
    private By purchaseBrn = By.xpath("//button[text()='Purchase']");
    public CartPage(WebDriver driver) {
        super(driver);
    }
    public void deleteCartItem(){
        log.info("Click on delete button");
        waitUntilElementBeVisible(productsTable);
        clickElement(deleteBtn);
        // This line to override the generic implicit wait value to not take a lot of time in ExpectedConditions.invisibilityOfElementLocated step
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(productTableItems));
    }
    public String getTotalValue(){
        log.info("Get total value in cart page");
        return getElementText(totalValue);
    }
    public void openPlaceOrderForm(){
        log.info("Open cart page");
        waitUntilElementBeVisible(productTableItems);
        clickElement(placeOrderBtn);
    }
    public void placeOrderWithValidData(String name, String country, String city, String CC, String month, String year) throws InterruptedException {
        openPlaceOrderForm();
        log.info("Place order");
        waitUntilElementBeVisible(placeOrderNameTxt);
        setTextValue(placeOrderNameTxt, name);
        setTextValue(placeOrderCountryTxt, country);
        setTextValue(placeOrderCityTxt, city);
        setTextValue(placeCreditCardTxt, CC);
        setTextValue(placeMonthTxt, month);
        setTextValue(placeYearTxt, year);
        clickOnElementByJavascript(driver.findElement(purchaseBrn));
    }
    public String getHomePageURL() throws InterruptedException {
        log.info("Go to home page after placing order successfully");
        JavascriptExecutor jse =(JavascriptExecutor) driver;
        Thread.sleep(2000);
        var script = "document.querySelector('body > div.sweet-alert.showSweetAlert.visible > div.sa-button-container > div > button').click()";
        jse.executeScript(script);
        return driver.getCurrentUrl();
    }
}
