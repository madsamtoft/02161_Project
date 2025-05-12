Feature: Get project hours
  Background:
    Given a project
    And an activity in the project
    And the employee is registered in the system
    When setting the estimated hours of an activity to 100

  Scenario: Get project estimated hours
    When getting the project estimated hours
    Then the project estimated hours are 100

  Scenario: Get project total hours
    When getting the project total hours
    Then the project total hours are 0.0

  Scenario: Get project estimated hour list
    When getting the project estimated hour list
    Then the project estimated hour list is equal to 100

  Scenario: Get project total hour list
    When getting the project total hour list
    Then the project total hour list is equal to 0

  Scenario: Register time then get project total hours
    When the employee registers 5 hours and 30 minutes to the activity today
    And getting the project total hours
    Then the project total hours are 5.5

  Scenario: Register time then get project total hour list
    When the employee registers 5 hours and 30 minutes to the activity today
    And getting the project total hour list
    Then the project total hour list is equal to 5.5