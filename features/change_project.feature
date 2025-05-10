Feature: Change Project
  Description: A project leader or employee makes changes to a project
  User: Employee
  Background:
    Given a project
    And an employee

  Scenario: set project leader for a project
    When setting employee as project leader
    Then the project leader is employee

  Scenario: set project leader for a project
    Given employee is not the leader of the project
    When setting employee as project leader
    Then error message "Employee is not Project Leader" is given

  Scenario: Start- and end date changed for a project
    Given employee is the leader of the project
    When the start date is set to day 1, month 1, and year 2021
    And the end date is set to day 5, month 5, and year 2021
    Then the start date is day 1, month 1, and year 2021
    And the end date is day 5, month 5, and year 2021

  Scenario: Start- and end date changed by non-project leader for a project
    Given employee is not the leader of the project
    When the start date is set to day 1, month 1, and year 2021
    And the end date is set to day 5, month 5, and year 2021
    Then error message "Employee is not Project Leader" is given

  Scenario: set new name for a project as project leader
    Given employee is the leader of the project
    When setting project name to "new"
    Then project name is "new"

  Scenario: set blank name for a project as project leader
    Given employee is the leader of the project
    When setting project name to ""
    Then error message "Project Name cannot be empty" is given

  Scenario: set new name for a project as non-project leader
    Given employee is not the leader of the project
    When setting project name to "new"
    Then error message "Employee is not Project Leader" is given

  Scenario: set already used name for a project
    When creating a new project named "OtherProject"
    And setting project name to "OtherProject"
    Then error message "Project with that name already exists" is given

  Scenario: set new customer for a project as project leader
    Given employee is the leader of the project
    When setting project customer to "badabing"
    Then project customer is "badabing"

  Scenario: set new customer for a project as non-project leader
    Given employee is not the leader of the project
    # And there is no project leader assigned (evt)
    When setting project customer to "bad customer"
    Then error message "User not authorized to add customer to project \"proj1\"" is given

