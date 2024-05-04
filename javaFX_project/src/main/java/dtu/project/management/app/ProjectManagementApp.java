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
		assert employees != null;
		employees.add(new Employee("karl"));
		employees.add(new Employee("bjar"));
		employees.add(new Employee("huba"));
		employees.add(new Employee("beha"));
		employees.add(new Employee("wemo"));
		employees.add(new Employee("shuo"));
		employees.add(new Employee("erik"));
		assert employees.size() > 0;
	}

	public void testSetup() {
		assert employees != null;
		employees.add(new Employee("karl"));
		employees.add(new Employee("bjar"));
		employees.add(new Employee("beha"));
		employees.add(new Employee("wemo"));
		assert employees.size() > 0;
	}

	public Employee getCurrentLogin() {
		assert true;
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
		assert true;
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

	public Employee findEmployee(String ID) {
		assert employees != null;
		Employee result = null;
		for (Employee e : employees) {
			if (e.getId().equals(ID)) {
				result = e;
				break;
			}
		}
		assert (result == null) || (result.getId().equals(ID) && result instanceof Employee);
		return result;
	}

	public Project findProject(int serialNumber) {
		assert true;
		Project result = null;
		for (Project p : projects) {
			if (p.getSerialnumber() == serialNumber) {
				result = p;
				break;
			}
		}
		assert (result == null) || (result.getSerialnumber() == serialNumber && result instanceof Project);
		return result;
	}

	public ProjectActivity findProjectActivity(String name, Project project) throws OperationNotAllowedException {
		assert !name.equals("");
		ProjectActivity result = null;
		
		if (project == null) {
			throw new OperationNotAllowedException("Project does not exist");
		}
		
		for (ProjectActivity a : project.getActivities()) {
			if (a.getName().equals(name)) {
				result = a;
				break;
			}
		}
		assert (result == null) || (result.getName().equals(name) && result instanceof ProjectActivity);
		return result;
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
		if (findProjectActivity(tempProjectActivity.getName(), project) != null) {
			throw new OperationNotAllowedException("Activity already exists");
		}
		if (tempProjectActivity.getStartWeek() <= 0 || tempProjectActivity.getStartWeek() >= 54
				|| tempProjectActivity.getEndWeek() <= 0 || tempProjectActivity.getEndWeek() >= 54) {
			throw new OperationNotAllowedException("Invalid week number");
		}
		project.addActivity(tempProjectActivity);
		assert project.getActivities().contains(tempProjectActivity);
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
		assert employee.getActivities().contains(tempPersonalActivity);
	}

	public PersonalActivity findPersonalActivity(String activityName, Employee employee) {
		assert !activityName.equals("");
		assert employee != null;
		PersonalActivity result = null;
		for (PersonalActivity a : employee.getActivities()) {
			if (a.getName().equals(activityName)) {
				result = a;
				break;
			}
		}
		assert (result == null) || (result.getName().equals(activityName) && result instanceof PersonalActivity);
		return result;
	}

	public boolean isAssignedToActivity(Employee employee, ProjectActivity activity) {
		assert employee != null;
		assert activity != null;
		boolean result = activity.getEmployees().contains(employee);
		assert (result == true && activity.getEmployees().contains(employee)) || (result == false && !activity.getEmployees().contains(employee));
		return result;
	}

	public void addEmployeeToActivity(Employee employee, ProjectActivity activity, Project project)
			throws OperationNotAllowedException {
		assert true;
		if (activity == null) {
			throw new OperationNotAllowedException("Activity does not exist");
		}
		if (employee == null) {
			throw new OperationNotAllowedException("Employee does not exist");
		}

		if (currentLogin.equals(project.getProjectManager()) || currentLogin.equals(employee)) {
			if (!isAssignedToActivity(employee, activity)) {
				activity.addEmployeeToActivity(employee);
				assert activity.getEmployees().contains(employee);
				return;
			}
			throw new OperationNotAllowedException("Employee is already assigned to the activity");
		}
		throw new OperationNotAllowedException(
				"User is not project manager and can not assign other employees to activity");
	}

	public List<ProjectActivity> getProjectActivities(Project project) throws OperationNotAllowedException {
		assert true;
		if (project != null) {
			List<ProjectActivity> result = project.getActivities();
			assert result == project.getActivities();
			return result;
		}
		throw new OperationNotAllowedException("Project does not exist");
	}

	public List<PersonalActivity> getPersonalActivities() {
		assert currentLogin.getActivities() != null;
		return currentLogin.getActivities();
	}

	public void assignExpectedWorkHours(double expectedWorkHours, ProjectActivity activity)
			throws OperationNotAllowedException {
		assert expectedWorkHours % 0.5 == 0;
		if (activity != null) {
			activity.setExpectedWorkHours(expectedWorkHours);
			assert activity.getExpectedWorkHours() == expectedWorkHours;
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
			double tempHours = activity.getTotalWorkHours();
			activity.registerWorkHours(currentLogin, hours);
			assert activity.getTotalWorkHours() == tempHours + hours;
			return;
		}
		throw new OperationNotAllowedException("Activity does not exist");
	}

	public List<Employee> getEmployeesInActivity(ProjectActivity activity) throws OperationNotAllowedException {
		assert true;
		if (activity != null) {
			List<Employee> result = activity.getEmployees();
			assert result == activity.getEmployees();
			return result;
		}
		throw new OperationNotAllowedException("Activity does not exist");
	}

	public double getTotalWorkHours(ProjectActivity activity) {
		assert activity != null;
		return activity.getTotalWorkHours();
	}

}
