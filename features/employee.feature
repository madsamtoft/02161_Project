Feature: Register Employee in System
  Description: Register a new employee in the system
  User: Employee

  Scenario: Successfully add a new employee
    When registering an employee with identifier "asdf"
    Then "asdf" is a registered employee

  Scenario: Fail to add employee with blank id
    When registering an employee with identifier ""
    Then error message "Employee username must be 1-4 letters" is given

  Scenario: Fail to add employee with too long id
    When registering an employee with identifier "asdfg"
    Then error message "Employee username must be 1-4 letters" is given

  Scenario: Fail to add employee with numbers in id
    When registering an employee with identifier "emp1"
    Then error message "Employee username must be 1-4 letters" is given

  Scenario: Fail to add duplicate employee
    When registering an employee with identifier "asdf"
    Then "asdf" is a registered employee
    When registering an employee with identifier "asdf"
    Then error message "An employee with that username already exists" is given

  Scenario: Employee does not exist
    Given a project
    And it has 1 activities
    When the employee with name "none" is assigned to the activity in the project
    Then error message "Employee none does not exist" is given