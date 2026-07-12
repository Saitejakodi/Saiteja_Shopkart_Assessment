package com.shopkart.ui.pages;

import com.shopkart.config.AppConfig;
import com.shopkart.ui.locators.LoginLocators;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    public LoginPage openLoginPage() {
        open(AppConfig.BASE_URL + "/login");
        return this;
    }

    public LoginPage enterEmail(String email) {
        $x(LoginLocators.EMAIL).setValue(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        $x(LoginLocators.PASSWORD).setValue(password);
        return this;
    }

    public HomePage clickSignIn() {
        $x(LoginLocators.SIGN_IN).click();
        return new HomePage();
    }

    public HomePage login(String email, String password) {
        return openLoginPage()
                .enterEmail(email)
                .enterPassword(password)
                .clickSignIn();
    }
}