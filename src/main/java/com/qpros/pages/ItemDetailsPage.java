package com.qpros.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ItemDetailsPage extends BasePage{
    private By addToCartLink = By.linkText("Add to cart");
    public ItemDetailsPage(WebDriver driver) {
        super(driver);
    }
    public ItemDetailsPage clickOnAddItemToCart(){
        log.info("Click on add item to cart");
        waitUntilElementBeVisible(addToCartLink);
        clickElement(addToCartLink);
        return this;
    }
    public String getAlertText(){
        log.info("Get alert text");
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }
    public void acceptAlert(){
        log.info("Accept alert");
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
}
