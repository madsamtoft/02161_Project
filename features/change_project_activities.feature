Feature: Change Project Activities
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
    And the user is the leader of the project
    When the start week is set to "1"
    And the end week is set to "12"
    Then the start week is "1"
    And the end week is "12"

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
    Then the estimated time of the activity should be "100"

  Scenario: Add user to activity
    Given a project "P1" has an activity "A1" assigned to "user1"
    And I am logged in as a project manager
    When I reassign the activity "A1" to "user2"
    Then the activity should now be assigned to "user2"
