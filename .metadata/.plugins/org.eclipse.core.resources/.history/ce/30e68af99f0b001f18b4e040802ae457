#Lavet af Weihao Mo og Karl Johannes Agerbo
Feature: Assign employee activity
	Description: An employee is assigned to an activity
	Actors: Employee
	
Background:
  Given the test employee reposiatory is used.
  
Scenario: Employee assigns themself and are not already assigned to the activity
	Given that there is a project with serial number 24001
	And there is an activity with the name "Scrum-Meeting", a start date week 3 and an end date week 4 in the project with the serial number 24001
	And the current Login ID is "karl"
	And employee with ID "karl" is not assigned to activity "Scrum-Meeting" in project with serial number 24001
	When the user assigns the employee with ID "karl" to the activity "Scum-Meeting" in project with serial number 24001
	Then the employee with ID "karl" is assigned to the activity "Scrum-Meeting" in project with serial number 24001
	
Scenario: Employee assigns themself and are already assigned to the activity
	Given that there is a project with serial number 24001
	And there is an activity with the name "Scrum-Meeting", a start date week 3 and an end date week 4 in the project with the serial number 24001
	And the current Login ID is "karl"
	And employee with ID "karl" is assigned to activity "Scrum-Meeting" in project with serial number 24001
	When the user assigns the employee with ID "karl" to the activity "Scum-Meeting" in project with serial number 24001
	Then the error message "Employee is already assigned to the activity" is given
	
Scenario: Project manager assigns other employee to activity
	Given that there is a project with serial number 24001
	And there is an activity with the name "Scrum-Meeting", a start date week 3 and an end date week 4 in the project with the serial number 24001
	And the current Login ID is "bjar"
	And an employee with ID "bjar" is the project manager for the project with serial number 24001
	And employee with ID "karl" is not assigned to activity "Scrum-Meeting" in project with serial number 24001
	When the user assigns the employee with ID "karl" to the activity "Scum-Meeting" in project with serial number 24001
	Then the employee with ID "karl" is assigned to the activity "Scrum-Meeting" in project with serial number 24001

Scenario: Non project manager assigns other employee to activity
	Given that there is a project with serial number 24001
	And there is an activity with the name "Scrum-Meeting", a start date week 3 and an end date week 4 in the project with the serial number 24001
	And the current Login ID is "bjar"
	And an employee with ID "bjar" is not the project manager for the project with serial number 24001
	And employee with ID "karl" is not assigned to activity "Scrum-Meeting" in project with serial number 24001
	When the user assigns the employee with ID "karl" to the activity "Scum-Meeting" in project with serial number 24001
	Then the error message "User is not project manager and can not assign other employees to activity" is given

Scenario: Employee assigns non existing employee to activity
	Given that there is a project with serial number 24001
	And there is an activity with the name "Scrum-Meeting", a start date week 3 and an end date week 4 in the project with the serial number 24001
	And the current Login ID is "bjar"
	And that there is not an employee with ID "erik"
	When the user assigns the employee with ID "erik" to the activity "Scum-Meeting" in project with serial number 24001
	Then the error message "Employee does not exist" is given

Scenario: Project manager assigns employee that is already assigned to an activity
	Given that there is a project with serial number 24001
	And there is an activity with the name "Scrum-Meeting", a start date week 3 and an end date week 4 in the project with the serial number 24001
	And the current Login ID is "karl"
	And an employee with ID "karl" is the project manager for the project with serial number 24001
	And employee with ID "bjar" is assigned to activity "Scrum-Meeting" in project with serial number 24001
	When the user assigns the employee with ID "bjar" to the activity "Scum-Meeting" in project with serial number 24001
	Then the error message "Employee is already assigned to the activity" is given
	
	Scenario: Employee assigns employee to non existing activity
	Given that there is a project with serial number 24001
	And there is not a project activity with the name "Scrum-Meeting", a start date week 3 and an end date week 4 in the project with the serial number 24001
	And the current Login ID is "karl"
	When the user assigns the employee with ID "karl" to the activity "Scrum-Meeting" in project with serial number 24001
	Then the error message "Activity does not exist" is given
