Feature: Register used time
Description: A user registers work hours
Actors: Employee

Scenario: Register work hours to existing activity
	Given the current Login ID is "karl"
	And there is an activity with the name "Scrum-Meeting", a start date week 3 and an end date week 4 in the project with the serial number 24001
	And the user has 3 prior registered work hours on the activity "Scrum-Meeting"
	When the user registers 8 work hours to the activity "Scrum-Meeting"
	Then the activity has registered 11 work hours on the activity "Scrum-Meeting" from the user with ID "karl"
	
#Scenario: Register work hours to a non-existing activity
#	Given an employee with ID "karl" is registered with the system
#	And there is not an activity with the name "Scrum-Meeting" in the project 24001
#	When the user registers 8 work hours to the activity "Scrum-Meeting"
#	Then the error message "Activity does not exist" is given