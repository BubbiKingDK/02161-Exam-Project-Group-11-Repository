package dtu.project.management.app;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import dtu.project.management.domain.Activity;
import dtu.project.management.domain.Employee;
import dtu.project.management.domain.Project;
import io.cucumber.java.be.I.Is;

public class ProjectManagementApp {
	private Employee currentLogin;
	private Project project;
	private Activity activity;
	private List<Employee> employees = new ArrayList<>();
	private List<Project> projects = new ArrayList<>();

	public void setup() {
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
		return projects;
	}

	public void createProject(String name) {
		project = new Project(name, this);
	}

	public void addProject() {
		projects.add(project);
	}

	public int getYear() {
		return Year.now().getValue();
	}

	public Project getProject() {
		return project;
	}

	

	public void setProjectManager(int serialNumber) throws OperationNotAllowedException {
		for (Project p : projects) {
			if (p.getSerialnumber() == serialNumber) {
				p.setProjectManager(currentLogin);
				return;
			}
		}
		throw new OperationNotAllowedException("Project does not exist");
	}

	public Employee getEmployee(String ID) {
		for (Employee e : employees) {
			if (e.getId().equals(ID)) {
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

	public Activity getActivity(String name, Project project) {
		for (Activity a : project.getActivities()) {
			if (a.getName().equals(name)) {
				return a;
			}
		}
		return null;
	}

	public void createActivity(String name, int startWeek, int endWeek) {
		activity = new Activity(name, startWeek, endWeek);
	}

	public void addActivity(Project project) throws OperationNotAllowedException {
		if (project == null) {
			throw new OperationNotAllowedException("Project does not exist");
			
		}
		if (getActivity(activity.getName(), project) != null) {
			throw new OperationNotAllowedException("Activity already exists");
		} 
		project.addActivity(activity);
	}
	
	public Activity getActivity() {
		return activity;
	}

	public void addActivity(Activity activity, Employee employee) throws OperationNotAllowedException {
		if (!employee.getActivities().contains(activity)) {
			employee.addActivity(activity);
			return;
		}
		throw new OperationNotAllowedException("Activity already exists");
	}

	public Activity getActivity(String activityName, Employee employee) {
		for (Activity a : employee.getActivities()) {
			if(a.getName().equals(activityName)) {
				return a;
			}
		}
		return null;
	}
}
