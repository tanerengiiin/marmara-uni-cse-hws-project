import java.util.ArrayList;
//We can create customer class using person
public class Customer extends Person{
	// This is the file for the Github Repository.
	private ArrayList<Product> products; // arrayList required to hold Customer's products
	
	public Customer(int id, String firstName, String lastName, String gender, java.util.Calendar birthDate, String maritalStatus, String hasDriverLicence, ArrayList<Product> products) throws Exception {
		super(id,firstName,lastName,gender,birthDate,maritalStatus,hasDriverLicence);
		setProducts(products);
	}
	
	public Customer(Person person, ArrayList<Product>products) throws Exception {
		super(person.getId(),person.getFirstName(),person.getLastName(),person.getGender(),person.getBirthDate(),person.getMaritalStatus(),person.getHasDriverLicence());
		setProducts(products);
	}

	public ArrayList<Product> getProducts() {
		//we can get the products arrayList like this
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
	public String toString() { //a toString method is created based on the output
		return "Customer [id: "+getId()+" products="+products.toString();
	}
	
}
