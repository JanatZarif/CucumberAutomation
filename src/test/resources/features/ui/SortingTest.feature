@ui
Feature: Sorting Functionality
  Scenario: Verify user can sort items by price -high to low
    Given user open website
    When user login with username "login.username" and password "login.password"
    Then verify user is on home page
    When user click on filter drop down menu
    Then user sorting the product by price from high to low
    And verify products are displayed by price high to low
    