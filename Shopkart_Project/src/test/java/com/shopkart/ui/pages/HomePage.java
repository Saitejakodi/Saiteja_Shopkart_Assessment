package com.shopkart.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.shopkart.ui.components.Header;
import com.shopkart.ui.components.ProductCard;
import com.shopkart.ui.locators.HomeLocators;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage {

    private final Header header = new Header();

    public Header header() {
        return header;
    }

    public boolean isDisplayed() {

        return $x(HomeLocators.SEARCH_BOX)
                .shouldBe(Condition.visible)
                .exists();
    }

    public HomePage searchProduct(String product) {

        $x(HomeLocators.SEARCH_BOX)
                .setValue(product);

        $x(HomeLocators.SEARCH_BUTTON)
                .click();

        return this;
    }

    public ProductCard product(String productName) {

        SelenideElement root =
                $x(String.format(HomeLocators.PRODUCT_BY_NAME, productName));

        root.shouldBe(Condition.visible);

        return new ProductCard(root);
    }

    public HomePage verifyProductVisible(String productName) {

        product(productName);

        return this;
    }

    public HomePage verifyOutOfStock(String productName) {

        product(productName)
                .verifyOutOfStock();

        return this;
    }
}