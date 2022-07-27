// This is the file for the Github Repository.
public class Payroll { // Defined Payroll class
	private int workHour; // private variables are defined	
	private int itemCount; 
	
	public Payroll(int workHour, int itemCount) { // Payroll's constructor is defined
		this.workHour=workHour; //Payroll's varabiles are matched to the variables sent to the constructor. This is done using this.
		this.itemCount=itemCount;
	}
	
	

	public int calculateSalary() { // CalcultaSalary method is defined
		return workHour*3+itemCount*2; // Salary is the sum of 3 times the working time and 2 times the number of items produced.
	}
	
	public String toString() {//toString method returns a text of the class
		return "The work hour is "+workHour+" and the produced item count is "+itemCount+".";
	}
}
