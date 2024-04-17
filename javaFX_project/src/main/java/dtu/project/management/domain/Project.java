package dtu.project.management.domain;

import java.util.List;

import dtu.project.management.app.ProjectManagementApp;

public class Project{
	
	private String name;
	private int serialNumber;
	private ProjectManagementApp projectManagementApp;

	
	public Project(String name, ProjectManagementApp projectManagementApp){
		this.name = name;
		this.projectManagementApp = projectManagementApp;
		
		
		//Assign correct serial number based on year and amount of already created projects
		String serialNumberString = "";
		String yearString = Integer.toString(projectManagementApp.getYear()); 
		yearString = yearString.substring(yearString.length() - 2);
		
		List<Project> projects = projectManagementApp.getProjects();
		if(projects.isEmpty()) {
			serialNumberString = "000";
		}else {
			serialNumberString =  Integer.toString(projects.get(projects.size()-1).getSerialnumber()).substring(2);
		}
		serialNumberString = yearString + serialNumberString;
		
		this.serialNumber = Integer.parseInt(serialNumberString) +1;
	}
	
	public String getName() {
		return name;
	}
	
	
	public int getSerialnumber() {
		return serialNumber;
	}

	
}