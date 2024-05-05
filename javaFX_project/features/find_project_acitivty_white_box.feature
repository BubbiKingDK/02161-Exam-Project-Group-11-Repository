#Lavet af Karl Johannes Agerbo
Feature: Find project activity
Description: White box
Actors: Employee

Background:
  Given the test employee reposiatory is used.

Scenario: User find project activity from existing project and exisiting activity name
	Given that there is a project with the name "project" and serial number 24001 in the system
	And there is an activity with the name "activity1", a start date week 1 and an end date week 2 in the project with the serial number 24001
	And there is an activity with the name "activity2", a start date week 2 and an end date week 3 in the project with the serial number 24001	
	When the user tries to find the activity with name "activity1" in the project with serial number 24001
	Then the activity with name "activity1", a start date week 1 and an end date week 2 in the project with serial number 24001 is found
	
Scenario: User find project activity from existing project and non-exisiting activity name
	Given that there is a project with the name "project" and serial number 24001 in the system
	And there is an activity with the name "activity2", a start date week 1 and an end date week 2 in the project with the serial number 24001
	And there is an activity with the name "activity3", a start date week 2 and an end date week 3 in the project with the serial number 24001	
	When the user tries to find the activity with name "activity1" in the project with serial number 24001
	Then the activity with name "activity1" in the project with serial number 24001 is not found
	
Scenario: User find project activity from non-existing project
	Given that there is not a project with serial number 24001	
	When the user tries to find the activity with name "activity1" in the project with serial number 24001
	Then the error message "Project does not exist" is given
