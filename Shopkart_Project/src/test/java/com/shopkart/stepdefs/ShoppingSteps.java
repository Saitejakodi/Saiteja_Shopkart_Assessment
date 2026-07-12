package com.shopkart.stepdefs;

import com.shopkart.support.WorldContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ShoppingSteps {

    private final WorldContext context;

    public ShoppingSteps(WorldContext context) {
        this.context = context;
    }

    @When("{string} searches for {string}")
    public void searchesFor(String user, String product) {

        context.getHomePage()
                .searchProduct(product);
    }

    @When("{string} adds {string} to the cart")
    public void addsToTheCart(String user, String product) {

        context.getHomePage()
                .product(product)
                .addToCart();
    }

    @When("{string} opens the cart")
    public void opensTheCart(String user) {

        context.setCartPage(
                context.getHomePage()
                        .header()
                        .clickCart()
        );
    }

    @Then("the cart total should be {string}")
    public void verifyCartTotal(String expectedTotal) {

        context.getCartPage()
                .verifyCartTotal(expectedTotal);
    }

    @Then("the line total for {string} should be {string}")
    public void verifyLineTotal(String sku,
                                String expectedTotal) {

        context.getCartPage()
                .verifyLineTotal(sku, expectedTotal);
    }

    @When("{string} proceeds to checkout")
    public void proceedsToCheckout(String user) {

        context.setCheckoutPage(
                context.getCartPage()
                        .checkout()
        );
    }

    @Then("the order status should be {string}")
    public void verifyOrderStatus(String status) {

        context.getOrderPage()
                .verifyStatus(status);
    }

    @Then("the order total should be {string}")
    public void verifyOrderTotal(String total) {

        context.getOrderPage()
                .verifyTotal(total);
    }

    @Then("the delivery address should be {string}")
    public void verifyDeliveryAddress(String address) {

        context.getOrderPage()
                .verifyDeliveryAddress(address);
    }
}