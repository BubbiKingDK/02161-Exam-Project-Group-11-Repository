Feature: Assign project manager
	Description: The employee registers a project manager of the project
	Actors: employee

Scenario: Register a project manager
	Given that there is a project with serial number 24001
	When the user assigns the project manager with ID "karl" to the project with serial number 24001
	Then the employee with ID "karl" is registered project manager of the project with serial number 24001

Scenario: Can not register a project manager with a project that does not exist
	Given that there is not a project with serial number 24001
	When the user assigns the project manager with ID "karl" to the project with serial number 24001
	Then the error message "Project does not exist" is given

Scenario: An employee assigns a new project manager to a project with an existing project manager 
	Given that there is a project with serial number 24001
	And an employee with ID "bjar" is the project manager for the project with serial number 24001
	When the user assigns the project manager with ID "karl" to the project with serial number 24001
	Then the employee with ID "karl" is registered project manager of the project with serial number 24001
	And the employee with ID "bjar" is no longer project manager of the project with serial number 24001. 
