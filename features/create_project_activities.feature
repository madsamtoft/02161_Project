Feature: Create Project Activity
  Scenario: Successfully create a new activity for a project
    Given a project with ID "P1" exists
    And I am logged in as a project manager
    When I create a new activity with the name "A1"
    And I assign it to user "user1"
    Then the activity "Design Phase" should be added to project "P1"
    And it should be assigned to user "user1"

  Scenario: Fail to create activity with missing name
    Given a project with ID "P1" exists
    And I am logged in as a project manager
    When I try to create an activity with an empty name
    Then I should see an error message "Activity name is required"