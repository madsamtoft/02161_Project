#Feature: Assign Employee
#	Description: Assign an Employee to an Activity
#	User: Employee
#
#    Scenario: Succesfully add employee to an activity
#        Given Some employee "e"
#        And Some existing project "p" with an activity "a"
#        And "e" is assigned to 0 activities
#        And "e" is not assigned to "a" in "p"
#        When "e" is assigned to "a" in "p"
#        Then "e" is succesfully assigned to "a" in "p"
#
#    Scenario: The employee is already assigned to 20 activities
#        Given Some employee "e"
#        And Some existing project "p" with an activity "a"
#        And "e" is assigned to 20 activities
#        And "e" is not assigned to "a" in "p"
#        When "e" is assigned to "a" in "p"
#        Then "e" wont be assigned to "a" in "p"
#        And error message "Too Many Activities" is given
#
#    Scenario: The employee is already assigned to the given activity
#        Given Some employee "e"
#        And Some existing project "p" with an activity "a"
#        And "e" is assigned to 0 activities
#        And "e" is already assigned to "a" in "p"
#        When "e" is assigned to "a" in "p"
#        Then "e" is succesfully assigned to "a" in "p"