Feature: Create an activity
	Description: The user creates an activity
	Actors: Employee
	
Scenario: User creates an activity that is not already created
	Given that there is a project with serial number 24001
	And there is an activity with the name "Scrum-Meeting", a start date week 3 and an end date week 4
	When the activity with name "Scrum-Meeting" is added to the project with serial number 24001
	Then the activity with the name "Scrum-Meeting", a start date week 3 and an end date week 4 is added to the project with serial number 24001

Scenario: Project does not exist
	Given there is not a project with serial number 24001
	When the activity with name "Scrum-Meeting" is added to the project with serial number 24001
	Then the error message "Project does not exist" is given

#Scenario: Trying to add and activity that is already created
#	Given there is a project with serial number 24001
#	And there is an activity with the name "Scrum-Meeting", a start date week 3 and an end date week 4
#	And the activity with the name "Scrum-Meeting" is already in the project with serial number 24001
#	When the activity the name "Scrum-Meeting" is added to the project with serial number 24001
#	Then the error message "Activity already exists" is given
#	
#Scenario: User creates a personal activity that is not already created
#	Given there is an activity with the name "Holliday"
#	When the activity with name "Holliday" is added to the user with ID "karl"
#	Then the activity with the name "Holliday" is added to the user with ID "karl"
#	
#Scenario: User adding a personal activity that is already created
#	Given there is an activity with the name "Holliday"
#	And the activity is already within the repository of the user with ID "karl"
#	When the activity with the name "Holliday" is added to the user
#	Then the error message "Activity already exists" is given