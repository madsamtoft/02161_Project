Feature: SystemApp tests
  Description: Tests for the whole system
  User:


  Scenario: Create a new project and assign a project leader

  Scenario: set a non existing employee as project leader
#    Given "chjk" does not exist as employee
#    When setting employee as project leader
#    Then error message "Not A Valid Employee" is given

  Scenario: Assign project leader to an existing project

  Scenario: Create a new activity for an existing project

  Scenario: Employee registers more than 59 minutes in an activity
#    When employee tries to register daily time to 0:60 for activity
#    Then error message "Cannot enter more than 59 minutes" is given

  Scenario: Assign an employee to an activity successfully

  Scenario: Assign an employee to an activity unsuccessfully (too many activities assigned)