@smoke
@ui
@api
Feature: Product search

  Scenario: Search returns matching products

    Given "alice" opens the ShopKart login page
    When "alice" logs in
    Then she should be redirected to the home page
    When "alice" searches for "Focus Desk Lamp"
    Then the API should return "Focus Desk Lamp"