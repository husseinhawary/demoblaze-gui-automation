package com.qpros.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class HomePage extends BasePage{

    // Locators
    private By loginLink = By.id("login2");
    private By loginFormUserNameTxt = By.id("loginusername");
    private By loginFormPasswordTxt = By.id("loginpassword");
    private By loginFormLoginBtn = By.xpath("//button[text()='Log in']");
    private By logoutLink = By.id("logout2");
    private By signupLink = By.id("signin2");
    private By signupFormUserNameTxt = By.id("sign-username");
    private By signupFormPasswordTxt = By.id("sign-password");
    private By signupFormSignupBtn = By.xpath("//button[text()='Sign up']");
    private By itemCard = By.xpath("//div[@id='tbodyid']//div[@class='card-block']");
    public By itemImage = By.xpath("//div[@id='tbodyid']//div//img");
    private By cartLink = By.id("cartur");

    public HomePage(WebDriver driver){
        super(driver);
    }
    @Step("Open login form step...")
    public HomePage openLoginForm(){
        log.info("Open login form");
        clickElement(loginLink);
        return this;
    }
    @Step("Login with username: {0}, password: {1} for method: {method} step...")
    public HomePage userLogin(String userName, String userPassword){
        openLoginForm();
        log.info("Fill user login form");
        waitUntilElementBeVisible(loginFormUserNameTxt);
        clearTextValue(loginFormUserNameTxt);
        setTextValue(loginFormUserNameTxt, userName);
        clearTextValue(loginFormPasswordTxt);
        setTextValue(loginFormPasswordTxt, userPassword);
        clickElement(loginFormLoginBtn);
        waitUntilElementBeVisible(logoutLink);
        return this;
    }
    @Step("Get logout text step...")
    public String getLogoutLinkText(){
        log.info("Get logout link text");
        waitUntilElementBeVisible(logoutLink);
        return getElementText(logoutLink);
    }
    @Step("Open signup form step...")
    public HomePage openSignupForm(){
        log.info("Open signup form");
        driver.findElement(signupLink).click();
        return this;
    }
    @Step("Get alert text step...")
    public String getAlertText(){
        log.info("Get alert text");
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }
    @Step("Accept alert step...")
    public void acceptAlert(){
        log.info("Accept alert");
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
    @Step("Signup with username: {0}, password: {1} for method: {method} step...")
    public HomePage userRegister(String userName, String userPassword){
        openSignupForm();
        log.info("Fill user signup form");
        waitUntilElementBeVisible(signupFormUserNameTxt);
        clearTextValue(signupFormUserNameTxt);
        setTextValue(signupFormUserNameTxt, userName);
        clearTextValue(signupFormPasswordTxt);
        setTextValue(signupFormPasswordTxt, userPassword);
        clickElement(signupFormSignupBtn);
        return this;
    }
    @Step("Get categories list items step...")
    public List<WebElement> getCategoriesItemsList(){
        log.info("Get categories list items step");
        List<WebElement> itemsList =  driver.findElements(itemCard);
        return itemsList;
    }
    @Step("Open item details page with index: {0} step...")
    public ItemDetailsPage openItemDetailsPage(int itemIndex){
        log.info("Open item details page");
        waitUntilElementBeVisible(itemImage);
        WebElement item =  driver.findElements(itemImage).get(itemIndex);
        scrollIntoElement(item);
        clickOnElementByJavascript(item);
        return new ItemDetailsPage(driver);
    }
    @Step("Open Cart page with index: {0} step...")
    public CartPage openCartPage(){
        log.info("Open cart page");
        waitUntilElementBeVisible(cartLink);
        clickElement(cartLink);
        return new CartPage(driver);
    }
}
