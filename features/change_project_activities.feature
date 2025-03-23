Feature: Change Project Activities
  Description: A project leader or employee makes changes to a project activity
  User: Employee

  Scenario: Start- and end week set by project leader for an activity
    Given a project
    And it has an activity
    And the user is the leader of the project
    When the start week is set to "1"
    And the end week is set to "12"
    Then the start week is "1"
    And the end week is "12"

  Scenario: Start- and end week set by non-project leader for an activity
    Given a project
    And it has an activity
    And the user is not the leader of the project
    When the start week is set to "1"
    And the end week is set to "12"
    Then error message "Not Project Leader" is given

  Scenario: Set estimated hours for an activity as project leader
    Given a project
    And it has an activity
    And the user is the leader of the project
    When setting the estimated hours of an activity to "100"
    Then the estimated time of the activity should be "100"

  Scenario: Ser estimated hours for an activity without being project leader
    Given a project
    And it has an activity
    And the user is not the leader of the project
    When setting the estimated hours of an activity to "100"
    Then error message "Not Project Leader" is given

  Scenario: Add user to activity
    Given a project
    And it has an activity
    When i add myself to the project
    Then i am a part of the project
