Feature: Login

  @Smoke
  Scenario: Successful login
    Given Customer clicks on "Login" link
    When Customer is on the login page
    And Customer enters valid inputs
    And Customer clicks login form button "conectare"
    Then Customer verifies the homepage is displayed






