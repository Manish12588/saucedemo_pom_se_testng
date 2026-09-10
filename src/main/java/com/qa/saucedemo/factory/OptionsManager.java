package com.qa.saucedemo.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager {

    private final Properties prop;
    public static final Logger logger = LogManager.getLogger(OptionsManager.class);

    public OptionsManager(Properties properties) {
        this.prop = properties;
    }

    public ChromeOptions getChromeOptions() {
        ChromeOptions chromeOption = new ChromeOptions();
        if (Boolean.parseBoolean(prop.getProperty("HEADLESS"))) {
            logger.info("---- RUNNING IN HEADLESS MODE ----");
            chromeOption.addArguments("--headless=new");
            chromeOption.addArguments("--no-sandbox");
            chromeOption.addArguments("--disable-dev-shm-usage");
        }

        if (Boolean.parseBoolean(prop.getProperty("INCOGNITO"))) {
            logger.info("---- RUNNING IN INCOGNITO MODE ----");
            chromeOption.addArguments("--incognito");
        }
        if (Boolean.parseBoolean(prop.getProperty("remote"))) {
            chromeOption.setCapability("browserName", "chrome");
        }
        return chromeOption;
    }

    public FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOption = new FirefoxOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            firefoxOption.addArguments("--headless=new");
            firefoxOption.addArguments("--no-sandbox");
            firefoxOption.addArguments("--disable-dev-shm-usage");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            firefoxOption.addArguments("--incognito");
        }
        if (Boolean.parseBoolean(prop.getProperty("remote"))) {
            firefoxOption.setCapability("browserName", "firefox");
        }
        return firefoxOption;
    }

    public EdgeOptions getEdgeOptions() {
        EdgeOptions edgeOption = new EdgeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            edgeOption.addArguments("--headless=new");
            edgeOption.addArguments("--no-sandbox");
            edgeOption.addArguments("--disable-dev-shm-usage");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            edgeOption.addArguments("--inprivate");
        }
        if (Boolean.parseBoolean(prop.getProperty("remote"))) {
            edgeOption.setCapability("browserName", "edge");
        }
        return edgeOption;
    }
}
