Feature: Register and Check time
  Description: Register and Check time spent on an activity
  User: Employee
  Background:
    Given a project
    And "huba" exists as employee

  Scenario: Employee registers 5 hours to non-existing activity on a specific day
    When "huba" registers 5 hours and 30 minutes to day 2, month 2 and year 2021 to Activity "a"
    Then error message "No such activity found" is given

  Scenario: Employee Registers 5 hours to existing activity on a specific day
    When creating a new activity "a" in the project
    When "huba" registers 5 hours and 30 minutes to day 2, month 2 and year 2021 to Activity "a"
    Then "huba" has registered 5 hours and 30 minutes to day 2, month 2, and year 2021 to Activity "a"