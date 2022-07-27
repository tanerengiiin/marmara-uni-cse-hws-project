
public class Employee extends Person{
	// This is the file for the Github Repository.
	private double salary;
	private java.util.Calendar hireDate;
	private Department department;
	public static int numberOfEmployees=0; //Incremented each time a person object is spawned.
	public Employee(int id, String firstName, String lastName, String gender,
			java.util.Calendar birthDate, String maritalStatus, String hasDriverLicence,
			double salary, java.util.Calendar hireDate, Department department) throws Exception {
		super(id,firstName,lastName,gender,birthDate,maritalStatus,hasDriverLicence);
		setSalary(salary);
		setHireDate(hireDate);
		setDepartment(department);
		numberOfEmployees++;
	}
	
	public Employee(Person person, double salary, java.util.Calendar hireDate, Department department) throws Exception {
		super(person.getId(),person.getFirstName(),person.getLastName(),person.getGender(),person.getBirthDate(),person.getMaritalStatus(),person.getHasDriverLicence());
		setSalary(salary);
		setHireDate(hireDate);
		setDepartment(department);
		numberOfEmployees++;
	}
	
	public double raiseSalary(double percent) throws Exception {
		//this way it may throw an exception if the condition is not met
		if(percent<0) {
			throw new Exception("The value must be positive");
		}else {
			//The salary value is increased according to the value desired to be increased and the increased value is returned.
			salary=salary*(1+percent);
			return salary;
		}
	}
		
	public double raiseSalary(int amount) throws Exception {
		if(amount<0) {
			throw new Exception("The value must be positive");
		}else {
			salary=salary+amount;
			return salary;	
		}
		
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) throws Exception {
		if(salary<0) {
			throw new Exception("The value must be positive");
		}else {
			this.salary = salary;
		}
	}

	public java.util.Calendar getHireDate() {
		return hireDate;
	}

	public void setHireDate(java.util.Calendar hireDate) {
		this.hireDate = hireDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String toString() {
		return super.toString()+"\n\t\t\t\tEmployee Info [salary="+salary+", hireDate="+hireDate.get(5)+"/"+(hireDate.get(2)+1)+"/"+hireDate.get(1)+"]";
	}
}
