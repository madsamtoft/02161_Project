Feature: Find Available Employees
  Description: Employee gets a list of all employees who are not currently working on activities
  User: Employee

  Background:
    Given these employees registered in the app
      | bert | deja | karl | mads | seba |
    And a project
    And it has 1 activities

  Scenario: All employees are found
    When searching for available employees
    Then employees found are
      | bert | deja | karl | mads | seba | huba |

  Scenario: Some employees are found
    When the employee with name "bert" is assigned to the activity in the project
    And the employee with name "karl" is assigned to the activity in the project
    And searching for available employees
    Then employees found are
      | deja | mads | seba | huba |

  Scenario: No employees are found
    When the employee with name "bert" is assigned to the activity in the project
    And the employee with name "deja" is assigned to the activity in the project
    And the employee with name "karl" is assigned to the activity in the project
    And the employee with name "mads" is assigned to the activity in the project
    And the employee with name "seba" is assigned to the activity in the project
    And the employee with name "huba" is assigned to the activity in the project
    And searching for available employees
    Then no employees are found


  Scenario: All employees are assigned to the activity but it ended in the past
    When the employee with name "bert" is assigned to the activity in the project
    And the employee with name "deja" is assigned to the activity in the project
    And the employee with name "karl" is assigned to the activity in the project
    And the employee with name "mads" is assigned to the activity in the project
    And the employee with name "seba" is assigned to the activity in the project
    And the employee with name "huba" is assigned to the activity in the project
    And the end week is set to 10 in year 1066
    And searching for available employees
    Then employees found are
      | bert | deja | karl | mads | seba | huba |
