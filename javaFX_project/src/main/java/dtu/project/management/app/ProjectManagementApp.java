package dtu.project.management.app;

import java.util.ArrayList;
import java.util.List;

import dtu.project.management.domain.Employee;

public class ProjectManagementApp {
	private Employee currentLogin;
	private List<Employee> employees = new ArrayList<>();

	
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
}
