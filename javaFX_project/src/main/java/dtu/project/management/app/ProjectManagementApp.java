package dtu.project.management.app;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import dtu.project.management.domain.ProjectActivity;
import dtu.project.management.domain.Activity;
import dtu.project.management.domain.Employee;
import dtu.project.management.domain.PersonalActivity;
import dtu.project.management.domain.Project;
import io.cucumber.java.be.I.Is;

public class ProjectManagementApp {
	private Employee currentLogin;
	private Project tempProject;
	private ProjectActivity tempProjectActivity;
	private PersonalActivity tempPersonalActivity;
	private List<Employee> employees = new ArrayList<>();
	private List<Project> projects = new ArrayList<>();

	public void setup() {
		employees.add(new Employee("karl"));
		employees.add(new Employee("bjar"));
		employees.add(new Employee("huba"));
		employees.add(new Employee("beha"));
		employees.add(new Employee("wemo"));
		employees.add(new Employee("shuo"));
	}

	public void testSetup() {
		employees.add(new Employee("karl"));
		employees.add(new Employee("bjar"));
		employees.add(new Employee("huba"));
		employees.add(new Employee("beha"));
		employees.add(new Employee("wemo"));
	}

	public Employee getCurrentLogin() {
		return currentLogin;
	}

	public void login(String id) throws OperationNotAllowedException {
		assert employees != null;

		for (Employee e : employees) {
			if (e.getId().equals(id)) {
				currentLogin = e;
				assert currentLogin.getId().equals(id);
				return;
			}
		}
		throw new OperationNotAllowedException("Employee is not registered");
	}

	public List<Project> getProjects() {
		assert projects != null;
		assert projects instanceof List<Project>;
		return projects;
	}

	public void createProject(String name) {
		assert !name.equals("");
		tempProject = new Project(name, this);
	}

	public void addProject() {
		assert tempProject != null;
		projects.add(tempProject);
	}

	public int getYear() {
		assert Year.now().getValue() != 0;
		return Year.now().getValue();

	}

	public Project getProject() {
		assert tempProject != null;
		assert tempProject instanceof Project;
		return tempProject;
	}

	public void setProjectManager(Project project) throws OperationNotAllowedException {
		assert currentLogin != null;
		assert currentLogin instanceof Employee;

		if (project != null) {
			project.setProjectManager(currentLogin);
			assert project.getProjectManager().equals(currentLogin);
			return;
		}
		throw new OperationNotAllowedException("Project does not exist");
	}

	public Employee getEmployee(String ID) {
		assert employees != null;
		for (Employee e : employees) {
			if (e.getId().equals(ID)) {
				assert e.getId().equals(ID);
				return e;
			}
		}
		return null;
	}

	public Project getProject(int serialNumber) {
		for (Project p : projects) {
			if (p.getSerialnumber() == serialNumber) {
				return p;
			}
		}
		return null;
	}

	public ProjectActivity getProjectActivity(String name, Project project) throws OperationNotAllowedException {
		if (project == null) {
			throw new OperationNotAllowedException("Project does not exist");
		}

		for (ProjectActivity a : project.getActivities()) {
			if (a.getName().equals(name)) {
				return a;
			}
		}
		return null;
	}

	public void createProjectActivity(String name, int startWeek, int endWeek) {
		assert !name.equals("");
		tempProjectActivity = new ProjectActivity(name, startWeek, endWeek);
	}

	public void createPersonalActivity(String name, int startWeek, int endWeek) {
		assert !name.equals("");
		tempPersonalActivity = new PersonalActivity(name, startWeek, endWeek);
	}

