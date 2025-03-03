Feature: Registration flow

  @Regression
  Scenario: Customer can successfully register
    Given Customer is on the registration page
    And Customer accepts All Cookies popup
    When Customer completes registration form using following data:
      | emailAddress | test1@yopmail.com |
      | password     | password          |
      | dayOfBirth   | 15                |
      | monthOfBirth | December          |
      | yearOfBirth  | 1986              |