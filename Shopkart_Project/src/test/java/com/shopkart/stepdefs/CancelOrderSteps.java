package com.shopkart.stepdefs;

import com.shopkart.api.AuthClient;
import com.shopkart.api.CartClient;
import com.shopkart.api.OrderClient;
import com.shopkart.api.model.Cart;
import com.shopkart.api.model.LoginResponse;
import com.shopkart.api.model.Order;
import com.shopkart.data.db.OrderRepository;
import com.shopkart.support.WorldContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CancelOrderSteps {

    private final WorldContext context;

    private final AuthClient authClient = new AuthClient();
    private final CartClient cartClient = new CartClient();
    private final OrderClient orderClient = new OrderClient();
    private final OrderRepository repository = new OrderRepository();


    public CancelOrderSteps(WorldContext context) {
        this.context = context;
    }

    @When("Alice cancels the order")
    public void cancelOrder() {

        orderClient.cancelOrder(
                context.getToken(),
                context.getOrderId()
        );
    }

    @Then("the order status should become {string}")
    public void verifyCancelled(String status) {

        assertEquals(
                status,
                repository.orderStatus(
                        context.getOrderId()
                )
        );
    }

    @When("Alice cancels the same order again")
    public void cancelAgain() {

        context.setResponseStatus(
                orderClient.cancelOrderStatusCode(
                        context.getToken(),
                        context.getOrderId()
                )
        );
    }
}