#@author Benjamin Benyo Endahl Hansen
Feature: Create an activity
	Description: The user creates an personal activity
	Actors: Employee
	
Background:
  Given the test employee reposiatory is used.
	
	
#A)
Scenario: User creates a personal activity that is not already created
	Given the current Login ID is "karl"
	And there is already an activity with the name "Holliday2", a start date week 4 and an end date week 9 added to the user with ID "karl"
	And there is a personal activity with the name "Holliday", a start date week 3 and an end date week 4
	And there is not already an activity with the name "Holliday" added to the user
	When the activity with name "Holliday" is added to the user with ID "karl"
	Then the activity with the name "Holliday", a start date week 3 and an end date week 4 is added to the user with ID "karl"
	
#B)
Scenario: User creates a personal activity that has a too low start week number
	Given there is a personal activity with the name "Holliday", a start date week -1 and an end date week 8
	When the activity with name "Holliday" is added to the user with ID "karl"
	Then the error message "Invalid week number" is given
	
#C)
Scenario: User adding a personal activity that is already created
	Given there is a personal activity with the name "Holliday", a start date week 5 and an end date week 8
	And the activity with the name "Holliday" is already assigned to the user with ID "karl"
	When the activity with name "Holliday" is added to the user with ID "karl"
	Then the error message "Activity already exists" is given
	