Feature: Search Feature

  @Smoke
  Scenario: Successful search
    Given Customer is on the homepage
    When Customer searches for "rochie"
    Then Search results should display items containing "rochie"

  @Smoke
  Scenario: Search with no results
    Given Customer is on the homepage
    When Customer searches for "ciocolata"
    Then Search results should display a "Cautarea nu a intors niciun rezultat" message