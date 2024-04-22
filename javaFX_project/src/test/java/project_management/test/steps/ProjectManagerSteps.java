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
	@Given("an employee with ID {string} is registered with the project with serial number {int}")
	public void anEmployeeWithIDIsRegisteredWithTheProjectWithSerialNumber(String ID, int serialNumber) throws OperationNotAllowedException {
		projectManagementApp.setup();
		projectManagementApp.login(ID);
		projectManagementApp.assignToProject(serialNumber);
	}
	
	@When("the user assigns the project manager with ID {string} to the project with serial number {int}")
	public void theUserAssignsTheProjectManagerWithIDToTheProjectWithSerialNumber(String ID, int serialNumber) throws OperationNotAllowedException {
		projectManagementApp.setup();
		projectManagementApp.login(ID);
		try {
	    	projectManagementApp.setProjectManager(serialNumber);
		} catch (OperationNotAllowedException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
		
	}


	@Then("the employee with ID {string} is registered project manager of the project with serial number {int}")
	public void theEmployeeWithIDIsRegisteredProjectManagerOfTheProjectWithSerialNumber(String ID, Integer serialNumber) {
		Project p = projectManagementApp.getProject(serialNumber);
	    assertEquals(p.getProjectManager().getId(), ID);
	}
	
	@Given("an employee with ID {string} is not registered with the project with serial number {int}")
	public void anEmployeeWithIDIsNotRegisteredWithTheProjectWithSerialNumber(String ID, int serialNumber) {
		Project project = projectManagementApp.getProject();
		Employee employee = projectManagementApp.getEmployee(ID);
		assertFalse(project.getEmployees().contains(employee));
	}
	@Given("an employee with ID {string} is the project manager for the project with serial number {int}")
	public void anEmployeeWithIDIsTheProjectManagerForTheProjectWithSerialNumber(String ID, int serialNumber) throws OperationNotAllowedException {
	    projectManagementApp.login(ID);
	    projectManagementApp.setProjectManager(serialNumber);
	    
		Project project = null;
	    for (Project p : projectManagementApp.getProjects()) {
	    	if (p.getSerialnumber() == serialNumber) {
	    		project = p;
	    		break;
	    	}
	    }
	    assertEquals(ID, project.getProjectManager().getId());
	}

	@Then("the employee with ID {string} is no longer project manager of the project with serial number {int}.")
	public void theEmployeeWithIDIsNoLongerProjectManagerOfTheProjectWithSerialNumber(String ID, int serialNumber) {

		Project project = null;
	    for (Project p : projectManagementApp.getProjects()) {
	    	if (p.getSerialnumber() == serialNumber) {
	    		project = p;
	    		break;
	    	}
	    }
	    assertFalse(project.getProjectManager().getId().equals(ID));
	}
}