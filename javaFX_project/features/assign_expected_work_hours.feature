#Lavet af Bjarke Søderhamn Petersen
Feature: Assign expected work hours
	Description: A user assign expected work hours to activity
	Actors: Employee
	
Background:
  Given the test employee reposiatory is used.

Scenario: Assign work hours to existing activity
	Given that there is a project with serial number 24001
	And there is an activity with the name "Scrum-Meeting", a start date week 3 and an end date week 4 in the project with the serial number 24001
	When the user assigns the activity with the name "Scrum-Meeting", in the project with the serial number 24001, 100.5 work hours
	Then the activity with the name "Scrum-Meeting", in the project with the serial number 24001, is assigned 100.5 work hours

Scenario: Assign work hours to a non-existing activity
	Given that there is a project with serial number 24001
	And there is not a project activity with the name "Scrum-Meeting", a start date week 3 and an end date week 4 in the project with the serial number 24001
	When the user assigns the activity with the name "Scrum-Meeting", in the project with the serial number 24001, 100.5 work hours
	Then the error message "Activity does not exist" is given

Scenario: Assign work hours to a non-existing project
	Given that there is not a project with serial number 24001
	When the user assigns the activity with the name "Scrum-Meeting", in the project with the serial number 24001, 100.5 work hours
	Then the error message "Project does not exist" is given

