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
import java.util.HashMap;
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
	
	@When("the user assigns the activity with the name {string}, in the project with the serial number {int}, {double} work hours")
	public void theUserAssignsTheActivityWithTheNameInTheProjectWithTheSerialNumberWorkHours(String activityName, int serialNumber, double expectedWorkHours) {
	    try {
			projectManagementApp.assignExpectedWorkHours(expectedWorkHours, projectManagementApp.findProjectActivity(activityName, projectManagementApp.findProject(serialNumber)));
		} catch (OperationNotAllowedException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("the activity with the name {string}, in the project with the serial number {int}, is assigned {double} work hours")
	public void theActivityWithTheNameInTheProjectWithTheSerialNumberIsAssignedWorkHours(String activityName, int serialNumber, double expectedWorkHours) throws OperationNotAllowedException {
	    assertEquals(projectManagementApp.findProjectActivity(activityName, projectManagementApp.findProject(serialNumber)).getExpectedWorkHours(), expectedWorkHours, 0.1);
	}

	@Given("the user has {double} prior registered work hours on the activity {string} in the project with serial number {int}")
	public void theUserHasPriorRegisteredWorkHoursOnTheActivityInTheProjectWithSerialNumber(double workHours, String activityName, int serialNumber) throws OperationNotAllowedException {
	    try {
	    	projectManagementApp.registerWorkHours(projectManagementApp.findProjectActivity(activityName, projectManagementApp.findProject(serialNumber)),workHours);
		} catch (OperationNotAllowedException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@When("the user registers {double} work hours to the activity {string} in the project with serial number {int}")
	public void theUserRegistersWorkHoursToTheActivityInTheProjectWithSerialNumber(double workHours, String activityName, int serialNumber) throws OperationNotAllowedException {
		theUserHasPriorRegisteredWorkHoursOnTheActivityInTheProjectWithSerialNumber(workHours, activityName, serialNumber);
	}

	@Then("the activity has registered {double} work hours on the activity {string} in the project with serial number {int} from the user with ID {string}")
	public void theActivityHasRegisteredWorkHoursOnTheActivityInTheProjectWithSerialNumberFromTheUserWithID(double workHours, String activityName, int serialNumber, String ID) throws OperationNotAllowedException {
		assertEquals(workHours, projectManagementApp.getEmployeeWorkHours(projectManagementApp.findProjectActivity(activityName, projectManagementApp.findProject(serialNumber)), projectManagementApp.getEmployee(ID)), 0.1);
	}
	
	@When("the employee with ID {string} registers {double} work hours to the activity {string} in the project with serial number {int}")
	public void theEmployeeWithIDRegistersWorkHoursToTheActivityInTheProjectWithSerialNumber(String ID, double workHours, String activityName, int serialNumber) throws OperationNotAllowedException {
	    projectManagementApp.login(ID);
	    projectManagementApp.registerWorkHours(projectManagementApp.findProjectActivity(activityName, projectManagementApp.findProject(serialNumber)), workHours);
	}

	@Then("the total work hours of the activity with the name {string}, a start date week {int} and an end date week {int} in the project with the serial number {int}, is {double}")
	public void theTotalWorkHoursOfTheActivityWithTheNameAStartDateWeekAndAnEndDateWeekInTheProjectWithTheSerialNumberIs(String activityName, int startDate, int endDate, int serialNumber, double workHours) throws OperationNotAllowedException {
		
		assertEquals(workHours, projectManagementApp.getTotalWorkHours(projectManagementApp.findProjectActivity(activityName, projectManagementApp.findProject(serialNumber))), 0.1);
	}
}
	