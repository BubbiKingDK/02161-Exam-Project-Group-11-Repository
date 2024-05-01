package dtu.project.management.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dtu.project.management.app.OperationNotAllowedException;

public class Activity {
	private String name;
	private int startWeek;
	private int endWeek;
	private List<Employee> employees = new ArrayList<>();
	private int expectedWorkHours = 0;
	
	private HashMap<Employee, Integer> workHours = new HashMap<>();
	
	
	public Activity(String name, int startWeek, int endWeek) {
		this.name = name;
		this.startWeek = startWeek;
		this.endWeek = endWeek;
	}
	
	public String getName() {
		return name;
	}

	public int getStartWeek() {
		return startWeek;
	}

	public int getEndWeek() {
		return endWeek;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}
	
	public void addEmployeeToActivity(Employee employee) {
		employees.add(employee);
		workHours.put(employee, 0);
	}

	public void setExpectedWorkHours(int expectedWorkHours) {
		this.expectedWorkHours = expectedWorkHours;		
	}
	
	public int getExpectedWorkHours() {
		return expectedWorkHours;
	}
	
	public HashMap<Employee, Integer> getWorkHours() {
		return workHours;
	}
	
	public void registerWorkHours(Employee employee, int hours) throws OperationNotAllowedException {
		try {
			workHours.put(employee, workHours.get(employee)+hours);
		} catch (NullPointerException e) {
			throw new OperationNotAllowedException("User not assigned to activity");
		}
		
	}
}
