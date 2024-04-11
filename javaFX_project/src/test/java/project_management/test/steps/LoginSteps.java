package project_management.test.steps;

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

public class LoginSteps {
	private ProjectManagementApp projectManagementApp;
	
	public LoginSteps(ProjectManagementApp projectManagementApp) {
		this.projectManagementApp = projectManagementApp;
	}
	
	
	
	@Given("that the user is not logged in")
	public void thatTheUserIsNotLoggedIn() {
		assertThat(projectManagementApp.getCurrentLogin(),is(equalTo(null)));
	}

	@Given("that there is an employee with ID {string}")
	public void thatThereIsAnEmployeeWithID(String id) {
		Employee employee = new Employee(id);
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee("karl"));
		employees.add(new Employee("bjar"));
		projectManagementApp.setEmployees(employees);
		
		
		boolean employeeExist = false;
		for (Employee e: projectManagementApp.getEmployees()){
			if(e.getId().equals(id)) {
				employeeExist = true;
				break;
			}
		}
		assertTrue(employeeExist);
		
	}

	@When("the user logs in with the ID {string}")
	public void theUserLogsInWithTheID(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("the employee is logged in with the ID {string}")
	public void theEmployeeIsLoggedInWithTheID(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
}
