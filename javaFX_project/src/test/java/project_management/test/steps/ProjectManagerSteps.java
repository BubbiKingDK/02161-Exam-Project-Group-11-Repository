package project_management.test.steps;

import dtu.project.management.app.OperationNotAllowedException;
import dtu.project.management.app.ProjectManagementApp;
import dtu.project.management.domain.Employee;
import dtu.project.management.domain.Project;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.nullable;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import project_management.test.steps.helper.ErrorMessageHolder;

public class ProjectManagerSteps {
	private ProjectManagementApp projectManagementApp;
	private ErrorMessageHolder errorMessage;

	public ProjectManagerSteps(ProjectManagementApp projectManagementApp, ErrorMessageHolder errorMessage) {
		this.projectManagementApp = projectManagementApp;
		this.errorMessage = errorMessage;
	}
	
	@When("the user assigns the project manager with ID {string} to the project with serial number {int}")
	public void theUserAssignsTheProjectManagerWithIDToTheProjectWithSerialNumber(String ID, int serialNumber) throws OperationNotAllowedException {
		projectManagementApp.login(ID);
		try {
	    	projectManagementApp.setProjectManager(projectManagementApp.findProject(serialNumber));
		} catch (OperationNotAllowedException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("the employee with ID {string} is registered project manager of the project with serial number {int}")
	public void theEmployeeWithIDIsRegisteredProjectManagerOfTheProjectWithSerialNumber(String ID, Integer serialNumber) {
		Project p = projectManagementApp.findProject(serialNumber);
	    assertEquals(p.getProjectManager().getId(), ID);
	}
	
	
	@Given("an employee with ID {string} is the project manager for the project with serial number {int}")
	public void anEmployeeWithIDIsTheProjectManagerForTheProjectWithSerialNumber(String ID, int serialNumber) throws OperationNotAllowedException {
	    projectManagementApp.login(ID);
	    projectManagementApp.setProjectManager(projectManagementApp.findProject(serialNumber));
	    
		Project project = projectManagementApp.findProject(serialNumber);
	    assertEquals(ID, project.getProjectManager().getId());
	}

	@Then("the employee with ID {string} is no longer project manager of the project with serial number {int}.")
	public void theEmployeeWithIDIsNoLongerProjectManagerOfTheProjectWithSerialNumber(String ID, int serialNumber) {
		Project project = projectManagementApp.findProject(serialNumber);
	    assertFalse(project.getProjectManager().getId().equals(ID));
	}
	
	@Given("an employee with ID {string} is not the project manager for the project with serial number {int}")
	public void anEmployeeWithIDIsNotTheProjectManagerForTheProjectWithSerialNumber(String ID, int serialNumber) {
	    assertNotEquals(projectManagementApp.findProject(serialNumber).getProjectManager(),projectManagementApp.findEmployee(ID));
	}
}