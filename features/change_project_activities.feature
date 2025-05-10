Feature: Change Project Activities
  Description: A project leader or employee makes changes to a project activity
  User: Employee
  Background:
    Given a project
    And an employee
    And it has 1 activities

  Scenario: Change project name by project leader
    Given employee is the leader of the project
    When the name of the activity is changed to "newName"
    Then the name of the activity is "newName"

  Scenario: Start- and end week set by project leader for an activity
    Given employee is the leader of the project
    When the start week is set to 1 in year 2020
    And the end week is set to 12 in year 2020
    Then the start week is 1 in year 2020
    And the end week is 12 in year 2020

  Scenario: Start- and end week set by non-project leader for an activity
    Given employee is not the leader of the project
    When the start week is set to 1 in year 2020
    And the end week is set to 12 in year 2020
    Then error message "Employee is not Project Leader" is given

  Scenario: Start- and end week set by project leader for an activity with invalid date
    Given employee is the leader of the project
    When the start week is set to -1 in year 2020
    And the end week is set to 56 in year 2020
    Then error message "Invalid calendar date" is given

  Scenario: Set estimated hours for an activity as project leader
    Given employee is the leader of the project
    When setting the estimated hours of an activity to 100
    Then the estimated hours of the activity should be 100

  Scenario: Set estimated hours for an activity without being project leader
    Given employee is not the leader of the project
    When setting the estimated hours of an activity to 100
    Then error message "Employee is not Project Leader" is given
