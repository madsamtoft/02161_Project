Feature: Check registered Daily
  Description: Employee checks registered hours of the day
  User: Employee

  Scenario: Employee has registered 5 hours to a activity
    Given a project
    And an employee
    And it has 1 activities
    And registering an employee with identifier "abcd"
    And the employee has already registered 5 hours to a activity
    When an employee checks daily registered hours
    Then 5 hours is returned

  Scenario: Employee has registered 0 hours
    Given a project
    And an employee
    And it has 1 activities
    And registering an employee with identifier "abcd"
    When an employee checks daily registered hours
    Then 0 hours is returned

  Scenario: Employee has registered 5 hours to a activity and 4 to another activity
    Given a project
    And an employee
    And it has two activities
    And registering an employee with identifier "abcd"
    When the employee registers 5 hours to "activity1"
    When the employee registers 4 hours to "activity2"
    When an employee checks daily registered hours
    Then 9 hours is returned