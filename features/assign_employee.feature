Feature: Assign Employee
  Description: Assign an Employee to an Activity
  User: Employee
  Background:
    Given a project
    And it has an activity
    And "huba" exists as employee
    And "buba", "bepo", "nepo" exist as employees

  Scenario: Successfully add employee to an activity
    Given "huba" is assigned to 0 activities
    And "huba" is not assigned to the activity in the project
    When "huba" is assigned to the activity in the project
    Then "huba" is successfully assigned to the activity in the project

  Scenario: The employee is already assigned to the given activity
    Given "huba" is assigned to 0 activities
    And "huba" is not assigned to the activity in the project
    When "huba" is assigned to the activity in the project
    Then "huba" is successfully assigned to the activity in the project
    When "huba" is assigned to the activity in the project
    Then error message "Employee is already assigned to this activity" is given

#    Scenario: The employee is already assigned to 20 activities
#        And "huba" is assigned to 20 activities
#        And "e" is not assigned to "a" in "p"
#        When "e" is assigned to "a" in "p"
#        Then "e" wont be assigned to "a" in "p"
#        And error message "Too Many Activities" is given
#
