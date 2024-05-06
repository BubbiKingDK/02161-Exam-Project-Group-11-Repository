#@author Karl Johannes Agerbo
Feature: Create a project
Description: A project is added to the system
Actors: Employee

Background:
  Given the test employee reposiatory is used.

Scenario: Create first project successfully 
	Given the year is the current year
	And that there is a project with the name "Project 1"
	When the project is added to the system
	Then the project with the serial number 24001 and a name "Project 1" is added to the system

Scenario: Create project with correct serial number
  Given the year is the current year
  And that there is a project with the name "Important project" and serial number 24001 in the system
  And that there is a project with the name "Meeting"
  When the project is added to the system
  Then the project with the serial number 24002 and a name "Meeting" is added to the system	