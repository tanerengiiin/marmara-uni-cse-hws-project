
public class RegularEmployee extends Employee{
	// This is the file for the Github Repository.
	
	private double performanceScore;
	private double bonus;
	public RegularEmployee (int id, String firstName, String lastName, String
			gender, java.util.Calendar birthDate, String maritalStatus, String
			hasDriverLicence, double salary, java.util.Calendar hireDate, Department
			department, double performanceScore) throws Exception {
		//An object can be created from the extended class using super. The aim of our program is
		super(id,firstName,lastName,gender,birthDate,maritalStatus,hasDriverLicence,salary,hireDate,department);
		setPerformanceScore(performanceScore);
	}
	public RegularEmployee(Employee employee, double perfScore) throws Exception {
		super(employee.getId(),employee.getFirstName(),employee.getLastName(),employee.getGender(),employee.getBirthDate(),employee.getMaritalStatus(),employee.getHasDriverLicence(),employee.getSalary(),employee.getHireDate(),employee.getDepartment());
		setPerformanceScore(perfScore);
	}
	public double getPerformanceScore() {
		return performanceScore;
	}
	public void setPerformanceScore(double performanceScore) throws Exception {
		if(performanceScore<0) {
			throw new Exception("The value must be positive.");
		}else {
			this.performanceScore = performanceScore;	
		}
		
	}
	public double getBonus() {
		return bonus;
	}
	//an exception is thrown if the required conditions are not met
	public void setBonus(double bonus) throws Exception {
		if(bonus<0) {
			throw new Exception("The value must be positive.");
		}else {
			this.bonus = bonus;
		}
		
	}
	
	public String toString() {
		return super.toString()+"\n\t\t\t\tRegularEmployee Info [performanceScore="+performanceScore+", bonus="+getBonus()+"]";
	}
}
