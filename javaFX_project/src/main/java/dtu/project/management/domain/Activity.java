package dtu.project.management.domain;

import java.util.ArrayList;
import java.util.List;

public class Activity {
	private String name;
	private int startWeek;
	private int endWeek;
	private List<Employee> employees = new ArrayList<>();
	private int expectedWorkHours;
	
	
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
	}

	public void setExpectedWorkHours(int expectedWorkHours) {
		this.expectedWorkHours = expectedWorkHours;		
	}
	
	public int getExpectedWorkHours() {
		return expectedWorkHours;
	}
	
}
