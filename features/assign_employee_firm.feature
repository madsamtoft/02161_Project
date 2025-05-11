Feature: Assign an employee to a firm activity
	Description: Assign an Employee to a Firm Activity and Register/check time
	User: Employee
    Background:
      Given "huba" exists as employee

  Scenario: Creating a new firm activity
    When creating a new firm activity "A1"
    Then the firm activity with name "A1" exists

  Scenario: Creating a firm activity with duplicate name
    When creating a new firm activity "traktortræk"
    And creating a new firm activity "traktortræk"
    Then error message "Firm Activity Name already taken" is given

  Scenario: Trying to access non-existing firm activity
    When "huba" registers 1 hours and 30 minutes to day 2, month 2 and year 2021 to firm Activity "none"
    Then error message "Firm activity none does not exist" is given

  Scenario: Register time to firm activity
    When creating a new firm activity "a"
    When "huba" registers 1 hours and 30 minutes to day 2, month 2 and year 2021 to firm Activity "a"
    Then "huba" has registered 1 hours and 30 minutes to day 2, month 2, and year 2021 to firm Activity "a"

  Scenario: checking registered time for firm activity without work
    When creating a new firm activity "a"
    Then "huba" has registered 0 hours and 0 minutes to day 2, month 2, and year 2021 to firm Activity "a"

  Scenario: Employee registers negative minutes to existing firm activity
    When creating a new firm activity "a"
    And "huba" registers 0 hours and -1 minutes to day 2, month 2 and year 2021 to firm Activity "a"
    Then error message "Hours and minutes can't be negative" is given

  Scenario: Employee registers negative hours to existing firm activity
    When creating a new firm activity "a"
    And "huba" registers -1 hours and 0 minutes to day 2, month 2 and year 2021 to firm Activity "a"
    Then error message "Hours and minutes can't be negative" is given
