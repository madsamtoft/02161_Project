Feature: Register and Check time
  Description: Register and Check time spent on an activity
  User: Employee
  Background:
    Given a project
    And "huba" exists as employee

  Scenario: Employee registers 5 hours to non-existing activity on a specific day
    When "huba" registers 5 hours and 30 minutes to day 2, month 2 and year 2021 to Activity "a"
    Then error message "No such activity found" is given

  Scenario: Employee Registers 5 hours to existing activity on a specific day
    When creating a new activity "a" in the project
    When "huba" registers 5 hours and 30 minutes to day 2, month 2 and year 2021 to Activity "a"
    Then "huba" has registered 5 hours and 30 minutes to day 2, month 2, and year 2021 to Activity "a"


#old scenarios

#  Scenario: Employee registers 5 hours to non-existing activity
#    When employee tries to register daily time to 5:00 for activity
#    Then error message "No such activity found" is given
#
#  Scenario: Employee registers 5 hours to existing activity
#    Given it has an activity
#    When employee tries to register daily time to 5:00 for activity
#    Then 5:00 hours have been registered to the activity by employee
#
#
#  Scenario: Employee registers 100 hours to existing activity
#    Given it has an activity
#    When employee tries to register daily time to 100:00 for activity
#    Then error message "Cannot work more than 24 hours a day" is given
#
#  Scenario: Employee registers 5:30 hours to existing activity
#    Given it has an activity
#    When employee tries to register daily time to 5:30 for activity
#    Then 5:30 hours have been registered to the activity by employee
#
#  Scenario: Employee registers hours twice to existing activity
#    Given it has an activity
#    When employee tries to register daily time to 5:00 for activity
#    Then 5:00 hours have been registered to the activity by employee
#    When employee tries to register daily time to 4:00 for activity
#    Then 9:00 hours have been registered to the activity by employee
#
#  Scenario: Employee registers hours too many hours to existing activity
#    Given it has an activity
#    When employee tries to register daily time to 13:00 for activity
#    Then 13:00 hours have been registered to the activity by employee
#    When employee tries to register daily time to 12:00 for activity
#    Then error message "Cannot work more than 24 hours a day" is given
#
#  Scenario: Employee registers minutes twice
#    Given it has an activity
#    When employee tries to register daily time to 4:13 for activity
#    Then 4:30 hours have been registered to the activity by employee
#    When employee tries to register daily time to 2:56 for activity
#    Then 7:30 hours have been registered to the activity by employee
#
#  Scenario: Check time of an employee that doesn't exist
#    Given it has an activity
#    Then 0:00 hours have been registered to the activity by employee
#
