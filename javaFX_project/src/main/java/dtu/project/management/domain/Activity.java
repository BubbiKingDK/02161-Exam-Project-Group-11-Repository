//@author Bjarke Søderhamn Petersen
package dtu.project.management.domain;

public abstract class Activity {

	private String name;
	private int startWeek;
	private int endWeek;

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

}
