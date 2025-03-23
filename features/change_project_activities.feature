Feature: Change Project Activities
  Scenario: Change the name of an existing activity
    Given a project "P1" has an activity "A1" with assigned user "user1"
    And I am logged in as a project manager
    When I rename the activity "Initial Planning" to "Preliminary Planning"
    Then the activity should be updated with the new name "Preliminary Planning"

  Scenario: Reassign an activity to a different user
    Given a project "P1" has an activity "A1" assigned to "user1"
    And I am logged in as a project manager
    When I reassign the activity "A1" to "user2"
    Then the activity should now be assigned to "user2"
