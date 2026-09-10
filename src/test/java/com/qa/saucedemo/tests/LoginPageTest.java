package com.qa.saucedemo.tests;
import com.qa.saucedemo.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.qa.saucedemo.constants.AppConstants.*;

@Epic("Epic_01: Design Pages For Sauce Lab Demo Application")
@Feature("Feature_01: Sauce Lab Demo - Login Feature")
@Story("US_01: Implement login page for Sauce Lab Demo application")

public class LoginPageTest extends BaseTest {

    @Description("TC_01: Verify Login Page title")
    @Severity(SeverityLevel.MINOR)
    @Owner("Manish")
    @Test(description = "Validating Login Page Title")
    public void verifyLoginPageTitle() {
        String title = loginPage.getLoginPageTitle();
        Assert.assertEquals(title, LOGIN_PAGE_TITLE);
    }

    @Description("TC_02: Login with valid credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Manish")
    @Test(priority = Short.MAX_VALUE, description = "Login with valid credentials")
    public void loginWithValidCredentials() {
        inventoryPage = loginPage.doLogin(properties.getProperty("APP_USERNAME"), properties.getProperty("APP_PASSWORD"));
        Assert.assertEquals(inventoryPage.getInventoryPageUrl(), INVENTORY_PAGE_URL);

    }

    @DataProvider
    public Object[][] getInvalidUserName() {
        return new Object[][]{
                {"standard_user_1", "secret_sauce"}
        };
    }

    @Description("TC_03: Login with invalid username")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Manish")
    @Test(dataProvider = "getInvalidUserName", description = "Login with invalid username")
    public void loginWithInvalidUserName(String appUserName, String app_Password) {
        String actualErrorMessage = loginPage.doLoginWithInvalidCredential(appUserName,app_Password);
        Assert.assertEquals(actualErrorMessage, INVALID_CREDENTIALS_ERROR_MESSAGE);
    }

    @DataProvider
    public Object[][] getInvalidPassword() {
        return new Object[][]{
                {"standard_user", "secret_sauce_1"}
        };
    }
    @Description("TC_04: Login with invalid password")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Manish")
    @Test(dataProvider = "getInvalidPassword", description = "Login with invalid password")
    public void loginWithInvalidPassword(String appUserName, String app_Password) {
        String actualErrorMessage = loginPage.doLoginWithInvalidCredential(appUserName,app_Password);
        Assert.assertEquals(actualErrorMessage, INVALID_CREDENTIALS_ERROR_MESSAGE);
    }

    @DataProvider
    public Object[][] getLockedUserCredentials() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce"}
        };
    }

    @Description("TC_05: Login with locked-out user")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Manish")
    @Test(dataProvider = "getLockedUserCredentials", description = "Login with locked-out user")
    public void loginWithLockedUser(String appUserName, String app_Password) {
        String actualErrorMessage = loginPage.doLoginWithInvalidCredential(appUserName,app_Password);
        Assert.assertEquals(actualErrorMessage, LOCKED_USER_ERROR_MESSAGE);
    }




}
