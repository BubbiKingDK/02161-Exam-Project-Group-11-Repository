package dtu.project.management.app;

import java.util.Scanner;

import dtu.project.management.domain.Employee;
import dtu.project.management.domain.Project;

public class UserInterface {
	
	private ProjectManagementApp projectManagementApp = new ProjectManagementApp();
	private Scanner console = new Scanner(System.in);
	
	public UserInterface() throws OperationNotAllowedException {
		projectManagementApp.setup();
		System.out.println("Welcome!");
		login();
	}
	
	
	public void mainMenu() throws OperationNotAllowedException {
		System.out.println();
		System.out.println("Input 1 to create project");
		System.out.println("Input 2 to see all projects");
		System.out.println("Input 3 to assign to project");
		System.out.println("Input 0 to close the program");
		int input = console.nextInt();
		switch (input) {
		case 1: 
			createProject();
		case 2: 
			showProjects();
		case 3:
			assignToProject();
		case 0:
			login();
			//System.exit(0)
		default:
			throw new IllegalArgumentException("Unexpected value: " + input);
		}
		
	}
	
	public void login() throws OperationNotAllowedException {
		System.out.print("Input login credentials: ");
		String input = console.next();
		projectManagementApp.login(input);
		System.out.println("You are now logged in as: " + projectManagementApp.getCurrentLogin().getId());
		mainMenu();
	}
	
	public void createProject() throws OperationNotAllowedException{
		System.out.print("Input project name: ");
		String input = console.next();
		projectManagementApp.createProject(input);
		projectManagementApp.addProject();
		System.out.println("Created project: " + projectManagementApp.getProject().getName() + " " + projectManagementApp.getProject().getSerialnumber());
		mainMenu();
	}
	
	public void showProjects() throws OperationNotAllowedException {
		System.out.println("List of projects:");
		for(Project p: projectManagementApp.getProjects()) {
			System.out.println(p.getSerialnumber() + " " + p.getName() );
		}
		mainMenu();
	}
	
	
	public void assignToProject() throws OperationNotAllowedException {
		System.out.println("Input project serial number:");
		int input = console.nextInt();
		projectManagementApp.assignToProject(input);
		
		Project tempProject = null;
		for (Project p : projectManagementApp.getProjects()) {
			if (p.getSerialnumber() == input) {
				tempProject = p;
			}
		}
		
		
		System.out.println("User: " + projectManagementApp.getCurrentLogin().getId() + " Has been assigned to project:" + tempProject.getName() + ":" + tempProject.getSerialnumber());
		System.out.println("Users in project:");
		for (Employee e : tempProject.getEmployees()) {
			System.out.println(e.getId());
		}
		
		mainMenu();
	}
	
}
