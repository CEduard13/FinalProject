Feature: Login

  @Smoke @Europe @WIP
  Scenario: Successful login
    Given Customer accepts All Cookies popup
    And Customer clicks signIn button
    When Customer enters "mstest1@yopmail.com" and "19118612As"
    And Customer clicks login button
    Then currentURL contains "myaccount?registration=false"
    #And Customer clicks Checkout button
    #Then currentURL contains "checkout"

  @Smoke
  Scenario Outline: Failed login
    Given Customer accepts All Cookies popup
    And Customer clicks signIn button
    When Customer enters "<username>" and "<password>"
    # customer clicks login button
    # assertion on error message
    Examples:
      | username          | password  | expectedBehaviour   |
      | test1@yopmail.com | password1 | invalid credentials |
      | test2@yopmail.com | password2 | invalid credentials |
      |                   | password  | empty username      |
      | test4@yopmail.com |           | empty password      |


