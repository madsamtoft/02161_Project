Feature: Check registered Daily
  Description: Employee checks registered hours of the day
  User: Employee

  Scenario: Employee has registered 5 hours to a activity
    Given a project
    And it has an activity
    And the employee has already registered 5 hours to a activity
    When an employee checks daily registered hours
    Then 5 hours is returned
#
#  Scenario: Employee has registered 0 hours
#    Given a project
#    And it has an activity
#    When an employee checks daily registered hours
#    0 will be returned
#
#  Scenario: Employee has registered 5 hours to a activity and 4 to another activity
#    Given a project
#    And it has two activities
#    When an employee checks daily registered hours
#    9 will be returned