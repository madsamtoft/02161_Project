Feature: Assign an employee to a firm activity
	Description: Assign an Employee to a Firm Activity
	User: Employee

    Scenario: Succesfully add employee to a firm activity
        Given Some employee "e"
        And Some existing firm activity "a"
        And "e" is not assigned to "a"
        When "e" is assigned to "a"
        Then "e" is succesfully assigned to "a"
        
    Scenario: The employee is already assigned to the given firm activity
        Given Some employee "e"
        And Some existing firm activity "a"
        And "e" is already assigned to "a"
        When "e" is assigned to "a"
        Then "e" wont be assigned to "a"
        And Exception "AlreadyAssignedException" is thrown