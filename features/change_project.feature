#Feature: Change Project
#  Description: A project leader or employee makes changes to a project
#  User: Employee
#
#  Scenario: Start- and end date changed for a project
#    Given a project
#    And the user is the leader of the project
#    When the start date is set to "1.1.2021"
#    And the end date is set to "1.5.2021"
#    Then the start date is "1.1.2021"
#    And the end date is "1.5.2021"
#
#  Scenario: Start- and end date changed by non-project leader for a project
#    Given a project
#    And the user is the not the leader of the project
#    When the start date is set to "1.1.2021"
#    And the end date is set to "1.5.2021"
#    Then error message "Not Project Leader" is given
#
#  Scenario: set new name for a project as project leader
#    Given a project
#    And the user is the leader of the project
#    When setting project name to "new"
#    Then project name is "new"
#
#  Scenario: set new name for a project as non-project leader
#    Given a project
#    And the user is not the leader of the project
#    When setting project name to "new"
#    Then error message "Not Project Leader" is given
#
#  Scenario: set new customer for a project as project leader
#    Given a project
#    And the user is the leader of the project
#    When setting project customer to "good customer"
#    Then project name is "good customer"
#
#  Scenario: set new customer for a project as non-project leader
#    Given a project
#    And the user is not the leader of the project
#    When setting project customer to "good customer"
#    Then error message "Not Project Leader" is given
#
#  Scenario: set new project leader for a project
#    Given a project
#    Given "per" exists as employee
#    When setting leader as "per"
#    Then leader is "per"
#
#  Scenario: set a non existing employee as project leader
#    Given a project
#    Given "John" does not exist as employee
#    When setting leader as "John"
#    Then error message "Not A Valid Employee" is given