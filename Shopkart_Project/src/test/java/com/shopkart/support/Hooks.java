package com.shopkart.support;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.shopkart.config.AppConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setUp() {

        Configuration.baseUrl = AppConfig.BASE_URL;
        Configuration.browser = AppConfig.BROWSER;
        Configuration.timeout = AppConfig.TIMEOUT;

        Configuration.headless = false;
        Configuration.browserSize = "1920x1080";

    }

    @After
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}