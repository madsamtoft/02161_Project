Feature: Register time Daily
  Description: Employee registers time used for the day
  User: Employee
  Background:
    Given a project
    And "huba" exists as employee


  Scenario: Employee registers 5 hours to existing activity
    Given it has an activity
    When employee tries to register daily time to 5:00 for activity
    Then 5:00 hours have been registered to the activity by employee

  Scenario: Employee registers 5 hours to non-existing activity
    When employee tries to register daily time to 5:00 for activity
    Then error message "No Activity To Registers Hours To" is given

  Scenario: Employee registers 100 hours to existing activity
    Given it has an activity
    When employee tries to register daily time to 100:00 for activity
    Then error message "Cannot work more than 24 hours a day" is given

  Scenario: Employee registers 5:30 hours to existing activity
    Given it has an activity
    When employee tries to register daily time to 5:30 for activity
    Then 5:30 hours have been registered to the activity by employee

  Scenario: Employee registers hours twice to existing activity
    Given it has an activity
    When employee tries to register daily time to 5:00 for activity
    Then 5:00 hours have been registered to the activity by employee
    When employee tries to register daily time to 4:00 for activity
    Then 9:00 hours have been registered to the activity by employee

  Scenario: Employee registers hours too many hours to existing activity
    Given it has an activity
    When employee tries to register daily time to 13:00 for activity
    Then 13:00 hours have been registered to the activity by employee
    When employee tries to register daily time to 12:00 for activity
    Then error message "Cannot work more than 24 hours a day" is given

  Scenario: Employee registers minutes twice
    Given it has an activity
    When employee tries to register daily time to 4:13 for activity
    Then 4:30 hours have been registered to the activity by employee
    When employee tries to register daily time to 2:56 for activity
    Then 7:30 hours have been registered to the activity by employee
  
  Scenario: Check time of an employee that doesn't exist
    Given it has an activity
    Then 0:00 hours have been registered to the activity by employee

#  SYSTEMAPP TEST
#  Scenario: Employee registers more than 59 minutes
##    When employee tries to register daily time to 0:60 for activity
#    Then error message "Cannot enter more than 59 minutes" is given
