package com.qa.saucedemo.tests;

import com.qa.saucedemo.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

import static com.qa.saucedemo.constants.AppConstants.INVENTORY_PAGE_URL;

@Epic("Epic_01: Design Pages For Sauce Lab Demo Application")
@Feature("Feature_02: Sauce Lab Demo - Inventory Feature")
@Story("US_02: Implement inventory page for Sauce Lab Demo application")
public class InventoryPageTest extends BaseTest {

    @BeforeClass
    public void inventoryPageSetup() {
        inventoryPage = loginPage.doLogin(properties.getProperty("APP_USERNAME"), properties.getProperty("APP_PASSWORD"));
        Assert.assertEquals(inventoryPage.getInventoryPageUrl(), INVENTORY_PAGE_URL);
    }

    @Description("TC_01: Verify all products are displayed")
    @Severity(SeverityLevel.MINOR)
    @Owner("Manish")
    @Test(description = "Verify all products are displayed, Count Validation")
    public void verifyAllProductsDisplayed() {
        int actualProductCount = inventoryPage.getProductListCount();
        Assert.assertEquals(actualProductCount, 6);
    }


    @Description("TC_02: Sort Products Low to High")
    @Severity(SeverityLevel.MINOR)
    @Owner("Manish")
    @Test(description = "Sort Products Low to High")
    public void sortProductLowToHigh() {
        List<Double> pricesBeforeSort = inventoryPage.getProductPrices();
        Assert.assertTrue(
                inventoryPage.doPriceSort("Price (low to high)"),
                "Unable to select Price (low to high) sorting option"
        );
        List<Double> actualPriceList = inventoryPage.getProductPrices();
        List<Double> expectedPriceList = pricesBeforeSort.stream().sorted().toList();
        Assert.assertEquals(actualPriceList, expectedPriceList, "Products are not sorted by price Low to High");
    }

    @Description("TC_03: Sort Products High to Low")
    @Severity(SeverityLevel.MINOR)
    @Owner("Manish")
    @Test(description = "Sort Products High to Low")
    public void sortProductHighToLow() {
        List<Double> pricesBeforeSort = inventoryPage.getProductPrices();
        Assert.assertTrue(
                inventoryPage.doPriceSort("Price (high to low)"),
                "Unable to select Price (high to low) sorting option"
        );
        List<Double> actualPriceList = inventoryPage.getProductPrices();
        List<Double> expectedPriceList = pricesBeforeSort.stream().sorted(Comparator.reverseOrder()).toList();
        Assert.assertEquals(actualPriceList, expectedPriceList, "Products are not sorted by price High to Low");
    }


    @Description("TC_04: Add One Product To Cart")
    @Severity(SeverityLevel.MINOR)
    @Owner("Manish")
    @Test(description = "Add One Product To Cart")
    public void addProductToCart() {
        String productName = "Sauce Labs Backpack";
        inventoryPage.addProductToCart(List.of(productName));
        Assert.assertEquals(inventoryPage.getProductButtonText(productName),
                "Remove",
                "Product was not added to cart."
        );
    }

    @Description("TC_05: Add Multiple Product To Cart")
    @Severity(SeverityLevel.MINOR)
    @Owner("Manish")
    @Test(description = "Add Multiple Product To Cart")
    public void addMultipleProductToCart() {
        List<String> productNames = List.of(
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt"
        );
        inventoryPage.addProductToCart(productNames);
        List<String> actualButtonTexts = inventoryPage.getProductButtonText(productNames);
        List<String> expectedButtonTexts =
                List.of("Remove", "Remove", "Remove");
        Assert.assertEquals(
                actualButtonTexts,
                expectedButtonTexts,
                "Not all products were added to cart."
        );
    }

}
