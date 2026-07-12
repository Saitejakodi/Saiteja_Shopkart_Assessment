@api
@negative
Feature: Out of stock

  Scenario: Quantity exceeds available stock

    Given Alice has an empty cart
    When she adds 999 of "SKU-BAG"
    Then the response status should be 409