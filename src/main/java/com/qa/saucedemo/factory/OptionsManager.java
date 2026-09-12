package com.qa.saucedemo.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class OptionsManager {

    private final Properties prop;
    public static final Logger logger = LogManager.getLogger(OptionsManager.class);

    public OptionsManager(Properties properties) {
        this.prop = properties;
    }

    private boolean flag(String key) {
        return Boolean.parseBoolean(prop.getProperty(key));
    }

    /**
     * Shared by Chrome and Edge - both Chromium, both hit the same leak-detection modal.
     */
    private Map<String, Object> chromiumPasswordManagerPrefs() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        return prefs;
    }

    public ChromeOptions getChromeOptions() {
        ChromeOptions chromeOption = new ChromeOptions();
        chromeOption.setExperimentalOption("prefs", chromiumPasswordManagerPrefs());
        if (flag("HEADLESS")) {
            logger.info("---- RUNNING IN HEADLESS MODE ----");
            chromeOption.addArguments("--headless=new");
            chromeOption.addArguments("--no-sandbox");
            chromeOption.addArguments("--disable-dev-shm-usage");
        }
        if (flag("INCOGNITO")) {
            logger.info("---- RUNNING IN INCOGNITO MODE ----");
            chromeOption.addArguments("--incognito");
        }
        if (flag("remote")) {
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
        edgeOption.setExperimentalOption("prefs", chromiumPasswordManagerPrefs());
        if (flag("headless")) {
            edgeOption.addArguments("--headless=new");
            edgeOption.addArguments("--no-sandbox");
            edgeOption.addArguments("--disable-dev-shm-usage");
        }
        if (flag("incognito")) {
            edgeOption.addArguments("--inprivate");
        }
        if (flag("remote")) {
            edgeOption.setCapability("browserName", "edge");
        }
        return edgeOption;
    }
}
