Feature: Create Project
  Description: A project leader or employee creates a project
  User: Employee

  Scenario: The creation of a new project succeeded
    When creating a new project named "success"
    Then 1 unique project(s) with name "success" exist(s)

  Scenario: The creation of a new project failed
    When creating a new project named ""
    Then error message "Project Name cannot be empty" is given

  Scenario: Create two projects with the same name
    When creating a new project named "project"
    And creating a new project named "project"
    Then 2 unique project(s) with name "project" exist(s)

#  Scenario: First project in a year has the correct id
#    Given no existing projects
#    And the year is 2025
#    When creating a new project named "project 2025"
#    Then project "project 2025" has id 25001