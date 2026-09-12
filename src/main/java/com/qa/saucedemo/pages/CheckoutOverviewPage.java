package com.qa.saucedemo.pages;

import com.qa.saucedemo.utils.ElementUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage {

    private WebDriver driver;
    private ElementUtil elementUtil;
    private static final Logger logger = LogManager.getLogger(CheckoutOverviewPage.class);

    private final By cancelButton = By.id("cancel");
    private final By finishButton = By.id("finish");
    private final By checkoutOverviewPageHeader = By.cssSelector("[data-test='title']");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public String getPageTitle(){
        return elementUtil.doElementGetText(checkoutOverviewPageHeader);
    }

    public CheckoutCompletePage doFinishPurchase(){
        elementUtil.doClick(finishButton);
        return new CheckoutCompletePage(driver);
    }
}
