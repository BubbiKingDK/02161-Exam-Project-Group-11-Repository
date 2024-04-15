package dtu.project.management.app;

import java.util.Scanner;

public class UserInterface {
	
	private ProjectManagementApp projectManagementApp = new ProjectManagementApp();
	private Scanner console = new Scanner(System.in);
	
	public UserInterface() throws OperationNotAllowedException {
		projectManagementApp.setup();
		System.out.println("Welcome!");
		login();
	}
	
	public void login() throws OperationNotAllowedException {
		System.out.print("Input login credentials: ");
		String input = console.next();
		projectManagementApp.login(input);
		System.out.println(projectManagementApp.getCurrentLogin().getId());
	}
}
