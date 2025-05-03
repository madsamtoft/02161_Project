Feature: Register time Daily
  Description: Employee registers time used for the day
  User: Employee
  Background:
    Given a project
    And "huba" exists as employee


  Scenario: Employee registers 5 hours to existing activity
    Given it has an activity
    When "huba" tries to register daily time to 5 for activity
    Then 5 hours have been registered to the activity

  Scenario: Employee registers 5 hours to non-existing activity
    When "huba" tries to register daily time to 5 for activity
    Then error message "No Activity To Registers Hours To" is given