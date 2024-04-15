package project_management.test.steps;

import static org.junit.Assert.assertTrue;

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
	
	public ProjectSteps(ProjectManagementApp projectManagementApp, ErrorMessageHolder errorMessage) {
		this.projectManagementApp = projectManagementApp;
		this.errorMessage = errorMessage;
	
	}
	
	
	
	@Given("that there is a project with serial number {int} and a name {string}")
	public void thatThereIsAProjectWithSerialNumberAndAName(int serialnumber, String name) {		
		
		List<Project> projects = new ArrayList<>();
		projects.add(new Project(name, serialnumber));
		projectManagementApp.setProjects(projects);
		
		boolean projectExist = false;
		for (Project e: projectManagementApp.getProjects()) {
			if(e.getName().equals(name) && e.getSerialnumber() == serialnumber) {
				projectExist = true;
				break;
			}
		}
		assertTrue(projectExist);
		
	}

	@Given("the project is not in the system")
	public void theProjectIsNotInTheSystem() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the project is added to the system")
	public void theProjectIsAddedToTheSystem() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the project with the serial number {int} and a name {string} is added to the system")
	public void theProjectWithTheSerialNumberAndANameIsAddedToTheSystem(Integer int1, String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	
}