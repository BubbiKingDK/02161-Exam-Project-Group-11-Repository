package dtu.project.management.domain;

public class Project{
	
	private String name;
	private Employee projectManager;
	private int serialnumber;

	
	public Project(String name, int serialnumber){
		this.name = name;
		this.serialnumber = serialnumber;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSerialnumber() {
		return serialnumber;
	}
}