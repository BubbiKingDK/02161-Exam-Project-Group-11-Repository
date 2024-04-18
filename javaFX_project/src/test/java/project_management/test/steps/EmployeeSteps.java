package project_management.test.steps;

import dtu.project.management.app.OperationNotAllowedException;
import dtu.project.management.app.ProjectManagementApp;
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
		int tempSerialNumber = 0;
	    projectManagementApp.createProject("Project");
	    projectManagementApp.addProject();
	    for (Project p : projectManagementApp.getProjects()) {
	    	if (p.getSerialnumber() == serialNumber) {
	    		tempSerialNumber = p.getSerialnumber();
	    		break;
	    	}
	    }
	    assertEquals(tempSerialNumber, serialNumber);
	}

	@Given("an employee with ID {string} is logged in")
	public void anEmployeeWithIDIsLoggedIn(String ID) throws OperationNotAllowedException {
		projectManagementApp.setup();
		projectManagementApp.login(ID);
	    assertEquals(projectManagementApp.getCurrentLogin().getId(), ID);
	}
	
	@Given("the employee with ID {string} is not already in the project with serial number {int}")
	public void theEmployeeWithIDIsNotAlreadyInTheProjectWithSerialNumber(String ID, int serialNumber) {
	    assertFalse(projectManagementApp.isAlreadyInProject(ID, serialNumber));
	}

	@When("the user with ID {string} assigns themselves to the project with serial number {int}")
	public void theUserWithIDAssignsThemselvesToTheProjectWithSerialNumber(String ID, int serialNumber) throws OperationNotAllowedException {
	    try {
	    	projectManagementApp.assignToProject(serialNumber);
		} catch (OperationNotAllowedException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("the employee with ID {string} is assigned to the project with serial number {int}")
	public void theEmployeeWithIDIsAssignedToTheProjectWithSerialNumber(String ID, int serialNumber) {
		Project project = null;
		String tempID = "";
	    for (Project p : projectManagementApp.getProjects()) {
	    	if (p.getSerialnumber() == serialNumber) {
	    		project = p;
	    		break;
	    	}
	    }
	    if (project != null) {
	    	for (Employee e : project.getEmployees()) {
	    		if (e.getId().equals(ID)) {
	    			tempID = e.getId();
	    		}
	    	}
	    }
	    assertEquals(tempID, ID);
	}
	
	@Given("that there is not a project with serial number {int}")
	public void thatThereIsNotAProjectWithSerialNumber(int serialNumber) {
		int tempSerialNumber = 0;
	    for (Project p : projectManagementApp.getProjects()) {
	    	if (p.getSerialnumber() == serialNumber) {
	    		tempSerialNumber = p.getSerialnumber();
	    		break;
	    	}
	    }
	    assertFalse(tempSerialNumber == serialNumber);
	}
	
}
