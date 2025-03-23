Feature: Create Project Activity
  Description: A project leader or employee creates a project activity
  User: Employee

  Scenario: Create a new activity for a project as project leader
    Given a project
    And the user is the leader of the project
    When creating a new activity "A1"
    Then the activity "A1" is a part of the project

  Scenario: Create a new activity for a project as non-project leader
    Given a project
    And the user is not the leader of the project
    When creating a new activity "A1"
    Then error message "Not Project Leader" is given

  Scenario: Create activity with missing name
    Given a project
    When creating a new activity ""
    Then error message "Invalid Activity Name" is given

  Scenario: Creating activity with duplicate name
    Given a project
    And it has an activity "A1"
    When creating a new activity "A1"
    Then error message "Activity Name Already Taken" is given