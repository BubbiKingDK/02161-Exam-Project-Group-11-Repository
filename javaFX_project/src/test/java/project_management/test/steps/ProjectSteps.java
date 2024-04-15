package project_management.test.steps;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import dtu.project.management.app.ProjectManagementApp;
import dtu.project.management.domain.Employee;
import dtu.project.management.domain.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import project_management.test.steps.helper.ErrorMessageHolder;






public class ProjectSteps{
	
	private ProjectManagementApp projectManagementApp;
	private ErrorMessageHolder errorMessage;
	
	private Project project;
	private List<Project> projects = new ArrayList<>();
	
	public ProjectSteps(ProjectManagementApp projectManagementApp, ErrorMessageHolder errorMessage) {
		this.projectManagementApp = projectManagementApp;
		this.errorMessage = errorMessage;
	
	}
	
	@Given("that there is a project with serial number {int} and a name {string}")
	public void thatThereIsAProjectWithSerialNumberAndAName(int serialnumber, String name) {		
		project = new Project(name, serialnumber);
		
	}

	@Given("the project is not in the system")
	public void theProjectIsNotInTheSystem() {
		assertFalse(projects.contains(project));
	}

	@When("the project is added to the system")
	public void theProjectIsAddedToTheSystem() {
		projects.add(project);
		projectManagementApp.setProjects(projects);
	}

	@Then("the project with the serial number {int} and a name {string} is added to the system")
	public void theProjectWithTheSerialNumberAndANameIsAddedToTheSystem(int serialnumber, String name) {
		assertTrue(projects.contains(project));
	}

	
}