package com.qpros;

import com.google.common.io.Files;
import com.qpros.pages.HomePage;
import com.qpros.utils.LoadProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.qpros.utils.Utils.getCurrentDateTime;

public class BaseTests {
    public WebDriver driver;
    protected HomePage homePage;
    protected String userName = LoadProperties.userCredentialsProperties.getProperty("userName");
    protected String userPassword = LoadProperties.userCredentialsProperties.getProperty("userPassword");
    public static Logger log = LogManager.getLogger();

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setup(String browser) {
        log.info("Browser Setup Based On Browser Value From testng.xml File");
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(getChromeOptions());
        }else
        {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        goHome();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        log.info("Browser initialized successfully");
    }

    @BeforeMethod
    public void goHome(){
        log.info("Load env url from env.properties file");
        String baseUrl = LoadProperties.envProperties.getProperty("url");
        driver.get(baseUrl);
    }
    @AfterClass
    public void tearDown(){
        log.info("Closing browser");
        driver.quit();
        log.info("Browser closed successfully");
    }

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result){
        String dateTime = getCurrentDateTime();
        if(ITestResult.FAILURE == result.getStatus()){
            var camera = (TakesScreenshot) driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try {
                Files.move(screenshot, new File(".\\screenshots\\" + result.getName() + dateTime + ".png"));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    private ChromeOptions getChromeOptions(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("headless=new");
        return chromeOptions;
    }
}
