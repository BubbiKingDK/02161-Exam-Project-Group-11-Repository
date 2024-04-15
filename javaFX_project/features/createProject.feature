Feature: Create a project
Description: A project is added to the system
Actors: Employee

Scenario: Create a project successfully
	Given that there is a project with serial number 24001 and a name "Project 1"
	And the project is not in the system
	When the project is added to the system
	Then the project with the serial number 24001 and a name "Project 1" is added to the system