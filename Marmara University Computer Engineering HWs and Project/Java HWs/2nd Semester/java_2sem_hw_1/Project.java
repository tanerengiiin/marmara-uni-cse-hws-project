
public class Project {
	// This is the file for the Github Repository.
	private String projectName;
	private java.util.Calendar startDate;
	private boolean state;
	
	public Project(String pName, java.util.Calendar startDate, String state) throws Exception {
		setProjectName(pName);
		setStartDate(startDate);
		setState(state);
	}
	public void close() {
		state=false;
	}
	//A String value is saved according to the boolean value and vice versa
	public String getState() {
		if(state) {
			return "Open";
		}else if(!state) {
			return "Close";
		}else {
			return null;
		}
	}
	
	//an exception is thrown if the required conditions are not met
	public void setState(String state) throws Exception {
		if(state.length()<3) {
			throw new Exception("The length of the value must be at least 3.");
		}else {
			if(state.equals("Open")) {
				this.state=true;
			}else if(state.equals("Close")) {
				this.state=false;
			}	
		}
		
		
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) throws Exception {
		if(projectName.length()<3) {
			throw new Exception("The length of the value must be at least 3.");
		}else {
			this.projectName = projectName;	
		}
		
	}

	public java.util.Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(java.util.Calendar startDate) {
		this.startDate = startDate;
	}
	
	public String toString() {
		return "Project [projectName="+getProjectName()+", startDate="+getStartDate().get(5)+"/"+(getStartDate().get(2)+1)+"/"+getStartDate().get(1)+", state="+state+"]";
	}
}
