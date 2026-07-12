package com.shopkart.ui.pages;

import com.codeborne.selenide.Condition;
import com.shopkart.ui.locators.OrderLocators;

import static com.codeborne.selenide.Selenide.$x;

public class OrderPage {

    public boolean isDisplayed() {

        return $x(OrderLocators.ORDER_STATUS)
                .shouldBe(Condition.visible)
                .exists();
    }

    public OrderPage verifyStatus(String expectedStatus) {

        $x(OrderLocators.ORDER_STATUS)
                .shouldHave(Condition.exactText(expectedStatus));

        return this;
    }

    public OrderPage verifyTotal(String expectedTotal) {

        $x(OrderLocators.ORDER_TOTAL)
                .shouldHave(Condition.text(expectedTotal));

        return this;
    }

    public OrderPage verifyDeliveryAddress(String expectedAddress) {

        $x(OrderLocators.DELIVERY_ADDRESS)
                .shouldHave(Condition.exactText(expectedAddress));

        return this;
    }

    public HomePage returnToCatalog() {

        $x(OrderLocators.RETURN_TO_CATALOG)
                .click();

        return new HomePage();
    }

    public long getOrderId() {

        String text = $x(OrderLocators.ORDER_NUMBER)
                .shouldBe(Condition.visible)
                .getText();

        String orderNumber = text.replaceAll("\\D+", "");

        return Long.parseLong(orderNumber);
    }
}