package com.qa.saucedemo.tests;
import com.qa.saucedemo.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.qa.saucedemo.constants.AppConstants.*;

@Epic("Epic_01: Design Pages For Sauce Lab Demo Application")
@Feature("Feature_01: Sauce Lab Demo - Login Feature")
@Story("US_01: Implement login page for Sauce Lab Demo application")

public class LoginPageTest extends BaseTest {

    @Description("TC_01: Validating Sauce Lab Login Page Title.")
    @Severity(SeverityLevel.MINOR)
    @Owner("Manish")
    @Test(description = "Validating Login Page Title")
    public void loginPageTitleTest() {
        String title = loginPage.getLoginPageTitle();
        Assert.assertEquals(title, LOGIN_PAGE_TITLE);
    }

    @Description("TC_02: User is Able to Login with Valid Credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Manish")
    @Test(priority = Short.MAX_VALUE, description = "Validating Successful Login")
    public void loginTest() {
        inventoryPage = loginPage.doLogin(properties.getProperty("APP_USERNAME"), properties.getProperty("APP_PASSWORD"));
        Assert.assertEquals(inventoryPage.getInventoryPageUrl(), INVENTORY_PAGE_URL);

    }
}
