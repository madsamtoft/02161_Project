Feature: Assign Employee
  Description: Assign an Employee to an Activity
  User: Employee
  Background:
    Given a project
#    And a project leader
#      Comment: A project leader is not needed on the project to test
#    And it has 1 activities
#    And "abcd" exists as employee
    And an employee
#    And employee is the leader of the project
#    And "buba", "bepo", "nepo" exist as employees

  Scenario: Successfully add employee to an activity
    Given it has 1 activities
    And the employee is assigned to 0 activities
    And the employee is not assigned to the activity in the project
    When the employee is assigned to the activity in the project
    Then the employee is successfully assigned to the activity in the project

  Scenario: The employee is already assigned to the given activity
    Given it has 1 activities
    And the employee is assigned to 0 activities
    And the employee is not assigned to the activity in the project
    When the employee is assigned to the activity in the project
    Then the employee is successfully assigned to the activity in the project
    When the employee is assigned to the activity in the project
    Then error message "Employee is already assigned to this activity" is given

#  Scenario: The employee is already assigned to 20 activities
#    Given it has 21 activities
#    And the employee is assigned to 20 activities
#    When the employee is assigned to activity "act21"
#    Then error message "Too Many Activities" is given