Feature: List functions

  Scenario: List Projects
    Given a project
    When getting the project list
    Then the output map is equal to the project list reference map

  Scenario: List activities
    Given a project
    And creating a new activity "activity" in the project
    When getting the activity list
    Then the output list is equal to the activity list reference list containing "activity"

  Scenario: List firm activities
    When creating a new firm activity "a"
    When getting the firm activity list
    Then the output list is equal to the firm activity list reference list containing "a"
