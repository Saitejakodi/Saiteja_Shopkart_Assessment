@e2e
@smoke
Feature: Checkout places an order and the backend agrees

  Scenario: Alice checks out a single-item cart

    Given "alice" opens the ShopKart login page
    When "alice" logs in
    Then she should be redirected to the home page
    When "alice" searches for "Focus Desk Lamp"
    And "alice" adds "Focus Desk Lamp" to the cart
    And "alice" opens the cart
    Then the cart total should be "₹799.00"
    And the line total for "SKU-LMP" should be "₹799.00"
    When "alice" proceeds to checkout
    And "alice" enters the delivery address "Hyderabad, Telangana"
    And "alice" places the order
    Then the order status should be "PLACED"
    And the order total should be "₹799.00"
    And the delivery address should be "Hyderabad, Telangana"
    Then the order should be available through the API
    And exactly one PLACED order should exist in the database