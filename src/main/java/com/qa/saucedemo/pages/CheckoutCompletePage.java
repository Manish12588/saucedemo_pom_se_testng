package com.qa.saucedemo.pages;

import com.qa.saucedemo.utils.ElementUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {
    private WebDriver driver;
    private ElementUtil elementUtil;
    private static final Logger logger = LogManager.getLogger(CheckoutCompletePage.class);

    private final By checkoutCompletePageHeader = By.cssSelector("[data-test='title']");
    private final By orderSuccessFullMessage = By.cssSelector("h2.complete-header");
    private final By deliveryStatus = By.className("complete-text");

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public String getPageTitle(){
        return elementUtil.doElementGetText(checkoutCompletePageHeader);
    }

    public String getOrderSuccessMessage(){
        return elementUtil.doElementGetText(orderSuccessFullMessage);
    }
    public String getOrderDeliveryStatus(){
        return elementUtil.doElementGetText(deliveryStatus);
    }


}
