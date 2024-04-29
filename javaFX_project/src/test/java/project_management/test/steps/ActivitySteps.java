package project_management.test.steps;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dtu.project.management.app.OperationNotAllowedException;
import dtu.project.management.app.ProjectManagementApp;
import dtu.project.management.domain.Activity;
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

	@Given("there is an activity with the name {string}, a start date week {int} and an end date week {int}")
	public void thereIsAnActivityWithTheNameAStartDateWeekAndAnEndDateWeek(String name, int startWeek , int endWeek) {
		projectManagementApp.setup();
	    projectManagementApp.createActivity(name, startWeek, endWeek);
	}

	@When("the activity with name {string} is added to the project with serial number {int}")
	public void theActivityWithNameIsAddedToTheProjectWithSerialNumber(String name, int serialNumber) throws OperationNotAllowedException {
		try {
			projectManagementApp.addActivity(projectManagementApp.getProject(serialNumber));
		} catch (OperationNotAllowedException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	    
	}
	
	@Then("the activity with the name {string}, a start date week {int} and an end date week {int} is added to the project with serial number {int}")
	public void the_activity_with_the_name_a_start_date_week_and_an_end_date_week_is_added_to_the_project_with_serial_number(String name, int startWeek, int endWeek, int serialNumber) {
	    Project project = projectManagementApp.getProject(serialNumber);
	    Activity activity = projectManagementApp.getActivity(name, project);
	    
	    assertTrue(project.getActivities().contains(activity));
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
			projectManagementApp.addActivity(projectManagementApp.getActivity(),projectManagementApp.getEmployee(ID));
		} catch (OperationNotAllowedException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("the activity with the name {string}, a start date week {int} and an end date week {int} is added to the user with ID {string}")
	public void theActivityWithTheNameAStartDateWeekAndAnEndDateWeekIsAddedToTheUserWithID(String activityName, int startWeek, int endWeek, String ID) {
	    Activity activity = projectManagementApp.getActivity(activityName, projectManagementApp.getEmployee(ID));
	    
	    assertTrue(projectManagementApp.getEmployee(ID).getActivities().contains(activity));
		assertEquals(activity.getName(), activityName);
		assertEquals(activity.getStartWeek(), startWeek);
		assertEquals(activity.getEndWeek(), endWeek);
	}
	
	@Given("the activity with the name {string} is already assigned to the user with ID {string}")
	public void theActivityWithTheNameIsAlreadyAssignedToTheUserWithID(String activityName, String ID) throws OperationNotAllowedException {
		theActivityWithNameIsAddedToTheUserWithID(activityName, ID);
	}
}
