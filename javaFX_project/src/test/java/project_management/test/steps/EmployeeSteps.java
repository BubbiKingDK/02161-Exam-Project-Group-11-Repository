package project_management.test.steps;

import dtu.project.management.app.OperationNotAllowedException;
import dtu.project.management.app.ProjectManagementApp;
import dtu.project.management.domain.Activity;
import dtu.project.management.domain.Employee;
import dtu.project.management.domain.Project;

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

public class EmployeeSteps {
	
	private ProjectManagementApp projectManagementApp;
	private ErrorMessageHolder errorMessage;
	
	public EmployeeSteps(ProjectManagementApp projectManagementApp, ErrorMessageHolder errorMessage) {
		this.projectManagementApp = projectManagementApp;
		this.errorMessage = errorMessage;
	}
	
	@Given("that there is a project with serial number {int}")
	public void thatThereIsAProjectWithSerialNumber(int serialNumber) {
	    projectManagementApp.createProject("Project");
	    projectManagementApp.addProject();
	    Project project = projectManagementApp.getProject(serialNumber);
	    assertEquals(project.getSerialnumber(), serialNumber);
	}

	@Given("that there is not a project with serial number {int}")
	public void thatThereIsNotAProjectWithSerialNumber(int serialNumber) {
	    assertEquals(projectManagementApp.getProject(serialNumber), null);
	}

	@Given("the current Login ID is {string}")
	public void theCurrentLoginIDIs(String ID) throws OperationNotAllowedException {
	    projectManagementApp.login(ID);
	}

	@Given("employee with ID {string} is not assigned to activity {string} in project with serial number {int}")
	public void employeeWithIDIsNotAssignedToActivityInProjectWithSerialNumber(String ID, String activityName,int serialNumber) throws OperationNotAllowedException {
	    boolean isAssigned = projectManagementApp.isAssignedToActivity(projectManagementApp.getEmployee(ID),projectManagementApp.getActivity(activityName, projectManagementApp.getProject(serialNumber)));
	    assertFalse(isAssigned);
	}

	@When("the user assigns the employee with ID {string} to the activity {string} in project with serial number {int}")
	public void theUserAssignsTheEmployeeWithIDToTheActivityInProjectWithSerialNumber(String ID, String ActivityName,int serialNumber) throws OperationNotAllowedException {
		Employee employee = projectManagementApp.getEmployee(ID);
		Activity activity = projectManagementApp.getActivity();
		try {
		    projectManagementApp.addEmployeeToActivity(employee,activity,projectManagementApp.getProject(serialNumber));
		} catch (OperationNotAllowedException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("the employee with ID {string} is assigned to the activity {string} in project with serial number {int}")
	public void theEmployeeWithIDIsAssignedToTheActivityInProjectWithSerialNumber(String ID, String activityName, int serialNumber) throws OperationNotAllowedException {
	    Employee e = projectManagementApp.getEmployee(ID);
	    Activity a = projectManagementApp.getActivity(activityName, projectManagementApp.getProject(serialNumber));
	    assertTrue(projectManagementApp.getEmployeesInActivity(a).contains(e));
	}
	
	@Given("employee with ID {string} is assigned to activity {string} in project with serial number {int}")
	public void employeeWithIDIsAssignedToActivityInProjectWithSerialNumber(String ID, String activityName, int serialNumber) throws OperationNotAllowedException {
		projectManagementApp.login(ID);
		Employee employee = projectManagementApp.getEmployee(ID);
		Activity activity = projectManagementApp.getActivity(activityName, projectManagementApp.getProject(serialNumber));
	    projectManagementApp.addEmployeeToActivity(employee,activity,projectManagementApp.getProject(serialNumber));
	    boolean isAssigned = projectManagementApp.isAssignedToActivity(projectManagementApp.getEmployee(ID),projectManagementApp.getActivity(activityName, projectManagementApp.getProject(serialNumber)));
	    assertTrue(isAssigned);
	}

}
