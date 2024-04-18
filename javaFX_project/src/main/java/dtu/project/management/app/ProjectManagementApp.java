package dtu.project.management.app;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import dtu.project.management.domain.Employee;
import dtu.project.management.domain.Project;

public class ProjectManagementApp {
	private Employee currentLogin;
	private Project project;
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
	
	public void setEmployees(List<Employee> employees) {
		this.employees = employees; 
	}
	
	public List<Employee> getEmployees(){
		return employees;
	}
	

	public void login(String id) throws OperationNotAllowedException {
		assert employees != null;
		
		for (Employee e: employees){
			if(e.getId().equals(id)) {
				currentLogin = e;
				assert currentLogin.getId().equals(id);
				return;
			}
		}
		throw new OperationNotAllowedException("Employee is not registered");
	}
	
	
	public List<Project> getProjects(){
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

	public void assignToProject(int serialNumber) throws OperationNotAllowedException {
		for (Project p : projects) {
			if (p.getSerialnumber() == serialNumber) {
				p.assignToProject(currentLogin);
				return;
			}
		}
		throw new OperationNotAllowedException("Project does not exist");
	}


	public boolean isAlreadyInProject(String ID, int serialNumber) {
		Project project = null;
		for (Project p : projects) {
			if (p.getSerialnumber() == serialNumber) {
				project = p;
			}
		}
		if (project != null) {
			for (Employee e : project.getEmployees()) {
				if (e.getId() == ID) {
					return true;
				}
			}			
		}
		return false;
	}
}
