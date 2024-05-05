//Lavet af Karl Johannes Agerbo og Bjarke Søderhamn Petersen
package dtu.project.management.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dtu.project.management.app.OperationNotAllowedException;

public class ProjectActivity extends Activity {
	private List<Employee> employees;
	private double expectedWorkHours = 0d;

	private HashMap<Employee, Double> workHours;

	public ProjectActivity(String name, int startWeek, int endWeek) {
		super(name, startWeek, endWeek);
		employees = new ArrayList<>();
		workHours = new HashMap<>();
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void addEmployeeToActivity(Employee employee) {
		employees.add(employee);
	}

	public void setExpectedWorkHours(double expectedWorkHours) {
		this.expectedWorkHours = expectedWorkHours;
	}

	public double getExpectedWorkHours() {
		return expectedWorkHours;
	}

	public HashMap<Employee, Double> getWorkHours() {
		return workHours;
	}

	public void registerWorkHours(Employee employee, double hours) throws OperationNotAllowedException {
		if (!workHours.containsKey(employee)) {
			workHours.put(employee, 0d);			
		}
		workHours.put(employee, workHours.get(employee) + hours);
	}

	public double getTotalWorkHours() {
		double sum = 0d;

		for (Employee e : workHours.keySet()) {
			sum += workHours.get(e);
		}
		return sum;
	}
}
