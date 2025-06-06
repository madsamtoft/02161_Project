Feature: Create Project Activity
  Description: A project leader or employee creates a project activity
  User: Employee
  Background:
    Given a project
    And an employee

  Scenario: Create a new activity for a project as project leader
    Given employee is the leader of the project
#    And it has 1 activities
#      Step is irrelevant for test
    When creating a new activity "A1" in the project
    Then the activity with name "A1" is a part of the project

  Scenario: Create a new activity for a project as non-project leader
    Given employee is not the leader of the project
    And it has 1 activities
    When creating a new activity "A1" in the project
    Then error message "Employee is not Project Leader" is given

  Scenario: Create activity with missing name
    Given employee is the leader of the project
    And it has 1 activities
    When creating a new activity "" in the project
    Then error message "Activity Name cannot be empty" is given

  Scenario: Creating activity with duplicate name
    Given employee is the leader of the project
    And it has 1 activities
#    And activity "A1" already exists in the project
#      Duplicate step definition is not needed
    When creating a new activity "A1" in the project
    And creating a new activity "A1" in the project
    Then error message "Activity Name already taken" is given