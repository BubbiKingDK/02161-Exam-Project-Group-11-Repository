package dtu.project.management.domain;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	private String id;
	private List <Activity> activities = new ArrayList<>();
	
	public Employee(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void addActivity(Activity activity) {
		activities.add(activity);
	}

	public List <Activity> getActivities() {
		return activities;
	}
}
