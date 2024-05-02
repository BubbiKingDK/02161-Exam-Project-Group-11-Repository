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

Scenario: Trying to add and activity that is already created
	Given that there is a project with serial number 24001
	And there is an activity with the name "Scrum-Meeting", a start date week 3 and an end date week 4
	And the activity with the name "Scrum-Meeting" is already in the project with serial number 24001
	When the activity with name "Scrum-Meeting" is added to the project with serial number 24001
	Then the error message "Activity already exists" is given

Scenario: User creates a personal activity that is not already created
	Given the current Login ID is "karl"
	And there is an activity with the name "Holliday", a start date week 5 and an end date week 8
	And there is not already an activity with the name "Holliday" added to the user
	When the activity with name "Holliday" is added to the user with ID "karl"
	Then the activity with the name "Holliday", a start date week 5 and an end date week 8 is added to the user with ID "karl"
	
Scenario: User adding a personal activity that is already created
	Given there is an activity with the name "Holliday", a start date week 5 and an end date week 8
	And the activity with the name "Holliday" is already assigned to the user with ID "karl"
	When the activity with name "Holliday" is added to the user with ID "karl"
	Then the error message "Activity already exists" is given
	
Scenario: User attempts to see activities for project that does not exist
	Given there is not a project with serial number 24001
	When the user gets the list of activities for the project with serial number 24001
	Then the error message "Project does not exist" is given
	
Scenario: User attempts to see employees for activity that does not exist
	Given that there is a project with serial number 24001
	And there is not an activity with the name "scrum", a start date week 3 and an end date week 4 in the project with the serial number 24001
	When the user gets the list of employees for the activity with the name "scrum" in the project with serial number 24001
	Then the error message "Activity does not exist" is given