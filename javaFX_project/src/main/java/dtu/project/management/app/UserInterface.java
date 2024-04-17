package dtu.project.management.app;

import java.util.Scanner;

import dtu.project.management.domain.Project;

public class UserInterface {
	
	private ProjectManagementApp projectManagementApp = new ProjectManagementApp();
	private Scanner console = new Scanner(System.in);
	
	public UserInterface() throws OperationNotAllowedException {
		projectManagementApp.setup();
		System.out.println("Welcome!");
		runApp();
	}
	
	public void runApp() throws OperationNotAllowedException {
		login();
		mainMenu();
	}
	
	public void mainMenu() {
		System.out.println();
		System.out.println("Input 1 to create project");
		System.out.println("Input 2 to see all projects");
		System.out.println("Input 0 to close the program");
		int input = console.nextInt();
		switch (input) {
		case 1: 
			createProject();
		case 2: 
			showProjects();
		case 0:
			System.exit(0);
		default:
			throw new IllegalArgumentException("Unexpected value: " + input);
		}
		
	}
	
	public void login() throws OperationNotAllowedException {
		System.out.print("Input login credentials: ");
		String input = console.next();
		projectManagementApp.login(input);
		System.out.println("You are now logged in as: " + projectManagementApp.getCurrentLogin().getId());
	}
	
	public void createProject(){
		System.out.print("Input project name: ");
		String input = console.next();
		projectManagementApp.createProject(input);
		projectManagementApp.addProject();
		System.out.println("Created project: " + projectManagementApp.getProject().getName() + " " + projectManagementApp.getProject().getSerialnumber());
		mainMenu();
	}
	
	public void showProjects() {
		System.out.println("List of projects:");
		for(Project p: projectManagementApp.getProjects()) {
			System.out.println(p.getSerialnumber() + " " + p.getName() );
		}
		mainMenu();
	}
	
	
}
