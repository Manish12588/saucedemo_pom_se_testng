package com.qa.saucedemo.pages;

import com.qa.saucedemo.utils.ElementUtil;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static com.qa.saucedemo.constants.AppConstants.*;

public class InventoryPage {

    private WebDriver driver;
    private ElementUtil elementUtil;
    private static final Logger logger = LogManager.getLogger(InventoryPage.class);

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    @Step("Getting Inventory Page URL")
    public String getInventoryPageUrl() {
        String url = elementUtil.waitForURLContains(INVENTORY_PAGE_FRACTION_URL,DEFAULT_TIMEOUT);
        logger.info("Inventory Page Url {} ",url);
        return url;
    }
}
