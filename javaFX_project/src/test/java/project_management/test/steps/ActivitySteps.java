package project_management.test.steps;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dtu.project.management.app.OperationNotAllowedException;
import dtu.project.management.app.ProjectManagementApp;
import dtu.project.management.domain.ProjectActivity;
import dtu.project.management.domain.PersonalActivity;
import dtu.project.management.domain.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import project_management.test.steps.helper.ErrorMessageHolder;

public class ActivitySteps {
	private ProjectManagementApp projectManagementApp;
	private ErrorMessageHolder errorMessage;
	
	public ActivitySteps(ProjectManagementApp projectManagementApp, ErrorMessageHolder errorMessage) {
		this.projectManagementApp = projectManagementApp;
		this.errorMessage = errorMessage;
	}
	
	@Given("there is a project activity with the name {string}, a start date week {int} and an end date week {int}")
	public void thereIsAnActivityWithTheNameAStartDateWeekAndAnEndDateWeek(String name, int startWeek , int endWeek) {
	    projectManagementApp.createProjectActivity(name, startWeek, endWeek);
	}
	
	@Given("there is a personal activity with the name {string}, a start date week {int} and an end date week {int}")
	public void thereIsAPersonalActivityWithTheNameAStartDateWeekAndAnEndDateWeek(String name, int startWeek , int endWeek) {
		projectManagementApp.createPersonalActivity(name, startWeek, endWeek);
	}
	
	@Given("there is an activity with the name {string}, a start date week {int} and an end date week {int} in the project with the serial number {int}")
	public void thereIsAnActivityWithTheNameAStartDateWeekAndAnEndDateWeekInTheProjectWithTheSerialNumber(String activityName, int startWeek, int endWeek, int serialnumber) throws OperationNotAllowedException {
		projectManagementApp.createProjectActivity(activityName, startWeek, endWeek);
	    projectManagementApp.addProjectActivity(projectManagementApp.getProject(serialnumber));
	}
	
	@When("the activity with name {string} is added to the project with serial number {int}")
	public void theActivityWithNameIsAddedToTheProjectWithSerialNumber(String name, int serialNumber) throws OperationNotAllowedException {
		try {
			projectManagementApp.addProjectActivity(projectManagementApp.getProject(serialNumber));
		} catch (OperationNotAllowedException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	    
	}
	
	@Then("the activity with the name {string}, a start date week {int} and an end date week {int} is added to the project with serial number {int}")
	public void the_activity_with_the_name_a_start_date_week_and_an_end_date_week_is_added_to_the_project_with_serial_number(String name, int startWeek, int endWeek, int serialNumber) throws OperationNotAllowedException {
	    Project project = projectManagementApp.getProject(serialNumber);
	    ProjectActivity activity = projectManagementApp.getProjectActivity(name, project);
	    
	    assertTrue(projectManagementApp.getProjectActivities(project).contains(activity));
		assertEquals(activity.getName(), name);
		assertEquals(activity.getStartWeek(), startWeek);
		assertEquals(activity.getEndWeek(), endWeek);
	}

	
	@Given("there is not a project with serial number {int}")
	public void thereIsNotAProjectWithSerialNumber(int serialNumber) {
		assertTrue(projectManagementApp.getProject(serialNumber) == null);
	}

	
	@Given("the activity with the name {string} is already in the project with serial number {int}")
	public void theActivityWithTheNameIsAlreadyInTheProjectWithSerialNumber(String name, int serialNumber) throws OperationNotAllowedException {
		theActivityWithNameIsAddedToTheProjectWithSerialNumber(name, serialNumber);
	}
	
	@When("the activity with name {string} is added to the user with ID {string}")
	public void theActivityWithNameIsAddedToTheUserWithID(String activityName, String ID) throws OperationNotAllowedException {
		try {
			projectManagementApp.addPersonalActivity(projectManagementApp.getEmployee(ID));
		} catch (OperationNotAllowedException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}
	
	@Given("there is not already an activity with the name {string} added to the user")
	public void thereIsNotAlreadyAnActivityWithTheNameAddedToTheUser(String activityName) {
	    assertEquals(projectManagementApp.getPersonalActivity(activityName, projectManagementApp.getCurrentLogin()), null);
	}

	@Then("the activity with the name {string}, a start date week {int} and an end date week {int} is added to the user with ID {string}")
	public void theActivityWithTheNameAStartDateWeekAndAnEndDateWeekIsAddedToTheUserWithID(String activityName, int startWeek, int endWeek, String ID) {
	    PersonalActivity activity = projectManagementApp.getPersonalActivity(activityName, projectManagementApp.getEmployee(ID));
	    
	    System.out.println(activity);
	    assertTrue(projectManagementApp.getPersonalActivities().contains(activity));
		assertEquals(activity.getName(), activityName);
		assertEquals(activity.getStartWeek(), startWeek); 
		assertEquals(activity.getEndWeek(), endWeek);
	}
	
	@Given("the activity with the name {string} is already assigned to the user with ID {string}")
	public void theActivityWithTheNameIsAlreadyAssignedToTheUserWithID(String activityName, String ID) throws OperationNotAllowedException {
		theActivityWithNameIsAddedToTheUserWithID(activityName, ID);
	}
	
	@Given("there is not a project activity with the name {string}, a start date week {int} and an end date week {int} in the project with the serial number {int}")
	public void thereIsNotAnActivityWithTheNameAStartDateWeekAndAnEndDateWeekInTheProjectWithTheSerialNumber(String activityName, int startWeek, int endWeek, int serialNumber) throws OperationNotAllowedException {
		assertEquals(projectManagementApp.getProjectActivity(activityName, projectManagementApp.getProject(serialNumber)), null);
	}
	
	@When("the user gets the list of employees for the activity with the name {string} in the project with serial number {int}")
	public void theUserGetsTheListOfEmployeesForTheActivityWithTheNameInTheProjectWithSerialNumber(String activityName, int serialNumber) {
		try {
			projectManagementApp.getEmployeesInActivity(projectManagementApp.getProjectActivity(activityName, projectManagementApp.getProject(serialNumber)));
		} catch (OperationNotAllowedException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}
	
	@When("the user gets the list of activities for the project with serial number {int}")
	public void theUserGetsTheListOfActivitiesForTheProjectWithSerialNumber(int serialNumber) {
	    try {
			projectManagementApp.getProjectActivities(projectManagementApp.getProject(serialNumber));
		} catch (OperationNotAllowedException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}
	
	@Given("there is already an activity with the name {string}, a start date week {int} and an end date week {int} added to the user with ID {string}")
	public void thereIsAnAlreadyAnActivityWithTheNameAStartDateWeekAndAnEndDateWeekAddedToTheUserWithID(String activityName, int startWeek, int endWeek, String ID) throws OperationNotAllowedException {
		projectManagementApp.createPersonalActivity(activityName, startWeek, endWeek);
	    projectManagementApp.addPersonalActivity(projectManagementApp.getEmployee(ID));
	}
}
