package com.qa.saucedemo.tests;

import com.qa.saucedemo.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class CheckoutInfoPageTest extends BaseTest {

    private final List<String> productsToAdd = List.of(
            "Sauce Labs Backpack"
    );

    @BeforeClass
    public void CheckoutInfoPageSetup() {
        inventoryPage = loginPage.doLogin(properties.getProperty("APP_USERNAME"), properties.getProperty("APP_PASSWORD"));
        inventoryPage.addProductToCart(productsToAdd);
        cartPage = inventoryPage.navigateToCartPage();
        checkoutInfoPage = cartPage.navigateToCheckoutPage();
    }

    @Description("TC_01: Checkout with missing First Name")
    @Severity(SeverityLevel.MINOR)
    @Owner("Manish")
    @Test(description = "Checkout with missing First Name")
    public void checkoutWithMissingFirstName() {
        checkoutInfoPage = checkoutInfoPage.submitCheckoutInformation("","Kumar","60326");
        Assert.assertEquals(checkoutInfoPage.getUserInfoError(),"Error: First Name is required");
        cartPage = checkoutInfoPage.navigateToCartPage();
        Assert.assertEquals(cartPage.getPageTitle(),"Your Cart");
        cartPage.navigateToCheckoutPage();

    }

    @Description("TC_02: Checkout with missing Last Name")
    @Severity(SeverityLevel.MINOR)
    @Owner("Manish")
    @Test(description = "Checkout with missing Last Name")
    public void checkoutWithMissingLastName() {
        checkoutInfoPage = checkoutInfoPage.submitCheckoutInformation("Manish","","60326");
        Assert.assertEquals(checkoutInfoPage.getUserInfoError(),"Error: Last Name is required");
        cartPage = checkoutInfoPage.navigateToCartPage();
        Assert.assertEquals(cartPage.getPageTitle(),"Your Cart");
        cartPage.navigateToCheckoutPage();

    }

    @Description("TC_03: Checkout with missing Postal Code")
    @Severity(SeverityLevel.MINOR)
    @Owner("Manish")
    @Test(description = "Checkout with missing Postal Code")
    public void checkoutWithMissingPostalCode() {
        checkoutInfoPage = checkoutInfoPage.submitCheckoutInformation("Manish","Kumar","");
        Assert.assertEquals(checkoutInfoPage.getUserInfoError(),"Error: Postal Code is required");
        cartPage = checkoutInfoPage.navigateToCartPage();
        Assert.assertEquals(cartPage.getPageTitle(),"Your Cart");

    }
}
