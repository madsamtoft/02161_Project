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

  Scenario: Employee registers hours to existing activity multiple times
    When creating a new activity "a" in the project
    And "huba" registers 5 hours and 30 minutes to day 2, month 2 and year 2021 to Activity "a"
    Then "huba" has registered 5 hours and 30 minutes to day 2, month 2, and year 2021 to Activity "a"
    When "huba" registers 2 hours and 30 minutes to day 3, month 2 and year 2021 to Activity "a"
    Then "huba" has registered 2 hours and 30 minutes to day 3, month 2, and year 2021 to Activity "a"
    And "huba" has registered 8 hours and 0 minutes in total to Activity "a"

  Scenario: Employee not assigned to existing activity checks hours
    Given creating a new activity "a" in the project
    Then "huba" has registered 0 hours and 0 minutes to day 2, month 2, and year 2021 to Activity "a"
    And "huba" has registered 0 hours and 0 minutes in total to Activity "a"

  Scenario: Employee checks hours on date not assigned hours to
    Given creating a new activity "a" in the project
    When "huba" registers 2 hours and 30 minutes to day 2, month 2 and year 2021 to Activity "a"
    Then "huba" has registered 0 hours and 0 minutes to day 3, month 2, and year 2021 to Activity "a"

  Scenario: Employee registers more than 24 hours to existing activity
    When creating a new activity "a" in the project
    And "huba" registers 100 hours and 0 minutes to day 2, month 2 and year 2021 to Activity "a"
    Then error message "Cannot work more than 24 hours a day" is given

  Scenario: Employee tries to register time on non-existing date
    When creating a new activity "a" in the project
    And "huba" registers 5 hours and 0 minutes to day 32, month 13 and year -1 to Activity "a"
    Then error message "Invalid calendar date" is given

  Scenario: Employee registers negative hours to existing activity
    When creating a new activity "a" in the project
    And "huba" registers -1 hours and 0 minutes to day 2, month 2 and year 2021 to Activity "a"
    Then error message "Hours and minutes can't be negative" is given

  Scenario: Employee registers negative minutes to existing activity
    When creating a new activity "a" in the project
    And "huba" registers 0 hours and -1 minutes to day 2, month 2 and year 2021 to Activity "a"
    Then error message "Hours and minutes can't be negative" is given
