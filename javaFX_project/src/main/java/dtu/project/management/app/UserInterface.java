package dtu.project.management.app;

import java.util.Scanner;

import dtu.project.management.domain.Activity;
import dtu.project.management.domain.Employee;
import dtu.project.management.domain.Project;
import io.cucumber.core.internal.com.fasterxml.jackson.core.io.OutputDecorator;
import io.cucumber.java.an.Y;

public class UserInterface {

	private ProjectManagementApp projectManagementApp = new ProjectManagementApp();
	private Scanner console = new Scanner(System.in);

	public UserInterface() throws OperationNotAllowedException {
	}

	public void run() throws OperationNotAllowedException {
		projectManagementApp.setup();
		System.out.println("Welcome!");
		login();
	}

	public void mainMenu() throws OperationNotAllowedException {
		System.out.println();
		System.out.println("Input 1 to create project");
		System.out.println("Input 2 to see all projects");
		System.out.println("Input 3 to assign project manager");
		System.out.println("Input 4 to create activity");
		System.out.println("Input 5 to see activities");
		System.out.println("Input 0 to close the program");
		try {
			int input = console.nextInt();
			switch (input) {
			case 1:
				createProject();
			case 2:
				showProjects();
			case 3:
				assignProjectManager();
			case 4:
				createActivity();
			case 5:
				seeActivities();
			case 0:
				login();
				// System.exit(0)
			default:
				System.out.println(("Unexpected value: " + input));
				mainMenu();
			}
		} catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
			mainMenu();
		}


	}

	private void seeActivities() throws OperationNotAllowedException {
		System.out.println("Enter project serial number");
		System.out.println("Enter 0 to create personal activity");
		int input = console.nextInt();
		
		if (input == 0) {
			for (Activity a : projectManagementApp.getCurrentLogin().getActivities()) {
				System.out.println(a.getName() + " - Start week: " + a.getStartWeek() + ", End week: " + a.getEndWeek());
			}
		} else {
			for (Activity a : projectManagementApp.getProject(input).getActivities()) {
				System.out.println(a.getName() + " - Start week: " + a.getStartWeek() + ", End week: " + a.getEndWeek());
			}
		}
		
		mainMenu();
	}

	private void createActivity() throws OperationNotAllowedException {
		System.out.println("Enter project serial number");
		System.out.println("Enter 0 to create personal activity");
		String input = console.next();
		input += console.nextLine();
		int intInput = convertInt(input);
		System.out.println("Enter activity name");
		String activityName = console.next();
		System.out.println("Enter start week");
		String startWeek = console.next();
		input += console.nextLine();
		int intStartWeek = convertInt(startWeek);
		System.out.println("Enter end week");
		String endWeek = console.next();
		input += console.nextLine();
		int intEndWeek = convertInt(endWeek);
		
		projectManagementApp.createActivity(activityName, intStartWeek, intEndWeek);			
		if (intInput == 0) {
			projectManagementApp.addActivity(projectManagementApp.getCurrentLogin());
		} else {
			projectManagementApp.addActivity(projectManagementApp.getProject(intInput));
		}
		mainMenu();
	}

	private void assignProjectManager() throws OperationNotAllowedException {
		System.out.println("Enter project serial number");
		String input = console.next();
		input += console.nextLine();
		int intInput = convertInt(input);
		projectManagementApp.setProjectManager(projectManagementApp.getProject(intInput));
		mainMenu();
	}

	public void login() throws OperationNotAllowedException {
		System.out.print("Input login credentials: ");
		String input = console.next();
		input += console.nextLine();
		try {
			projectManagementApp.login(input);
		} catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
			login();
		}

		System.out.println("You are now logged in as: " + projectManagementApp.getCurrentLogin().getId());
		mainMenu();
	}

	public void createProject() throws OperationNotAllowedException {
		System.out.print("Input project name: ");
		String input = console.next();
		input += console.nextLine(); 
		projectManagementApp.createProject(input);
		projectManagementApp.addProject();
		System.out.println("Created project: " + projectManagementApp.getProject().getName() + " "
				+ projectManagementApp.getProject().getSerialnumber());
		mainMenu();
	}

	public void showProjects() throws OperationNotAllowedException {
		System.out.println("List of projects:");
		for (Project p : projectManagementApp.getProjects()) {
			System.out.print(p.getSerialnumber() + " " + p.getName());
			if (p.getProjectManager() != null) {
				System.out.println(" - Project Manager: " +p.getProjectManager().getId());
			} else {
				System.out.println(" - Project Manager: None");
			}
		}
		mainMenu();
	}

	public int convertInt(String input) throws OperationNotAllowedException {
		int output = 0;
		try {
			output = Integer.parseInt(input);
		} catch (Exception e) {
			System.out.println("Error: Not an integer");
			mainMenu();
		}
		
		return output;
	}
}
