package dtu.project.management.app;

import java.util.ArrayList;
import java.util.List;

import dtu.project.management.domain.Employee;
import dtu.project.management.domain.Project;

public class ProjectManagementApp {
	private Employee currentLogin;
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
		for (Employee e: employees){
			if(e.getId().equals(id)) {
				currentLogin = e;
				return;
			}
		}
		throw new OperationNotAllowedException("Employee is not registered");
	}
	
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	public List<Project> getProjects(){
		return projects;
	}
	
}
