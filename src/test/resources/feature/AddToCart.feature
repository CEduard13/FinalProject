Feature: Cart Functionality

  @Smoke
  Scenario: Add a product to the cart
    Given Customer navigates to the Infinity Fashion homepage for shopping
    When Customer searches for product "rochie"
    And Customer hovers and clicks on the first product picture of the search results
    And Customer clicks on button "M"
    And Customer adds the first product to the cart
    Then The product should be present in the cart

  @Smoke
  Scenario: Remove a product from the cart
    Given Customer has a product in the cart
    When Customer removes the product from the cart
    Then The cart should be empty

