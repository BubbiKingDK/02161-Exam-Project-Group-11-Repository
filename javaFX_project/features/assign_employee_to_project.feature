Feature: Assign employee to project
	Description: An employee is assigned to a project
	Actors: Employee

Scenario: Assign employee to existing project
	Given that there is a project with serial number 24001
	And an employee with ID "karl" is logged in
	When the user with ID "karl" assigns themselves to the project with serial number 24001
	Then the employee with ID "karl" is assigned to the project with serial number 24001

Scenario: Assign employee to non-existing project
	Given that there is not a project with serial number 24001
	And an employee with ID "karl" is logged in
	When the user with ID "karl" assigns themselves to the project with serial number 24001
	Then the error message "Project does not exist" is given
