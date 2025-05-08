Feature: Assign an employee to a firm activity
	Description: Assign an Employee to a Firm Activity
	User: Employee
    Background:
      Given "huba" exists as employee
      #And there exists a firm activity


  # create firm activity

  # register time to firm activity

  # change registered time to firm activity

  # get time on firm activity?

  Scenario: Creating a new firm activity
    When creating a new firm activity "A1"
    Then the firm activity with name "A1" exists

  Scenario: Creating a firm activity with duplicate name
    When creating a new firm activity "traktortræk"
    And creating a new firm activity "traktortræk"
    Then error message "Firm Activity Name already taken" is given

  Scenario: Register time to firm activity
    When creating a new firm activity "a"
    When "huba" registers 1 hours and 30 minutes to day 2, month 2 and year 2021
    Then "huba" has registered 1 hours and 30 minutes to day 2, month 2, and year 2021

  Scenario: checking registered time for firm activity without work
    When creating a new firm activity "a"
    Then "huba" has registered 0 hours and 0 minutes to day 2, month 2, and year 2021



#    Scenario: Succesfully add employee to a firm activity
#        Given Some employee "e"
#        And Some existing firm activity "a"
#        And "e" is not assigned to "a"
#        When "e" is assigned to "a"
#        Then "e" is succesfully assigned to "a"
#
#    Scenario: The employee is already assigned to the given firm activity
#        Given Some employee "e"
#        And Some existing firm activity "a"
#        And "e" is already assigned to "a"
#        When "e" is assigned to "a"
#        Then "e" is succesfully assigned to "a"