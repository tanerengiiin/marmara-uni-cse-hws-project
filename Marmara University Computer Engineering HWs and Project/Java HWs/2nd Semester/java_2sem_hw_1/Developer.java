import java.util.ArrayList;

public class Developer extends RegularEmployee{
	// This is the file for the Github Repository.
	private ArrayList<Project> projects; //ArrayList to hold developer's projects
	public static int numberOfDevelopers=0; //number of all developers created
	
	public Developer(int id, String firstName, String lastName, String gender,
			java.util.Calendar birthDate, String maritalStatus, String hasDriverLicence,
			double salary, java.util.Calendar hireDate, Department department, double
			pScore, ArrayList<Project> p) throws Exception {
		super(id,firstName,lastName,gender,birthDate,maritalStatus,hasDriverLicence,salary,hireDate,department,pScore);
		setProjects(p);
		numberOfDevelopers++; //Incremented each time a developer is created
	}
	public Developer(RegularEmployee re, ArrayList<Project> p) throws Exception {
		super(re.getId(),re.getFirstName(),re.getLastName(),re.getGender(),re.getBirthDate(),re.getMaritalStatus(),
				re.getHasDriverLicence(),re.getSalary(),re.getHireDate(),re.getDepartment(),re.getPerformanceScore());
		setProjects(p);
		numberOfDevelopers++;
	}

	public boolean addProjects(Project s) {
		return projects.add(s);
	}
	public boolean removeProjects(Project s) {
		return projects.remove(s);
	}
	public ArrayList<Project> getProjects() {
		return projects;
	}

	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}
	public String toString() {
		return super.toString()+"\n\t\t\t\t"+projects.toString();
	}
}
