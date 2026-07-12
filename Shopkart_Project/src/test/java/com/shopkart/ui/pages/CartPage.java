package com.shopkart.ui.pages;

import com.codeborne.selenide.Condition;
import com.shopkart.ui.components.Header;
import com.shopkart.ui.locators.CartLocators;

import static com.codeborne.selenide.Selenide.$x;

public class CartPage {

    private final Header header = new Header();

    public Header header() {
        return header;
    }

    public CartPage verifyLineTotal(String sku, String expectedAmount) {

        $x(String.format(CartLocators.CART_LINE, sku))
                .$x(CartLocators.LINE_TOTAL)
                .shouldHave(Condition.text(expectedAmount));

        return this;
    }

    public CartPage verifyCartTotal(String expectedAmount) {

        $x(CartLocators.CART_TOTAL)
                .shouldHave(Condition.text(expectedAmount));

        return this;
    }

    public HomePage continueShopping() {

        $x(CartLocators.CONTINUE_SHOPPING).click();

        return new HomePage();
    }

    public CheckoutPage checkout() {

        $x(CartLocators.CHECKOUT).click();

        return new CheckoutPage();
    }
}