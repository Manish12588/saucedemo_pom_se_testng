package com.qa.saucedemo.pages;

import com.qa.saucedemo.utils.ElementUtil;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

import static com.qa.saucedemo.constants.AppConstants.*;

public class InventoryPage {

    private WebDriver driver;
    private ElementUtil elementUtil;
    private static final Logger logger = LogManager.getLogger(InventoryPage.class);

    private final By productList = By.cssSelector("div.inventory_list > div");
    private final By productPriceList = By.cssSelector("div.inventory_list div.inventory_item_price");
    private final By priceSortDropdown = By.className("product_sort_container");
    private final By cart = By.cssSelector("a.shopping_cart_link");


    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    @Step("Getting Inventory Page URL")
    public String getInventoryPageUrl() {
        String url = elementUtil.waitForURLContains(INVENTORY_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
        logger.info("Inventory Page Url {} ", url);
        return url;
    }

    @Step("Getting Products List Count")
    public int getProductListCount() {
        return elementUtil.waitForAllElementVisible(productList, MEDIUM_DEFAULT_TIMEOUT).size();
    }


    @Step("Get The Product Price")
    public List<Double> getProductPrices() {
        return elementUtil.getElementTextList(productPriceList).stream()
                .map(price -> Double.parseDouble(price.replace("$", "")))
                .toList();
    }

    public boolean doPriceSort(String sortOption) {
        return elementUtil.doSelectDropDownByVisibleText(priceSortDropdown, sortOption);
    }

    public void addProductToCart(List<String> productName) {
        for (String product : productName) {
            By addToCart = By.xpath("//div[text()='" + product + "']/../../following-sibling::div/button");
            elementUtil.doClick(addToCart);
        }
    }

    public String getProductButtonText(String productName) {
        By productButton = By.xpath("//div[text()='" + productName + "']/../../following-sibling::div/button");
        return elementUtil.doElementGetText(productButton);
    }

    public List<String> getProductButtonText(List<String> productName) {
        List<String> buttonTexts = new ArrayList<>();
        for (String product : productName) {
            By productButton = By.xpath(
                    "//div[text()='" + product + "']/../../following-sibling::div/button"
            );
            String buttonText = elementUtil.doElementGetText(productButton);
            buttonTexts.add(buttonText);
        }
        return buttonTexts;
    }

    public CartPage navigateToCartPage(){
        elementUtil.clickWhenReady(cart,DEFAULT_TIMEOUT);
        return new CartPage(driver);
    }
}
