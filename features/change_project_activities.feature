Feature: Change Project Activities
  Description: A project leader or employee makes changes to a project activity
  User: Employee
  Background:
    Given a project
    And "huba" exists as employee
    And it has an activity

  Scenario: Start- and end week set by project leader for an activity
    Given employee is the leader of the project
    When the start week is set to 1 in year 2020
    And the end week is set to 12 in year 2020
    Then the start week is 1 in year 2020
    And the end week is 12 in year 2020

#  Scenario: Start- and end week set by non-project leader for an activity
#    Given a project
#    And it has an activity
#    Given "huba" is not the leader of the project
#    When the start week is set to 1 in year 2020
#    And the end week is set to 12 in year 2020
#    Then error message "Employee is not Project Leader" is given

  Scenario: Set estimated hours for an activity as project leader
    Given employee is the leader of the project
    When setting the estimated hours of an activity to 100
    Then the estimated hours of the activity should be 100

#  Scenario: Ser estimated hours for an activity without being project leader
#    Given a project
#    And it has an activity
#    And the user is not the leader of the project
#    When setting the estimated hours of an activity to "100"
#    Then error message "Not Project Leader" is given

#  Scenario: Add user to activity
#    When "huba" is added to the activity
#    Then "huba" is an employee of the activity
