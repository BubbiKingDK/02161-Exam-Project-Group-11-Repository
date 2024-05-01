Feature: Assign employee activity
	Description: An employee is assigned to an activity
	Actors: Employee

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
	
#Scenario: Project manager assigns employee to activity
	#Given there is a project with serial number 24001
	#And there is an activity with the name "Scrum-Meeting" in the project 24001
	#And the current Login ID is "karl"
	#And user with ID "karl" is project manager to project with serial number 24001
	#And employee with ID "bjar" is assigned to the project with serial number 24001
	#And employee with ID "bjar" is not assigned to activity "Scrum-Meeting"
	#When the user assigns the employee with ID "bjar" to the activity "Scum-Meeting"
	#Then the employee with ID "bjar" is assigned to the activity "Scrum-Meeting"
	
#Scenario: Project manager assigns employee that is already assigned to an activity
	#Given there is a project with serial number 24001
	#And there is an activity with the name "Scrum-Meeting" in the project 24001
	#And the current Login ID is "karl"
	#And user with ID "karl" is project manager to project with serial number 24001
	#And employee with ID "bjar" is assigned to the project with serial number 24001
	#And employee with ID "bjar" is assigned to activity "Scrum-Meeting"
	#When the user assigns the employee with ID "bjar" to the activity "Scum-Meeting"
	#Then the error message "Employee is already assigned to the activity" is given
	
#Scenario: Project manager assigns employee that is not assigned to project
#	#Given there is a project with serial number 24001
#	#And there is an activity with the name "Scrum-Meeting" in the project 24001
#	And the current Login ID is "karl"
#	And user with ID "karl" is project manager to project with serial number 24001
#	And employee with ID "bjar" is not assigned to the project with serial number 24001
#	And employee with ID "bjar" is not assigned to activity "Scrum-Meeting"
#	When the user assigns the employee with ID "bjar" to the activity "Scum-Meeting"
#	Then the error message "Employee is not assigned to the project" is given
