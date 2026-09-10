package com.qa.saucedemo.pages;

import com.qa.saucedemo.utils.ElementUtil;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.qa.saucedemo.constants.AppConstants.*;

public class LoginPage {

    private WebDriver driver;
    private ElementUtil elementUtil;
    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    //1. Maintain private By Locator
    private final By userName = By.id("user-name");
    private final By password = By.id("password");
    private final By loginBtn = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    //2. Supply the driver: Public constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    //3. Public page method/actions
    @Step("Getting Login Page Title")
    public String getLoginPageTitle() {
        String title = elementUtil.waitForTitleIs(LOGIN_PAGE_TITLE, DEFAULT_TIMEOUT);
        logger.info("Login page title {} ", title);
        return title;
    }

    @Step("Login With Valid Username: {0}")
    public InventoryPage doLogin(String uname, String pwd) {
        logLoginAttempt(uname);
        elementUtil.waitForElementVisible(userName, MEDIUM_DEFAULT_TIMEOUT);
        elementUtil.doSendKeys(userName, uname);
        elementUtil.doSendKeys(password, pwd);
        elementUtil.doClick(loginBtn);
        return new InventoryPage(driver);
    }

    @Step("Login With Invalid Username: {0}")
    public String doLoginWithInvalidCredential(String uname, String pwd) {
        logLoginAttempt(uname);
        elementUtil.waitForElementVisible(userName, MEDIUM_DEFAULT_TIMEOUT);
        elementUtil.doSendKeys(userName, uname);
        elementUtil.doSendKeys(password, pwd);
        elementUtil.doClick(loginBtn);
        return elementUtil.waitForElementVisible(errorMessage, DEFAULT_TIMEOUT).getText();
    }

    //Common logger info method
    private void logLoginAttempt(String uname) {
        logger.info("user credentials - userName:{}, password:{}", uname, "******");
    }

}
