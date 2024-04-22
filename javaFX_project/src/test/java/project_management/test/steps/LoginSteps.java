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

public class LoginSteps {
	private ProjectManagementApp projectManagementApp;
	private ErrorMessageHolder errorMessage;
	
	public LoginSteps(ProjectManagementApp projectManagementApp, ErrorMessageHolder errorMessage) {
		this.projectManagementApp = projectManagementApp;
		this.errorMessage = errorMessage;
	}
	
	
	
	@Given("that the user is not logged in")
	public void thatTheUserIsNotLoggedIn() {
		assertEquals(projectManagementApp.getCurrentLogin(),null);
	}

	@Given("that there is an employee with ID {string}")
	public void thatThereIsAnEmployeeWithID(String ID) {
		projectManagementApp.setup();
		Employee e = projectManagementApp.getEmployee(ID);
		assertEquals(e.getId(), ID);
		
	}

	@When("the user logs in with the ID {string}")
	public void theUserLogsInWithTheID(String id) throws OperationNotAllowedException {
		try {
			projectManagementApp.login(id);
		} catch (OperationNotAllowedException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
		
	}

	@Then("the employee is logged in with the ID {string}")
	public void theEmployeeIsLoggedInWithTheID(String id) {
		assertEquals(projectManagementApp.getCurrentLogin().getId(),id);
	}
	
	@Given("that there is not an employee with ID {string}")
	public void thatThereIsNotAnEmployeeWithID(String ID) {
		projectManagementApp.setup();
		Employee e = projectManagementApp.getEmployee(ID);
		assertEquals(e, null);
	}

	@Then("the error message {string} is given")
	public void theErrorMessageIsGiven(String errorMessage) {
		assertEquals(errorMessage, this.errorMessage.getErrorMessage());
	}
}
