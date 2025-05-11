Feature: List functions

  Scenario: List Projects
    Given a project
    When getting the project list
    Then the output map is equal to the project list reference map