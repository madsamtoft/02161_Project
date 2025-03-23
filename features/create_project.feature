Feature: Create Project
  Description: A project leader or employee creates a project
  User: Employee

  Scenario: The creation of a new project succeeded
    When creating a new project named "success"
    And no projects of the same name was found
    Then a project with name "success" is created

  Scenario: The creation of a new project failed
    When creating a new project named "failed"
    And a project named "failed" already exists
    Then error message "Project Name Is Taken" is given