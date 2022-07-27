import java.util.ArrayList;

public class Manager extends Employee{
	// This is the file for the Github Repository.
	private ArrayList<RegularEmployee> regularEmployees=new ArrayList<RegularEmployee>();
	private double bonusBudget;
	public Manager(int id, String firstName, String lastName, String gender,
			java.util.Calendar birthDate, String maritalStatus, String hasDriverLicence,
			double salary, java.util.Calendar hireDate, Department department,double
			bonusBudget) throws Exception {
		super(id,firstName,lastName,gender,birthDate,maritalStatus,hasDriverLicence,salary,hireDate,department);
		setBonusBudget(bonusBudget);
	}
	public Manager(Employee employee, double bonusBudget) throws Exception {
		super(employee.getId(),employee.getFirstName(),employee.getLastName(),employee.getGender(),employee.getBirthDate(),employee.getMaritalStatus(),employee.getHasDriverLicence(),employee.getSalary(),employee.getHireDate(),employee.getDepartment());
		setBonusBudget(bonusBudget);
	}
	public void addEmployee(RegularEmployee e) {
		regularEmployees.add(e);
	}
	
	public void removeEmployee(RegularEmployee e) {
		regularEmployees.remove(e);
	}
	
	public void distributeBonusBudget() throws Exception {
		// bonus = unit * salary * performanceScore     unit = bonusBudget / âˆ‘(ğ��¬ğ��šğ��¥ğ��šğ��«ğ��² âˆ— ğ��©ğ���ğ��«ğ��Ÿğ��¨ğ��«ğ��¦ğ��šğ��§ğ��œğ���ğ��’ğ��œğ��¨ğ��«ğ���)
		// The bonus value is calculated according to the above formula and this bonus value is set to each value in the RegularEmployees list.

		double total=0;
		for(int i=0; i<regularEmployees.size(); i++) {
			total+=regularEmployees.get(i).getSalary()*regularEmployees.get(i).getPerformanceScore();
		}
		double unit=bonusBudget/total;
		for(int i=0; i<regularEmployees.size(); i++) {
			double bonus=(double)(unit*regularEmployees.get(i).getSalary()*regularEmployees.get(i).getPerformanceScore());
			bonus=(Math.round((((int)(bonus*1000))/10.0)))/100.0;
			regularEmployees.get(i).setBonus(bonus);
		}
	}
	public double getBonusBudget() {
		return bonusBudget;
	}
	public void setBonusBudget(double bonusBudget) throws Exception {
		if(bonusBudget<0) {
			throw new Exception("The value must be positive");
		}else {
			this.bonusBudget = bonusBudget;	
		}
		
	}
	public ArrayList<RegularEmployee> getRegularEmployees() {
		return regularEmployees;
	}
	public void setRegularEmployees(ArrayList<RegularEmployee> regularEmployees) {
		this.regularEmployees = regularEmployees;
	}
	public String toString() {
		return "\tManager [id: "+getId()+", "+getFirstName()+" "+getLastName()+"\n\t\t# of Employees: "+getRegularEmployees().size()+"]";
	}
}
