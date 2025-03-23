Feature: Check registered Daily
  Description: Employee checks registered hours of the day
  User: Employee

  Scenario: Employee has registered 5 hours to a activity
    Given a project
    And it has an activity
    And the employee has already registered 5 hours to a activity
    When an employee checks daily registered hours
    Then a map with the name of the activity and 5 hours is returned

  Scenario: Employee has registered 0 hours
    Given a project
    And it has an activity
    When an employee checks daily registered hours
    Then an empty map should be returned

  Scenario: Employee has registered 5 hours to a activity and 4 to another activity
    Given a project
    And it has two activities
    When an employee checks daily registered hours
    Then a map with the name of the first activity and 5 hours is returned
    And a map with the name of the second activity and 4 hours is returned