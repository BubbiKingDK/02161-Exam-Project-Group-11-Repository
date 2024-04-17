package project_management.test.steps;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.intThat;
import static org.junit.Assert.assertFalse;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import dtu.project.management.app.ProjectManagementApp;
import dtu.project.management.domain.Employee;
import dtu.project.management.domain.Project;
import io.cucumber.core.internal.com.fasterxml.jackson.core.sym.Name;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import project_management.test.steps.helper.ErrorMessageHolder;

public class ProjectSteps {

	private ProjectManagementApp projectManagementApp;
	private ErrorMessageHolder errorMessage;

	private Project project;
	private List<Project> projects = new ArrayList<>();

	public ProjectSteps(ProjectManagementApp projectManagementApp, ErrorMessageHolder errorMessage) {
		this.projectManagementApp = projectManagementApp;
		this.errorMessage = errorMessage;

	}

	@Given("the year is the current year")
	public void theYearIs() {
		int year = Year.now().getValue();
		assertEquals(year, projectManagementApp.getYear());
	}

	@Given("that there is a project with the name {string}")
	public void thatThereIsAProjectWithTheName(String name) {
		projectManagementApp.createProject(name);
	}

	@When("the project is added to the system")
	public void theProjectIsAddedToTheSystem() {
		projectManagementApp.addProject();
	}

	@Then("the project with the serial number {int} and a name {string} is added to the system")
	public void theProjectWithTheSerialNumberAndANameIsAddedToTheSystem(int serialnumber, String name) {
		Project tempProject = projectManagementApp.getProject();
		
		assertTrue(projectManagementApp.getProjects().contains(tempProject));
		assertEquals(tempProject.getSerialnumber(), serialnumber);
		assertEquals(tempProject.getName(), name);
	}
	
	
	@Given("that there is a project with the name {string} and serial number {int} in the system")
	public void thatThereIsAProjectWithTheNameAndSerialNumberInTheSystem(String name, Integer int1) {
		projectManagementApp.createProject(name);
		projectManagementApp.addProject();
		
	}

}