#Lavet af Weihao Mo
Feature: register work hours
	Description: An employee registers work hours
	Actors: Employee
	
Background:
  Given the test employee reposiatory is used.
 
Scenario: Register work hours to existing activity
	Given the current Login ID is "wemo"
	And that there is a project with serial number 24001
	And there is an activity with the name "Scrum-Meeting", a start date week 3 and an end date week 6 in the project with the serial number 24001
  And the user has 10.0 prior registered work hours on the activity "Scrum-Meeting" in the project with serial number 24001
	When the user registers 7.0 work hours to the activity "Scrum-Meeting" in the project with serial number 24001
	Then the activity has registered 17.0 work hours on the activity "Scrum-Meeting" in the project with serial number 24001 from the user with ID "wemo"
	
Scenario: Register work hours to a non-existing activity
	Given the current Login ID is "wemo"
	And that there is a project with serial number 24001
	And there is not a project activity with the name "Scrum-Meeting", a start date week 3 and an end date week 6 in the project with the serial number 24001
	When the user registers 7.0 work hours to the activity "Scrum-Meeting" in the project with serial number 24001
	Then the error message "Activity does not exist" is given
