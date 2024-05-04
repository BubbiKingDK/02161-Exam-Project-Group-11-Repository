package dtu.example.ui;

import java.util.Scanner;

import dtu.project.management.app.OperationNotAllowedException;
import dtu.project.management.app.ProjectManagementApp;
import io.cucumber.core.internal.com.fasterxml.jackson.core.io.OutputDecorator;
import io.cucumber.java.an.Y;

public class UserInterface {

	private ProjectManagementApp projectManagementApp = new ProjectManagementApp();
	private UIHelper uIHelper = new UIHelper(projectManagementApp);
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
		System.out.println("Input one of the following:");
		System.out.println("1. Create project");
		System.out.println("2. Create activity");
		System.out.println("3. Assign project manager");
		System.out.println("4. Assign employee to activity");
		System.out.println("5. Assign expected work hours to activity");
		System.out.println("6. Register work hours to activity");
		System.out.println("7. See all projects");
		System.out.println("8. See activities and total work hours");
		System.out.println("9. See employees and registered time to activity");
		System.out.println("0. Logout");
		try {
			String input = console.next();
			input += console.nextLine();
			int intInput = convertInt(input);
			switch (intInput) {
			case 1:
				createProject();
			case 2:
				createActivity();
			case 3:
				assignProjectManager();
			case 4:
				assignEmployeeToActivity();
			case 5:
				assignExpectedWorkHours();
			case 6:
				registerWorkHours();
			case 7:
				showProjects();
			case 8:
				seeActivities();
			case 9:
				seeEmployeesInActivity();
			case 0:
				login();
			default:
				System.out.println(("Unexpected value: " + input));
				mainMenu();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			mainMenu();
		}
	}

	private void registerWorkHours() throws OperationNotAllowedException {
		System.out.println("Enter project serial number");
		String serialNumber = console.next();
		serialNumber += console.nextLine();
		int intSerialNumber = convertInt(serialNumber);

		System.out.println("Enter activity name");
		String activityName = console.next();
		activityName += console.nextLine();

		System.out.println("Enter work hours");
		String workHours = console.next();
		workHours += console.nextLine();
		double doubleWorkHours = convertDouble(workHours);

		projectManagementApp.registerWorkHours(
				projectManagementApp.findProjectActivity(activityName, projectManagementApp.findProject(intSerialNumber)),
				doubleWorkHours);
		mainMenu();
	}

	private void assignExpectedWorkHours() throws OperationNotAllowedException {
		System.out.println("Enter project serial number");
		String serialNumber = console.next();
		serialNumber += console.nextLine();
		int intSerialNumber = convertInt(serialNumber);

		System.out.println("Enter activity name");
		String activityName = console.next();
		activityName += console.nextLine();

		System.out.println("Enter expected work hours");
		String expectedWorkHours = console.next();
		expectedWorkHours += console.nextLine();
		double doubleExpectedWorkHours = convertDouble(expectedWorkHours);
		projectManagementApp.assignExpectedWorkHours(doubleExpectedWorkHours, projectManagementApp
				.findProjectActivity(activityName, projectManagementApp.findProject(intSerialNumber)));

		System.out.println(
				"The activity " + activityName + " is now assigned " + expectedWorkHours + " expected work hours");
		mainMenu();
	}

	private void seeEmployeesInActivity() throws OperationNotAllowedException {
		System.out.println("Enter project serial number");
		String serialNumber = console.next();
		serialNumber += console.nextLine();
		int intSerialNumber = convertInt(serialNumber);

		System.out.println("Enter activity name");
		String activityName = console.next();
		activityName += console.nextLine();
		System.out.println("Employees in activity:");
		System.out.println(uIHelper.employeesInActivityToString(activityName, intSerialNumber));
		System.out.print("Employees not in activity:");
		System.out.println(uIHelper.employeesNotInActivityToString(activityName, intSerialNumber));
		mainMenu();
	}

	private void assignEmployeeToActivity() throws OperationNotAllowedException {
		System.out.println("Enter project serial number");
		String serialNumber = console.next();
		serialNumber += console.nextLine();
		int intSerialNumber = convertInt(serialNumber);

		System.out.println("Enter activity name");
		String activityName = console.next();
		activityName += console.nextLine();

		System.out.println("Enter user ID that you want to assign to activity");
		String ID = console.next();
		ID += console.nextLine();

		projectManagementApp.addEmployeeToActivity(projectManagementApp.findEmployee(ID),
				projectManagementApp.findProjectActivity(activityName, projectManagementApp.findProject(intSerialNumber)), projectManagementApp.findProject(intSerialNumber));
		System.out.println("Employee with ID " + ID + " is now assigned to the activity: " + activityName);
		mainMenu();
	}

	private void seeActivities() throws OperationNotAllowedException {
		System.out.println("Enter project serial number");
		System.out.println("Enter 0 to see personal activity");
		String input = console.next();
		input += console.nextLine();
		int intInput = convertInt(input);

		if (intInput == 0) {
			System.out.print(uIHelper.personalActivitiesToString());			
		} else {
			System.out.print(uIHelper.projectActivitiesToString(intInput));
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
		activityName += console.nextLine();
		System.out.println("Enter start week");
		String startWeek = console.next();
		startWeek += console.nextLine();
		int intStartWeek = convertInt(startWeek);
		System.out.println("Enter end week");
		String endWeek = console.next();
		endWeek += console.nextLine();
		int intEndWeek = convertInt(endWeek);

		if (intInput == 0) {
			projectManagementApp.createPersonalActivity(activityName, intStartWeek, intEndWeek);
			projectManagementApp.addPersonalActivity(projectManagementApp.getCurrentLogin());
			System.out.println("Created personal activity: " + projectManagementApp
					.findPersonalActivity(activityName, projectManagementApp.getCurrentLogin()).getName());
		} else {
			projectManagementApp.createProjectActivity(activityName, intStartWeek, intEndWeek);
			projectManagementApp.addProjectActivity(projectManagementApp.findProject(intInput));
			System.out.println("Created project activity: " + projectManagementApp
					.findProjectActivity(activityName, projectManagementApp.findProject(intInput)).getName());
		}
		mainMenu();
	}

	private void assignProjectManager() throws OperationNotAllowedException {
		System.out.println("Enter project serial number");
		String input = console.next();
		input += console.nextLine();
		int intInput = convertInt(input);
		projectManagementApp.setProjectManager(projectManagementApp.findProject(intInput));
		System.out.println("You are now project manager of project " + intInput + "!");
		mainMenu();
	}

	public void login() throws OperationNotAllowedException {
		System.out.println("Input login credentials");
		System.out.println("Input 0 to close program");
		String input = console.next();
		input += console.nextLine();
		if (input.equals("0")) {
			System.out.println("Goodbye :)");
			System.exit(0);
		}
		try {
			projectManagementApp.login(input);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
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
		System.out.print("List of projects:");
		System.out.println(uIHelper.projectsToString());
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

	public double convertDouble(String input) throws OperationNotAllowedException {
		double output = 0d;
		try {
			output = Double.parseDouble(input);
		} catch (Exception e) {
			System.out.println("Error: Not a number");
			mainMenu();
		}
		if (output % 0.5 != 0) {
			System.out.println("Error: You can only register half hours");
			mainMenu();
		}
		return output;
	}
	

}
