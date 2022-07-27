
public class Person {
	// This is the file for the Github Repository.
	//Some values ​​are processed in the method and kept as bytes according to the value received, or a String expression is returned according to this byte value.
	// If it is less than 3 letters an exception will be thrown but hasDriverLicence is not affected by this rule.
	private int id;
	private String firstName;
	private String lastName;
	private byte gender;
	private java.util.Calendar birthDate;
	private byte maritalStatus;
	private boolean hasDriverLicence;
	
	public Person(int id, String firstName, String lastName, String gender, java.util.Calendar birthDate, String maritalStatus, String hasDriverLicence) throws Exception{
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setBirthDate(birthDate);
		setMaritalStatus(maritalStatus);
		setGender(gender);
		setHasDriverLicence(hasDriverLicence);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) throws Exception {
		if(id<0) {
			throw new Exception("The value must be positive.");
		}else {
			this.id = id;	
		}
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws Exception {
		if(firstName.length()<3) {
			throw new Exception("The length of the value must be at least 3.");
		}else {
			this.firstName = firstName;	
		}
		
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws Exception {
		if(lastName.length()<3) {
			throw new Exception("The length of the value must be at least 3.");
		}else {
			this.lastName = lastName;	
		}
		
	}

	public String getGender() {
		if(this.gender==(byte)2) {
			return "Man";
		}else if(this.gender==(byte)1) {
			return "Woman"; 
		}else {
			return "null";
		}
	}
	//The byte value is returned according to the received tring expression.
	public void setGender(String gender) throws Exception {
		if(gender.length()<3) {
			throw new Exception("The length of the value must be at least 3.");
		}else {
			if(gender.equals("Man")) {
				this.gender=(byte)2;
			}else if(gender.equals("Woman")) {
				this.gender=(byte)1;
			}	
		}
		
	}

	public java.util.Calendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(java.util.Calendar birthDate) {
		this.birthDate = birthDate;
	}

	public String getMaritalStatus() {
		if(maritalStatus==(byte)2) {
			return "Married";
		}else if(maritalStatus==(byte)1) {
			return "Single"; 
		}else {
			return null;
		}
	}

	public void setMaritalStatus(String maritalStatus) throws Exception {
		if(maritalStatus.length()<3) {
			throw new Exception("The length of the value must be at least 3.");
		}else {
			if(maritalStatus.equals("Single")) {
				this.maritalStatus=(byte)1;
			}else if(maritalStatus.equals("Married")) {
				this.maritalStatus=(byte)2;
			}	
		}
	}

	public String getHasDriverLicence() {
		if(hasDriverLicence) {
			return "Yes";
		}else if(hasDriverLicence==false) {
			return "No"; 
		}else {
			return null;
		}
	}

	public void setHasDriverLicence(String hasDriverLicence) {
		if(hasDriverLicence.equals("Yes")) {
			this.hasDriverLicence = true;
		}else if(hasDriverLicence.equals("No")) {
			this.hasDriverLicence = false;
		}
	}
	
	public String toString() {
		return "\t\t\t\tPerson Info [id="+getId()+", firstName="+getFirstName()+", lastName="+getLastName()+", gender="+getGender()+"]"; 
	}
	//Requires a longer message than toString when printing non-employee Persons
	public String getLongPersonInfo() {
		return "Person [id="+getId()+", firstName="+getFirstName()+", lastName="+getLastName()+", gender="+getGender()+", birthDate="+birthDate.get(5)+"/"+(birthDate.get(2)+1)+"/"+birthDate.get(1)+", maritalStatus="+getMaritalStatus()+", hasDriverLicence="+getHasDriverLicence()+"]";
	}
}
