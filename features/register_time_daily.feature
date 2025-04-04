#Feature: Register time Daily
#  Description: Employee registers time used for the day
#  User: Employee
#
#  Scenario: Employee registers 5 hours to existing activity
#    Given a project
#    And it has an activity
#    When the user tries to register daily time to 5 for activity
#    Then 5 hours has been registered to the activity
#
#  Scenario: Employee registers 5 hours to non-existing activity
#    Given a project
#    When the user tries to register daily time to 5 for activity
#    Then error message "No Activity To Registers Hours To" is given