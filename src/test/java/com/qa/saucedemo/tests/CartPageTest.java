package com.qa.saucedemo.tests;

import com.qa.saucedemo.base.BaseTest;
import com.qa.saucedemo.models.ProductDetails;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;


public class CartPageTest extends BaseTest {

    @BeforeClass
    public void CartPageSetup() {
        List<String> products = List.of(
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt"
        );
        inventoryPage = loginPage.doLogin(properties.getProperty("APP_USERNAME"), properties.getProperty("APP_PASSWORD"));
        inventoryPage.addProductToCart(products);
        cartPage = inventoryPage.navigateToCartPage();
    }

    @Description("TC_01: Remove product from cart")
    @Severity(SeverityLevel.MINOR)
    @Owner("Manish")
    @Test(description = "Remove product from cart")
    public void removeProductFromCart() {
        List<String> removeProdList = List.of(
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt"
        );
        cartPage.removeProductFromCart(removeProdList);
        List<String> actualProducts = cartPage.getCartProductNames();
        for(String product : removeProdList)
        {
            Assert.assertFalse(actualProducts.contains(product), product + "was not remove from cart.");
        }
    }

    @Description("TC_02: Verify Cart Product Details")
    @Severity(SeverityLevel.MINOR)
    @Owner("Manish")
    @Test(description = "Verify Cart Product Details")
    public void verifyCartProductDetails() {
        String productName = "Sauce Labs Backpack";

        ProductDetails actualDetails = cartPage.getCartProductDetails(productName);

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

}
