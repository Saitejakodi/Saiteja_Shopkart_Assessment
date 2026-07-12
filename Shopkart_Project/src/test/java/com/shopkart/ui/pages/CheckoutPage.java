package com.shopkart.ui.pages;

import com.shopkart.ui.locators.CheckoutLocators;

import static com.codeborne.selenide.Selenide.$x;

public class CheckoutPage {

    public CheckoutPage enterAddress(String address) {

        $x(CheckoutLocators.ADDRESS)
                .setValue(address);

        return this;
    }

    public OrderPage placeOrder() {

        $x(CheckoutLocators.PLACE_ORDER)
                .click();

        return new OrderPage();
    }
}