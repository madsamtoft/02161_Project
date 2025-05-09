Feature: Assign Employee
  Description: Assign an Employee to an Activity
  User: Employee
  Background:
    Given a project
    And an employee

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

  Scenario: The employee is already assigned to 20 activities with overlapping dates
    Given it has 21 activities
    And the employee is assigned to 20 activities
    When the employee is assigned to activity "act21"
    Then error message "Too Many Activities Assigned To Employee" is given

    #TODO: MORE TESTING

  Scenario: The employee is already assigned to 20 where not all dates are overlapping
    Given it has 21 activities
    And activity 1 to 10 has start-date 1/1/2025 and end date 6/6/2025
    And activity 11 to 20 has start-data 7/7/2025 and end date 12/12/2025
    And activity 21 to 21 has start-data 6/6/2025 and end date 7/7/2025
    Then error message "Too Many Activities Assigned To Employee" is given