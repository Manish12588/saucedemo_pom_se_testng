package com.qa.saucedemo.pages;

import com.qa.saucedemo.utils.ElementUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInfoPage {
    private WebDriver driver;
    private ElementUtil elementUtil;
    private static final Logger logger = LogManager.getLogger(CheckoutInfoPage.class);

    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By checkoutInfoPageHeader = By.cssSelector("[data-test='title']");
    private final By checkoutInfoError = By.xpath("//h3[@role='alert']");
    private final By cancelButton = By.id("cancel");

    public CheckoutInfoPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public CheckoutOverviewPage doCheckoutWithValidInfo(String firstName, String lastName, String postalCode) {
        fillCustomerInformation(firstName, lastName, postalCode);
        return new CheckoutOverviewPage(driver);
    }

    public CheckoutInfoPage submitCheckoutInformation(String firstName, String lastName, String postalCode) {
        fillCustomerInformation(firstName, lastName, postalCode);
        return this;
    }

    private void fillCustomerInformation(String firstName, String lastName, String postalCode) {
        logger.info("Entering checkout information: firstName= {}, lastName= {}, postalCode= {}", firstName, lastName, postalCode);
        elementUtil.doSendKeys(firstNameInput, firstName);
        elementUtil.doSendKeys(lastNameInput, lastName);
        elementUtil.doSendKeys(postalCodeInput, postalCode);
        elementUtil.doClick(continueButton);
    }

    public String getPageTitle() {
        return elementUtil.doElementGetText(checkoutInfoPageHeader);
    }

    public String getUserInfoError() {
        return elementUtil.doElementGetText(checkoutInfoError);
    }

    public CartPage navigateToCartPage() {
       elementUtil.doClick(cancelButton);
       return new CartPage(driver);
    }


}
