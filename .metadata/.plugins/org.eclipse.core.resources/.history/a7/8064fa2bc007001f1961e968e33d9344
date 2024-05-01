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
	public void theActivityWithTheNameInTheProjectWithTheSerialNumberIsAssignedWorkHours(String activityName, int serialNumber, int expectedWorkHours) {
	    assertEquals(projectManagementApp.getActivity(activityName, projectManagementApp.getProject(serialNumber)).getExpectedWorkHours(), expectedWorkHours);
	}

}
	