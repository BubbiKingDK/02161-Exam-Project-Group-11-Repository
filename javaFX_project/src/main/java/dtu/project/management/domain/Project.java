//Lavet af Benjamin Benyo Endahl Hansen og Weihao Mo
package dtu.project.management.domain;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import dtu.project.management.app.ProjectManagementApp;

public class Project {

	private String name;
	private int serialNumber;
	private Employee projectManager;
	private List<ProjectActivity> activities;
	private List<Project> projects;

	public Project(String name, List<Project> projects) {
		this.name = name;
		this.projects = projects;
		this.activities = new ArrayList<>();

		// Assign correct serial number based on year and amount of already created
		// projects
		String serialNumberString = "";
		String yearString = Integer.toString(getYear());
		yearString = yearString.substring(yearString.length() - 2);

		if (projects.isEmpty()) {
			serialNumberString = "000";
		} else {
			serialNumberString = Integer.toString(projects.get(projects.size() - 1).getSerialnumber()).substring(2);
		}
		serialNumberString = yearString + serialNumberString;

		this.serialNumber = Integer.parseInt(serialNumberString) + 1;
	}

	public String getName() {
		return name;
	}

	public int getSerialnumber() {
		return serialNumber;
	}

	public void setProjectManager(Employee employee) {
		projectManager = employee;
	}

	public Employee getProjectManager() {
		return projectManager;
	}

	public void addActivity(ProjectActivity activity) {
		activities.add(activity);
	}

	public List<ProjectActivity> getActivities() {
		return activities;
	}
	
	public int getYear() {
		return Year.now().getValue();
	}
}