Feature: Register used time
Description: A user registers work hours
Actors: Employee

Background:
  Given the test employee reposiatory is used.

Scenario: Register work hours to existing activity
	Given the current Login ID is "karl"
	And that there is a project with serial number 24001
	And there is an activity with the name "Scrum-Meeting", a start date week 3 and an end date week 4 in the project with the serial number 24001
	And there is an activity with the name "Scrum-Meeting2", a start date week 5 and an end date week 6 in the project with the serial number 24001
	And employee with ID "karl" is assigned to activity "Scrum-Meeting" in project with serial number 24001
	And the user has 3 prior registered work hours on the activity "Scrum-Meeting" in the project with serial number 24001
	When the user registers 8 work hours to the activity "Scrum-Meeting" in the project with serial number 24001
	Then the activity has registered 11 work hours on the activity "Scrum-Meeting" in the project with serial number 24001 from the user with ID "karl"
	
Scenario: Register work hours to a non-existing activity
	Given the current Login ID is "karl"
	And that there is a project with serial number 24001
	And there is not an activity with the name "Scrum-Meeting", a start date week 3 and an end date week 4 in the project with the serial number 24001
	When the user registers 8 work hours to the activity "Scrum-Meeting" in the project with serial number 24001
	Then the error message "Activity does not exist" is given
	
Scenario: Register work hours to an activity that employee is not assigned
	Given the current Login ID is "karl"
	And that there is a project with serial number 24001
	And there is an activity with the name "Scrum-Meeting", a start date week 3 and an end date week 4 in the project with the serial number 24001
	When the user registers 8 work hours to the activity "Scrum-Meeting" in the project with serial number 24001
	Then the error message "User not assigned to activity" is given