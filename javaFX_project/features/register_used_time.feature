#@author Benjamin Benyo Endahl Hansen
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
	And the user has 3.0 prior registered work hours on the activity "Scrum-Meeting" in the project with serial number 24001
	When the user registers 8.0 work hours to the activity "Scrum-Meeting" in the project with serial number 24001
	Then the activity has registered 11.0 work hours on the activity "Scrum-Meeting" in the project with serial number 24001 from the user with ID "karl"
	
Scenario: Register work hours to a non-existing activity
	Given the current Login ID is "karl"
	And that there is a project with serial number 24001
	And there is not a project activity with the name "Scrum-Meeting", a start date week 3 and an end date week 4 in the project with the serial number 24001
	When the user registers 8.0 work hours to the activity "Scrum-Meeting" in the project with serial number 24001
	Then the error message "Activity does not exist" is given
	
Scenario: Correct combined work hours
	Given that there is a project with serial number 24001
	And there is an activity with the name "Scrum-Meeting", a start date week 3 and an end date week 4 in the project with the serial number 24001
	And employee with ID "karl" is assigned to activity "Scrum-Meeting" in project with serial number 24001
	And employee with ID "bjar" is assigned to activity "Scrum-Meeting" in project with serial number 24001
	When the employee with ID "karl" registers 8.0 work hours to the activity "Scrum-Meeting" in the project with serial number 24001
	And the employee with ID "bjar" registers 6.0 work hours to the activity "Scrum-Meeting" in the project with serial number 24001
	And the employee with ID "beha" registers 2.0 work hours to the activity "Scrum-Meeting" in the project with serial number 24001
	Then the total work hours of the activity with the name "Scrum-Meeting", a start date week 3 and an end date week 4 in the project with the serial number 24001, is 16.0
