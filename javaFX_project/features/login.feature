Feature: Login
	Description: The employee logs into the system
	Actors: Employee

Scenario: ID is registered
	Given that the user is not logged in
	And that there is an employee with ID "karl"
	When the user logs in with the ID "karl"
	Then the employee is logged in with the ID "karl"
