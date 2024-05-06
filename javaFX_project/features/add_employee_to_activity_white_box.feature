#@author Bjarke Søderhamn Petersen
Feature: Add employee to activity white box test
Actors: Employee

Background:
  Given the test employee reposiatory is used.

Scenario: Employee is logged in and adds themselves to the activity	
	Given that there is a project with the name "projectName" and serial number 24001 in the system
	And there is an activity with the name "activityName", a start date week 1 and an end date week 2 in the project with the serial number 24001
	And the current Login ID is "karl"
	And employee with ID "karl" is not assigned to activity "activityName" in project with serial number 24001
	When the user assigns the employee with ID "karl" to the activity "activityName" in project with serial number 24001
	Then the employee with ID "karl" is assigned to the activity "activityName" in project with serial number 24001
	
Scenario: Project manager assigns other employee to activity
	Given that there is a project with the name "projectName" and serial number 24001 in the system
	And there is an activity with the name "activityName", a start date week 1 and an end date week 2 in the project with the serial number 24001
	And the current Login ID is "bjar"
	And an employee with ID "bjar" is the project manager for the project with serial number 24001
	And employee with ID "karl" is not assigned to activity "activityName" in project with serial number 24001
	When the user assigns the employee with ID "karl" to the activity "activityName" in project with serial number 24001
	Then the employee with ID "karl" is assigned to the activity "activityName" in project with serial number 24001

Scenario: Employee assigns themself and are already assigned to the activity
	Given that there is a project with the name "projectName" and serial number 24001 in the system
	And there is an activity with the name "activityName", a start date week 1 and an end date week 2 in the project with the serial number 24001
	And the current Login ID is "karl"
	And employee with ID "karl" is assigned to activity "activityName" in project with serial number 24001
	When the user assigns the employee with ID "karl" to the activity "activityName" in project with serial number 24001
	Then the error message "Employee is already assigned to the activity" is given

Scenario: Non project manager assigns other employee to activity
	Given that there is a project with the name "projectName" and serial number 24001 in the system
	And there is an activity with the name "activityName", a start date week 1 and an end date week 2 in the project with the serial number 24001
	And the current Login ID is "bjar"
	And an employee with ID "bjar" is not the project manager for the project with serial number 24001
	When the user assigns the employee with ID "karl" to the activity "activityName" in project with serial number 24001
	Then the error message "User is not project manager and can not assign other employees to activity" is given
	
Scenario: User assigns non existing employee to activity // Change 
	Given that there is a project with the name "projectName" and serial number 24001 in the system
	And there is an activity with the name "activityName", a start date week 1 and an end date week 2 in the project with the serial number 24001
	And that there is not an employee with ID "erik"
	When the user assigns the employee with ID "erik" to the activity "activityName" in project with serial number 24001
	Then the error message "Employee does not exist" is given
	
	Scenario: Employee assigns employee to non existing activity
	Given that there is a project with the name "projectName" and serial number 24001 in the system
	And there is not a project activity with the name "activityName", a start date week 1 and an end date week 2 in the project with the serial number 24001
	And the current Login ID is "karl"
	When the user assigns the employee with ID "karl" to the activity "activityName" in project with serial number 24001
	Then the error message "Activity does not exist" is given