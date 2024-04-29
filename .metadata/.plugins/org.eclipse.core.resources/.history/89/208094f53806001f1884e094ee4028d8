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
		projectManagementApp.setup();
	    projectManagementApp.createProject("Project");
	    projectManagementApp.addProject();
	    Project project = projectManagementApp.getProject(serialNumber);
	    assertEquals(project.getSerialnumber(), serialNumber);
	}

	@Given("an employee with ID {string} is logged in")
	public void anEmployeeWithIDIsLoggedIn(String ID) throws OperationNotAllowedException {
		projectManagementApp.setup();
		projectManagementApp.login(ID);
	    assertEquals(projectManagementApp.getCurrentLogin().getId(), ID);
	}
	

	
	@Given("that there is not a project with serial number {int}")
	public void thatThereIsNotAProjectWithSerialNumber(int serialNumber) {
	    assertEquals(projectManagementApp.getProject(serialNumber), null);
	}
	
}
