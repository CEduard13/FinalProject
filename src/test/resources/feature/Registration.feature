Feature: Registration

  @Smoke
  Scenario: Create a new account
    Given Customer clicks on register link "Register"
    When Customer opens the registration page
    And Customer enters registration details using following data :
      | Email Address    | testixi32ngmail@gmail.com         |
      | Name             | Catalin                       |
      | Phone            | 0721345218                    |
      | Delivery Address | Bucuresti, Str.Lacramioarelor |
      | Password         | parola321                     |
      | Password Confirm | parola321                     |
    And Customer clicks registration button "Inregistreaza contul"
    Then Customer should receive a successful registration confirmation
