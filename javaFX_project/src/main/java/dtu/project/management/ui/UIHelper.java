package dtu.project.management.ui;

import dtu.project.management.app.OperationNotAllowedException;
import dtu.project.management.app.ProjectManagementApp;
import dtu.project.management.domain.Employee;
import dtu.project.management.domain.PersonalActivity;
import dtu.project.management.domain.Project;
import dtu.project.management.domain.ProjectActivity;

public class UIHelper {

	private ProjectManagementApp projectManagementApp;


	public UIHelper(ProjectManagementApp projectManagementApp) {
		this.projectManagementApp = projectManagementApp;
	}
	//Lavet af Karl Johannes Agerbo
	public String personalActivitiesToString() {
		String s = "";
		for (PersonalActivity a : projectManagementApp.getPersonalActivities()) {
			s += a.getName() + " - Start week: " + a.getStartWeek() + ", End week: " + a.getEndWeek() + "\n";
		}
		return s;
	}
	//Lavet af Bjarke Søderhamn Petersen
	public String projectActivitiesToString(int serialNumber) throws OperationNotAllowedException {
		String s = "";

		for (ProjectActivity a : projectManagementApp
				.getProjectActivities(projectManagementApp.findProject(serialNumber))) {

			s += a.getName() + " - Start week: " + a.getStartWeek() + ", End week: " + a.getEndWeek()
					+ ", Expected work hours: " + a.getExpectedWorkHours() + ", Total registered work hours: "
					+ a.getTotalWorkHours() + "\n";
		}
		return s;
	}
	//Lavet af Weihao Mo
	public String projectsToString() {
		String s = "";
		for (Project p : projectManagementApp.getProjects()) {
			s += "\n" + p.getSerialnumber() + " " + p.getName();
			if (p.getProjectManager() != null) {
				s += " - Project Manager: " + p.getProjectManager().getId();
			} else {
				s += " - Project Manager: None";
			}
		}
		return s;
	}
	//Lavet af Bjarke Søderhamn Petersen
	public String employeesNotInActivityToString(String activityName, int serialNumber)
			throws OperationNotAllowedException {
		ProjectActivity activity = projectManagementApp.findProjectActivity(activityName,
				projectManagementApp.findProject(serialNumber));
		String s = "";
		for (Employee e : activity.getWorkHours().keySet()) {
			if (!activity.getEmployees().contains(e)) {
				s += "\n" + e.getId() + ", Registered work hours: " + activity.getWorkHours().get(e);
			}
		}
		return s;
	}
	//Lavet af Bjarke Søderhamn Petersen
	public String employeesInActivityToString(String activityName, int serialNumber)
			throws OperationNotAllowedException {
		ProjectActivity activity = projectManagementApp.findProjectActivity(activityName,
				projectManagementApp.findProject(serialNumber));
		String s = "";
		for (Employee e : projectManagementApp.getEmployeesInActivity(activity)) {
			s += e.getId() + ", Registered work hours: " + (activity.getWorkHours().get(e) != null ? activity.getWorkHours().get(e) : "0.0") + "\n";
		}
		return s;
	}

}