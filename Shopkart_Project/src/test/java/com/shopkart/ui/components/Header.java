package com.shopkart.ui.components;

import com.shopkart.ui.locators.HeaderLocators;
import com.shopkart.ui.pages.CartPage;
import com.shopkart.ui.pages.HomePage;
import com.shopkart.ui.pages.LoginPage;

import static com.codeborne.selenide.Selenide.$x;

public class Header {

    public HomePage clickBrand() {
        $x(HeaderLocators.BRAND).click();
        return new HomePage();
    }

    public HomePage clickCatalog() {
        $x(HeaderLocators.CATALOG).click();
        return new HomePage();
    }

    public CartPage clickCart() {
        $x(HeaderLocators.CART).click();
        return new CartPage();
    }

    public LoginPage clickSignOut() {
        $x(HeaderLocators.SIGN_OUT).click();
        return new LoginPage();
    }

    public String loggedInUser() {
        return $x(HeaderLocators.LOGGED_IN_USER).getText();
    }
}