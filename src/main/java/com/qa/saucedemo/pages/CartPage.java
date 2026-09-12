package com.qa.saucedemo.pages;

import com.qa.saucedemo.models.ProductDetails;
import com.qa.saucedemo.utils.ElementUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.qa.saucedemo.constants.AppConstants.MEDIUM_DEFAULT_TIMEOUT;

public class CartPage {
    private WebDriver driver;
    private ElementUtil elementUtil;
    private static final Logger logger = LogManager.getLogger(InventoryPage.class);

    private final By cartProductList = By.cssSelector("div.cart_list > div.cart_item");
    private final By cartProductNames = By.cssSelector("[data-test='inventory-item-name']");
    private final By cartProductDescription = By.cssSelector("[data-test='inventory-item-desc']");
    private final By cartProductPrice = By.cssSelector("[data-test='inventory-item-price']");
    private final By cartProductQuantity = By.cssSelector("[data-test='item-quantity']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public void removeProductFromCart(List<String> productName) {
        for (String product : productName) {
            By removeCartProduct = By.xpath("//div[text()='" + product + "']/../following-sibling::div//button");
            elementUtil.doClick(removeCartProduct);
        }
    }

    public List<String> getCartProductNames() {
        return elementUtil.getElementTextList(cartProductNames);
    }


    public ProductDetails getCartProductDetails(String productName) {
        WebElement productContainer = getProductContainer(productName);
        String name = getProductName(productContainer);
        String description = getProductDescription(productContainer);
        String price = getProductPrice(productContainer);
        String quantity = getProductQuantity(productContainer);

        return new ProductDetails(
                name,
                description,
                price,
                quantity
        );
    }

    private WebElement getProductContainer(String productName) {
        By productContainer = By.xpath(
                "//div[@data-test='inventory-item' and " +
                        ".//div[@data-test='inventory-item-name' and text()='" +
                        productName + "']]"
        );
        return elementUtil.waitForElementVisible(productContainer, MEDIUM_DEFAULT_TIMEOUT);
    }

    private String getProductName(WebElement productContainer) {
        return productContainer.findElement(cartProductNames).getText();
    }

    private String getProductDescription(WebElement productContainer) {
        return productContainer.findElement(cartProductDescription).getText();
    }

    private String getProductPrice(WebElement productContainer) {
        return productContainer.findElement(cartProductPrice).getText();
    }

    private String getProductQuantity(WebElement productContainer) {
        return productContainer.findElement(cartProductQuantity).getText();
    }


}
