#Feature: Generate report
#  Description: A project leader or employee generates a report of the current activities in a project
#  User: Employee
#
#  Scenario: Report generated by project leader
#    Given a project
#    And the user is the leader of the project
#    When the user generates a report
#    Then a report is generated
#
#  Scenario: Report generated of project without a project leader
#    Given a project
#    When the user generates a report
#    Then a report is generated
#
#  Scenario: Report generated by different employee than project leader
#    Given a project with project leader "Different Employee"
#    When the user generates a report
#    Then error message "You are not the project leader of this project" is given
