Feature: Create Project Activity
  Scenario: Create a new activity for a project as project leader
    Given a project
    And the user is the leader of the project
    When creating a new activity "A1"
    Then the activity "A1" is a part of the project

  Scenario: Create a new activity for a project as non-project leader
    Given a project
    And the user is not the leader of the project
    When creating a new activity "A1"
    Then exception "Not Project Leader" is thrown

  Scenario: Create activity with missing name
    Given a project
    When creating a new activity ""
    Then exception "Invalid Activity Name" is thrown

  Scenario: Creating activity with duplicate name
    Given a project
    And it has an activity "A1"
    When creating a new activity "A1"
    Then exception "Activity Name Already Taken" is thrown