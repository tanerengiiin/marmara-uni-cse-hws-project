
public class Department {
	// This is the file for the Github Repository.
	private int departmentId;
	private String departmentName;
	
	public Department(int departmentId, String departmentName) throws Exception {
		setDepartmentId(departmentId);
		setDepartmentName(departmentName);
	}

	public int getDepartmentId() {
		return departmentId;
	}
	
	//we can throw an exception like this
	public void setDepartmentId(int departmentId) throws Exception {
		if(departmentId<0) {
			throw new Exception("The value must be positive");
		}else {
			this.departmentId = departmentId;	
		}
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) throws Exception {
		if(departmentName.length()<3) {
			throw new Exception("The length of the value must be at least 3.");
		}else {
			this.departmentName = departmentName;	
		}
	}
	
	public String toString() {
		return "Department [departmentId="+departmentId+", departmentName="+departmentName+"]";
	}
}
