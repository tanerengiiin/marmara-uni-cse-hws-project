// This is the file for the Github Repository.
public class Factory { // Factory class is defined
	private String name; // various variables are defined
	private Employee[] employees;
	private Payroll[] payrolls;
	private double itemPrice;
	private Storage storage;
	
	public Factory(String name, int capacity, double itemPrice) { // Factory's constructor is defined
		this.name=name; //Factory's variables are matched to the variables sent to the constructor. This is done using this.
		storage = new Storage(capacity);
		this.itemPrice=itemPrice;
		payrolls = new Payroll[0]; //first defined as empty to create the Item class 
		employees = new Employee[0];
	}
	
	public double getRevenue() { // Returns the total revenue by multiplying the total number of elements in the storage array with the price of an item.
		return storage.getItemsLength()*itemPrice;
	}
	
	public double getPaidSalaries() { 
		double paidSalaries = 0;
		for (int i = 0; i < payrolls.length; i++) {
			paidSalaries+=payrolls[i].calculateSalary(); // collected by taking the salary of each employe
		}
		return paidSalaries;
	}
	
	public void addEmployee(Employee employee) { //creating a function that adds new employee
		Employee[] temp = new Employee[employees.length+1]; // A new employee is added to the employees array using the method in the addItem method
		System.arraycopy(employees, 0, temp, 0, employees.length);
		
		temp[temp.length-1]=employee;
		employees=temp;
		Item[] itemsOfEmployees = employee.startShift(); // random id is generated for each employee
		for (int i = 0; i < itemsOfEmployees.length; i++) {
			storage.addItem(itemsOfEmployees[i]); 
		}
	}
	
	public Employee removeEmployee(int id) { //If there is an element in the array that matches the submitted id, it will be removed from the array.
		Employee nullEmploye = null;
		if(employees.length==0) { // if the array is empty
			System.out.println("There are no employees!"); 
			return null;
		}
		for (int i = 0; i < employees.length; i++) { 
			if(employees[i].getId() == id) { 
				nullEmploye = employees[i]; 
				addPayroll(employees[i].endShift()); 
				
				Employee[] temp = employees; // an empty array is created
				employees = new Employee[temp.length-1];  // Created by reducing the length of the employees array by one
				for (int j = 0; j < temp.length; j++) {   
					if(i>j) { // skips the index of the matched value and continues with the others
						employees[j] = temp[j]; 
					} else if(i<j) {
						employees[j-1] = temp[j];
					}
					
				}
				break;
			} else if (employees[i].getId() != id && i == employees.length-1) { // if there is no value matching the submitted id
				System.out.println("Employee does not exist!");
				return null;
			}
		}
		
		return nullEmploye; // if the above values ​​are not provided we should return a null value
	}
	
	private void addPayroll(Payroll payroll) { // A new element is added to the payrolls array using the method in addItem 
		Payroll[] temp =new Payroll[payrolls.length+1];
		System.arraycopy(payrolls, 0, temp, 0, payrolls.length);
		
		temp[temp.length-1]=payroll;
		payrolls=temp;
	}
}
