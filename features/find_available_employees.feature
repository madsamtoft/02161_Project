#Feature: Find Available Employees
#  Description: Employee gets a list of all employees who are not currently working on activities
#  User: Employee
#
#  Background:
#    Given these employees registered in the app
#      | bert | deja | karl | mads | seba |
#    And a project
#    And an activity in the project
#
#  Scenario: All employees are found
#    When searching for available employees
#    Then employees found are
#      | bert | deja | karl | mads | seba |
#
#  Scenario: Some employees are found
#    Given employee with name "bert" is assigned to activity
#    And employee with name "karl" is assigned to activity
#    When searching for available employees
#    Then employees found are
#      | deja | mads | seba |
#
#  Scenario: No employees are found
#    Given employee with name "bert" is assigned to activity
#    And employee with name "deja" is assigned to activity
#    And employee with name "karl" is assigned to activity
#    And employee with name "mads" is assigned to activity
#    And employee with name "seba" is assigned to activity
#    When searching for available employees
#    Then no employees are found
