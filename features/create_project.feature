Feature: Create Project
  Description: Employee can create a new project
  User: Employee

  Scenario: The creation of a new project succeeded
    When creating a new project named "success"
    And no projects of the same name was found
    Then a new project was created

  Scenario: The creation of a new project failed
    Given a project with the name "failed" already exists
    And the creation of a new project with the name "failed" is attempted created
    Then no new project was created