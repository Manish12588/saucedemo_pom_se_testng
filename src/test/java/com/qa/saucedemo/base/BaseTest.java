package com.qa.saucedemo.base;

import com.qa.saucedemo.factory.DriverFactory;
import com.qa.saucedemo.pages.*;
import com.qa.saucedemo.tests.CartPageTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.Properties;

//@Listeners(ChainTestListener.class) //If you dont want to use Listener annotation here then you can add this in runner xml file.
public class BaseTest {
    WebDriver driver;
    DriverFactory driverFactory;
    protected Properties properties;

    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;
    protected CartPage cartPage;
    protected CheckoutInfoPage checkoutInfoPage;
    protected CheckoutOverviewPage checkoutOverviewPage;
    protected CheckoutCompletePage checkoutCompletePage;


    //Pre-condition
    @Parameters({"browser"})
    @BeforeTest
    public void setup(@Optional("chrome") String browserName) {
        driverFactory = new DriverFactory();
        properties = driverFactory.initProp();

        //Browser name is passed from .xml file
        if (browserName != null) {
            properties.setProperty("browser", browserName);
        }
        driver = driverFactory.initDriver(properties);
        loginPage = new LoginPage(driver);
    }

    @AfterMethod  //It will run after each @Test method (In case of failure test it will attached screenshot)
    public void attachScreenshot(ITestResult result) {
        if (!result.isSuccess()) { //Only run for failure test case
            //ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
        }
    }

    //Post-condition
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
