package com.qa.saucedemo.tests;

import com.qa.saucedemo.base.BaseTest;
import com.qa.saucedemo.models.ProductDetails;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

@Epic("Epic_01: Design Pages For Sauce Lab Demo Application")
@Feature("Feature_05: Checkout Page - Test")
@Story("US_05: Add product to cart and successfully checkout.")
public class CheckoutPageTest extends BaseTest {

    private final List<String> productsToAdd = List.of(
            "Sauce Labs Backpack"
    );

    @BeforeClass
    public void CheckoutPageSetup() {
        inventoryPage = loginPage.doLogin(properties.getProperty("APP_USERNAME"), properties.getProperty("APP_PASSWORD"));
        inventoryPage.addProductToCart(productsToAdd);
        cartPage = inventoryPage.navigateToCartPage();
        checkoutInfoPage = cartPage.navigateToCheckoutPage();
    }

    @Description("TC_01: Checkout with valid customer information")
    @Severity(SeverityLevel.MINOR)
    @Owner("Manish")
    @Test(description = "Checkout with valid customer information")
    public void checkoutWithValidCustomerInformation() {
        checkoutOverviewPage = checkoutInfoPage.doCheckoutWithValidInfo("Manish","Kumar","60326");
        Assert.assertEquals(checkoutOverviewPage.getPageTitle(),"Checkout: Overview");
    }

    @Description("TC_02: Verify order summary")
    @Severity(SeverityLevel.MINOR)
    @Owner("Manish")
    @Test(description = "Verify order summary",dependsOnMethods = "checkoutWithValidCustomerInformation")
    public void verifyOrderSummary() {
        ProductDetails actualDetails = cartPage.getCartProductDetails(productsToAdd.get(0));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualDetails.getProductName(),
                "Sauce Labs Backpack",
                "Incorrect Product Name.");
        softAssert.assertEquals(actualDetails.getDescription(),
                "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
                "Incorrect product description.");
        softAssert.assertEquals( actualDetails.getPrice(),
                "$29.99",
                "Incorrect product price.");
        softAssert.assertEquals( actualDetails.getQuantity(),
                "1",
                "Incorrect product quantity.");
        softAssert.assertAll();
    }

    @Description("TC_03: Complete successful purchase")
    @Severity(SeverityLevel.MINOR)
    @Owner("Manish")
    @Test(description = "Complete successful purchase", dependsOnMethods = "verifyOrderSummary")
    public void completeSuccessfulPurchase() {
        checkoutCompletePage = checkoutOverviewPage.doFinishPurchase();
        SoftAssert softAssert = new SoftAssert();
        Assert.assertEquals(checkoutCompletePage.getPageTitle(),"Checkout: Complete!");
        Assert.assertEquals(checkoutCompletePage.getOrderSuccessMessage(),"Thank you for your order!");
        Assert.assertTrue(checkoutCompletePage.getOrderDeliveryStatus().contains("Your order has been dispatched"),"Order not completed.");
        softAssert.assertAll();
    }





}
