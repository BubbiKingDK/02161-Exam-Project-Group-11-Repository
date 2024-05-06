#Lavet af Weihao Mo
Feature: Login
	Description: The employee logs into the system
	Actors: Employee
	
Background:
  Given the test employee reposiatory is used.

Scenario: ID is registered
	Given that the user is not logged in
	And that there is an employee with ID "karl"
	When the user logs in with the ID "karl"
	Then the employee is logged in with the ID "karl"

Scenario: Id is not registered
	Given that the user is not logged in
	And that there is not an employee with ID "test"
	When the user logs in with the ID "test"
	Then the error message "Employee is not registered" is given