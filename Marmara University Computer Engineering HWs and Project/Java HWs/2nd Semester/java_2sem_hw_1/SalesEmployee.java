import java.util.ArrayList;

public class SalesEmployee extends RegularEmployee{
	// Taner Engin - 150120041
	private ArrayList<Product> sales; //ArrayList to hold products sold by salesEmployee
	public static int numberOfSalesEmployees=0; //As SalesEmployee is produced, its value increases.
	
	public SalesEmployee(int id, String firstName, String lastName, String gender,
			java.util.Calendar birthDate, String maritalStatus, String hasDriverLicence,
			double salary, java.util.Calendar hireDate, Department department, double
			pScore, ArrayList<Product> s) throws Exception {
		super(id,firstName,lastName,gender,birthDate,maritalStatus,hasDriverLicence,salary,hireDate,department,pScore);
		setSales(s);
		numberOfSalesEmployees++;
	}
	public SalesEmployee(RegularEmployee re, ArrayList<Product> s) throws Exception {
		super(re.getId(),re.getFirstName(),re.getLastName(),re.getGender(),re.getBirthDate(),
				re.getMaritalStatus(),re.getHasDriverLicence(),re.getSalary(),re.getHireDate(),re.getDepartment(),
				re.getPerformanceScore());
		setSales(s);
	}
	public boolean addSale(Product s) {
		return sales.add(s);
	}
	public boolean removeSale(Product s) {
		return sales.remove(s);
	}
	public ArrayList<Product> getSales() {
		return sales;
	}
	
	public void setSales(ArrayList<Product> sales) {
		this.sales = sales;
	}
	
	public double getTotalAmountSales() {
		double totalPrice=0;
		for(int i=0; i<sales.size(); i++) {
			totalPrice+=sales.get(i).getPrice();
		}
		return totalPrice;
	}
	
	public String toString() {
		return super.toString()+"\n\t\t\t\t"+sales.toString();
	}
	
}
