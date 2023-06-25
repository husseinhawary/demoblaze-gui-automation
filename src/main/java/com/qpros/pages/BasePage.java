package com.qpros.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
    protected WebDriver driver;
    public static Logger log = LogManager.getLogger();
    protected WebDriverWait wait;
    private JavascriptExecutor jse;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
        jse = (JavascriptExecutor) driver;
    }
    public void clearTextValue(By locator){
        driver.findElement(locator).clear();
    }
    public void setTextValue(By locator, String value){
        driver.findElement(locator).sendKeys(value);
    }
    public void clickElement(By locator){
        driver.findElement(locator).click();
    }
    public String getElementText(By locator){
        return driver.findElement(locator).getText();
    }
    public void waitUntilElementBeVisible(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void scrollIntoElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public void clickOnElementByJavascript(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        jse.executeScript("arguments[0].click();", element);
    }
}
