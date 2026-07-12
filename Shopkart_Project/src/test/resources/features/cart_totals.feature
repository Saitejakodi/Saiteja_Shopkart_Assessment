@api
@db
Feature: Cart totals

  Scenario: Cart total equals sum of line totals

    Given Alice has an empty cart
    When she adds 2 of "SKU-BAG"
    Then the API cart total should be 99800
    And the database cart total should be 99800