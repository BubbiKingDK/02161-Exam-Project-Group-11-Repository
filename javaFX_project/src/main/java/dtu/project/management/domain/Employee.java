//Lavet af Weihao Mo
package dtu.project.management.domain;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	private String id;
	private List<PersonalActivity> activities = new ArrayList<>();

	public Employee(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void addPersonalActivity(PersonalActivity activity) {
		activities.add(activity);
	}

	public List<PersonalActivity> getActivities() {
		return activities;
	}
}