	public void addProjectActivity(Project project) throws OperationNotAllowedException {
		assert tempProjectActivity != null;
		if (project == null) {
			throw new OperationNotAllowedException("Project does not exist");

		}
		if (getProjectActivity(tempProjectActivity.getName(), project) != null) {
			throw new OperationNotAllowedException("Activity already exists");
		}
		if (tempProjectActivity.getStartWeek() <= 0 || tempProjectActivity.getStartWeek() >= 54
				|| tempProjectActivity.getEndWeek() <= 0 || tempProjectActivity.getEndWeek() >= 54) {
			throw new OperationNotAllowedException("Invalid week number");
		}
		project.addActivity(tempProjectActivity);
	}

	public ProjectActivity getTempProjectActivity() {
		return tempProjectActivity;
	}

	public void addPersonalActivity(Employee employee) throws OperationNotAllowedException {
		assert employee != null;
		assert tempPersonalActivity != null;
		if (employee.getActivities().contains(tempPersonalActivity)) {
			throw new OperationNotAllowedException("Activity already exists");
		}
		if (tempPersonalActivity.getStartWeek() <= 0 || tempPersonalActivity.getStartWeek() >= 54
				|| tempPersonalActivity.getEndWeek() <= 0 || tempPersonalActivity.getEndWeek() >= 54) {
			throw new OperationNotAllowedException("Invalid week number");
		}
		employee.addPersonalActivity(tempPersonalActivity);

	}

	public PersonalActivity getPersonalActivity(String activityName, Employee employee) {
		assert !activityName.equals("");
		assert employee != null;
		for (PersonalActivity a : employee.getActivities()) {
			if (a.getName().equals(activityName)) {
				assert a.getName().equals(activityName);
				return a;
			}
		}
		return null;
	}

	public boolean isAssignedToActivity(Employee employee, ProjectActivity activity) {
		assert employee != null;
		assert activity != null;
		return activity.getEmployees().contains(employee);
	}

	public void addEmployeeToActivity(Employee employee, ProjectActivity activity, Project project)
			throws OperationNotAllowedException {
		if (activity == null) {
			throw new OperationNotAllowedException("Activity does not exist");
		}
		if (employee == null) {
			throw new OperationNotAllowedException("Employee does not exist");
		}

		if (currentLogin.equals(project.getProjectManager()) || currentLogin.equals(employee)) {
			if (!isAssignedToActivity(employee, activity)) {
				activity.addEmployeeToActivity(employee);
				return;
			}
			throw new OperationNotAllowedException("Employee is already assigned to the activity");
		}
		throw new OperationNotAllowedException(
				"User is not project manager and can not assign other employees to activity");
	}

	public List<ProjectActivity> getProjectActivities(Project project) throws OperationNotAllowedException {
		if (project != null) {
			return project.getActivities();
		}
		throw new OperationNotAllowedException("Project does not exist");
	}

	public List<PersonalActivity> getPersonalActivities() {
		assert currentLogin.getActivities() != null;
		return currentLogin.getActivities();
	}

	public void assignExpectedWorkHours(int expectedWorkHours, ProjectActivity activity)
			throws OperationNotAllowedException {
		if (activity != null) {
			activity.setExpectedWorkHours(expectedWorkHours);
			return;
		}
		throw new OperationNotAllowedException("Activity does not exist");
	}

	public double getEmployeeWorkHours(ProjectActivity activity, Employee employee) {
		assert employee != null;
		assert activity != null;
		return activity.getWorkHours().get(employee);
	}

	public void registerWorkHours(ProjectActivity activity, double hours) throws OperationNotAllowedException {
		assert hours % 0.5 == 0;
		if (activity != null) {
			activity.registerWorkHours(currentLogin, hours);
			return;
		}
		throw new OperationNotAllowedException("Activity does not exist");
	}

	public List<Employee> getEmployeesInActivity(ProjectActivity activity) throws OperationNotAllowedException {
		if (activity != null) {
			return activity.getEmployees();
		}
		throw new OperationNotAllowedException("Activity does not exist");
	}

	public double getTotalWorkHours(ProjectActivity activity) {
		assert activity != null;
		return activity.getTotalWorkHours();
	}

}
