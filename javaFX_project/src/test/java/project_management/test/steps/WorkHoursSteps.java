package project_management.test.steps;

import dtu.project.management.app.OperationNotAllowedException;
import dtu.project.management.app.ProjectManagementApp;
import dtu.project.management.domain.Employee;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import project_management.test.steps.helper.ErrorMessageHolder;

public class WorkHoursSteps {
	private ProjectManagementApp projectManagementApp;
	private ErrorMessageHolder errorMessage;
	
	public WorkHoursSteps(ProjectManagementApp projectManagementApp, ErrorMessageHolder errorMessage) {
		this.projectManagementApp = projectManagementApp;
		this.errorMessage = errorMessage;
	}
	
	@When("the user assigns the activity with the name {string}, in the project with the serial number {int}, {int} work hours")
	public void theUserAssignsTheActivityWithTheNameInTheProjectWithTheSerialNumberWorkHours(String activityName, int serialNumber, int expectedWorkHours) {
	    try {
			projectManagementApp.assignExpectedWorkHours(expectedWorkHours, projectManagementApp.getActivity(activityName, projectManagementApp.getProject(serialNumber)));
		} catch (OperationNotAllowedException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("the activity with the name {string}, in the project with the serial number {int}, is assigned {int} work hours")
	public void theActivityWithTheNameInTheProjectWithTheSerialNumberIsAssignedWorkHours(String activityName, int serialNumber, int expectedWorkHours) throws OperationNotAllowedException {
	    assertEquals(projectManagementApp.getActivity(activityName, projectManagementApp.getProject(serialNumber)).getExpectedWorkHours(), expectedWorkHours);
	}

	@Given("the user has {int} prior registered work hours on the activity {string} in the project with serial number {int}")
	public void theUserHasPriorRegisteredWorkHoursOnTheActivityInTheProjectWithSerialNumber(int workHours, String activityName, int serialNumber) throws OperationNotAllowedException {
	    try {
	    	projectManagementApp.registerWorkHours(projectManagementApp.getActivity(activityName, projectManagementApp.getProject(serialNumber)),workHours);
		} catch (OperationNotAllowedException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@When("the user registers {int} work hours to the activity {string} in the project with serial number {int}")
	public void theUserRegistersWorkHoursToTheActivityInTheProjectWithSerialNumber(int workHours, String activityName, int serialNumber) throws OperationNotAllowedException {
		theUserHasPriorRegisteredWorkHoursOnTheActivityInTheProjectWithSerialNumber(workHours, activityName, serialNumber);
	}

	@Then("the activity has registered {int} work hours on the activity {string} in the project with serial number {int} from the user with ID {string}")
	public void theActivityHasRegisteredWorkHoursOnTheActivityInTheProjectWithSerialNumberFromTheUserWithID(int workHours, String activityName, int serialNumber, String ID) throws OperationNotAllowedException {
		assertEquals(workHours, projectManagementApp.getEmployeeWorkHours(projectManagementApp.getActivity(activityName, projectManagementApp.getProject(serialNumber)), projectManagementApp.getEmployee(ID)));
	}
}
	