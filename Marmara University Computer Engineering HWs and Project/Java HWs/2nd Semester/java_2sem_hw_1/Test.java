import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws Exception {
		// Taner Engin - 150120041
		// The purpose of this program is to read data from a file, generate objects according to the parent-child
		// relationship, and return some results as files by using the appropriate methods for these objects.
		// It generates an object according to the first word of each line read from the input and keeps this object in the required arraylist.
		
		File inputFile = new File("input.txt"); // input file
		File outputFile = new File("output.txt"); // output file
		if (!inputFile.exists()) {
			inputFile.createNewFile(); // Generates the file if the input file does not exist
		}
		if (!outputFile.exists()) {
			outputFile.createNewFile(); // Generates the file if the output file does not exist
		}

		PrintWriter output = new PrintWriter(outputFile); // to write to output file
		Scanner input = new Scanner(inputFile); // to read the input file

		ArrayList<Person> persons = new ArrayList<Person>(); //To keep objects produced from person and person as polymorphic
		ArrayList<Department> departments = new ArrayList<Department>(); // ArrayLists needed to hold objects
		ArrayList<Project> projects = new ArrayList<Project>();
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			while (input.hasNextLine()) {
				String getLineText[] = input.nextLine().split(" ");
				int id;
				Calendar date;
				//We generate an object according to the first word of the line by sending the necessary parameters.
				if (getLineText[0].equals("Department")) {
					id = Integer.parseInt(getLineText[1]);
					Department department = new Department(id, getLineText[2]);
					departments.add(department);
				} else if (getLineText[0].equals("Project")) {
					date = calendar(getLineText[2]);
					Project project = new Project(getLineText[1], date, getLineText[3]);
					projects.add(project);
				} else if (getLineText[0].equals("Product")) {
					date = calendar(getLineText[2]);
					double price = Double.parseDouble(getLineText[3]);
					Product product = new Product(getLineText[1], date, price);
					products.add(product);
				} else if (getLineText[0].equals("Person")) {
					date = calendar(getLineText[5]);
					id = Integer.parseInt(getLineText[3]);
					persons.add(new Person(id, getLineText[1], getLineText[2], getLineText[4], date, getLineText[6],
							getLineText[7]));
				} else if (getLineText[0].equals("Manager")) {
					id = Integer.parseInt(getLineText[1]);
					double bonus = Double.parseDouble(getLineText[2]);
					for (int i = 0; i < persons.size(); i++) {
						if ((persons.get(i) instanceof Employee) && persons.get(i).getId() == id) {
							persons.add(new Manager((Employee) persons.get(i), bonus));
							// We generate the person object with equal ids as polymorphism. Since the already produced object will be produced from person, 
							// we delete the used person object from the persons list. We can also use this method for other productions.
							persons.remove(persons.get(i)); 
							break;
						}
					}
				} else if (getLineText[0].equals("Developer")) {
					id = Integer.parseInt(getLineText[1]);
					ArrayList<Project> devProjects = new ArrayList<Project>();
					//We pull the projects required for the developer from the projects arraylist
					for (int i = 2; i < getLineText.length; i++) {
						for (int j = 0; j < projects.size(); j++) {
							if (projects.get(j).getProjectName().equals(getLineText[i])) {
								devProjects.add(projects.get(j));
							}
						}
					}
					for (int i = 0; i < persons.size(); i++) {
						if (persons.get(i) instanceof RegularEmployee && persons.get(i).getId() == id) {
							persons.add(new Developer((RegularEmployee) persons.get(i), devProjects));
							persons.remove(persons.get(i));
							break;
						}
					}
				} else if (getLineText[0].equals("Employee")) {
					id = Integer.parseInt(getLineText[1]);
					double salary = Double.valueOf(getLineText[2]);
					date = calendar(getLineText[3]);
					Department dpt = null;
					for (int i = 0; i < departments.size(); i++) {
						if (departments.get(i).getDepartmentName().equals(getLineText[4])) {
							dpt = departments.get(i);
						}
					}
					for (int i = 0; i < persons.size(); i++) {
						if (persons.get(i).getId() == id) {
							persons.add(new Employee(persons.get(i), salary, date, dpt));
							persons.remove(persons.get(i));
							break;
						}
					}

				} else if (getLineText[0].equals("RegularEmployee")) {
					id = Integer.parseInt(getLineText[1]);
					RegularEmployee regularEmployee = null;
					double perfScore = Double.valueOf(getLineText[2]);
					for (int i = 0; i < persons.size(); i++) {
						if (persons.get(i) instanceof Employee && persons.get(i).getId() == id) {
							regularEmployee = new RegularEmployee((Employee) persons.get(i), perfScore);
							persons.add(regularEmployee);
							persons.remove(persons.get(i));
							break;
						}
					}
				} else if (getLineText[0].equals("Customer")) {
					id = Integer.parseInt(getLineText[1]);
					ArrayList<Product> custProducts = new ArrayList<Product>();
					for (int i = 2; i < getLineText.length; i++) {
						for (int j = 0; j < products.size(); j++) {
							if (products.get(j).getProductName().equals(getLineText[i])) {
								custProducts.add(products.get(j));
							}
						}
					}
					for (int i = 0; i < persons.size(); i++) {
						if (persons.get(i).getId() == id) {
							persons.add(new Customer(persons.get(i), custProducts));
							persons.remove(persons.get(i));
							break;
						}
					}
				} else if (getLineText[0].equals("SalesEmployee")) {
					id = Integer.parseInt(getLineText[1]);
					ArrayList<Product> saleProducts = new ArrayList<Product>();
					for (int i = 2; i < getLineText.length; i++) {
						for (int j = 0; j < products.size(); j++) {
							if (products.get(j).getProductName().equals(getLineText[i])) {
								saleProducts.add(products.get(j));
							}
						}
					}
					for (int i = 0; i < persons.size(); i++) {
						if (persons.get(i).getId() == id && persons.get(i) instanceof RegularEmployee
								&& (persons.get(i) instanceof Employee)) {
							persons.add(new SalesEmployee((RegularEmployee) persons.get(i), saleProducts));
							persons.remove(persons.get(i));
							break;
						}
					}
				} else {
					//if there is a space in any line we can catch that error like this
					throw new Exception("The value of any property must not be empty.");
				}
			}
			//We check the RegularEmployees required for a manager from the arraylist and add them to the arrayList owned by the manager.
			for(int i=0; i<persons.size(); i++) {
				if(persons.get(i) instanceof Manager) {
					String mngDptName=((Manager)persons.get(i)).getDepartment().getDepartmentName();
					for(int j=0; j<persons.size(); j++) {
						if(persons.get(j) instanceof RegularEmployee && ((RegularEmployee)persons.get(j)).getDepartment().getDepartmentName().equals(mngDptName)) {
							((Manager)persons.get(i)).addEmployee((RegularEmployee)persons.get(j));
						}
					}
					managerEmployeeSorted((Manager)persons.get(i));
				}
			}
			// we call the necessary raise Salary method for each object. We can use this method because we produce objects as polymorphic.
			for (int i = 0; i < persons.size(); i++) {
				if (persons.get(i) instanceof Manager) {
					((Manager) (persons.get(i))).distributeBonusBudget();
					((Manager) (persons.get(i))).setSalary(((Manager) (persons.get(i))).raiseSalary(0.2));
				}
			}
			for (int i = 0; i < persons.size(); i++) {
				if (persons.get(i) instanceof Developer) {
					((Developer) (persons.get(i))).setSalary(((Developer) (persons.get(i))).raiseSalary(0.23));
				}
			}
			
			//We need to get the first salesEmployee from the persons list
			SalesEmployee maxSalesEmployee=null;
			for (int i = 0; i < persons.size(); i++) {
				if (persons.get(i) instanceof SalesEmployee) {
					maxSalesEmployee=(SalesEmployee)persons.get(i); 
					break;
				}
			}

			for (int i = 0; i < persons.size(); i++) {
				if (persons.get(i) instanceof SalesEmployee) {
					((SalesEmployee) (persons.get(i))).raiseSalary(0.18);
					if(((SalesEmployee)persons.get(i)).getTotalAmountSales()>maxSalesEmployee.getTotalAmountSales()) {
						maxSalesEmployee=(SalesEmployee)persons.get(i);
					}
				}
			}
			//here we find the highest paid salesEmployee and increase their salary.
			maxSalesEmployee.setSalary((maxSalesEmployee.raiseSalary(1000)));
			for (int i = 0; i < persons.size(); i++) {
				if (persons.get(i) instanceof RegularEmployee && !(persons.get(i) instanceof Developer) && !(persons.get(i) instanceof SalesEmployee)) {
					((RegularEmployee) (persons.get(i))).setSalary(((RegularEmployee) (persons.get(i))).raiseSalary(0.3));

				}
			}
			
			//According to the necessary conditions, we can print it into the output file using the toString method of the classes.
			//I wanted to print to the Console as well.
			for (int i = 0; i < persons.size(); i++) {
				if (persons.get(i) instanceof Manager) {
					System.out.println("************************************************");
					output.println("************************************************");
					System.out.println(((Manager) (persons.get(i))).getDepartment().toString());
					output.println(((Manager) (persons.get(i))).getDepartment().toString());
					System.out.println(((Manager) (persons.get(i))).toString());
					output.println(((Manager) (persons.get(i))).toString());

					for (int j = 0; j < ((Manager) (persons.get(i))).getRegularEmployees().size(); j++) {
						String headerForEmployee = "RegularEmployee"; //Need to keep a title based on your Employee's type
						//If the employee is Developer or Sales, we can keep their project or product in detail and print it at the end.
						String detail = "";
						if (((Manager) persons.get(i)).getRegularEmployees().get(j) instanceof Developer) {
							headerForEmployee = "Developer";
						} else if (((Manager) persons.get(i)).getRegularEmployees().get(j) instanceof SalesEmployee) {
							headerForEmployee = "SalesEmployee";
						}
						System.out.println("\t\t\t" + (j + 1) + ". " + headerForEmployee + "\n"
								+ ((Manager) (persons.get(i))).getRegularEmployees().get(j).toString() + detail);
						output.println("\t\t\t" + (j + 1) + ". " + headerForEmployee + "\n"
								+ ((Manager) (persons.get(i))).getRegularEmployees().get(j).toString() + detail);
					}
					System.out.println();
					output.println();
				}

			}
			System.out.println("\n\n**********************CUSTOMERS************************");
			output.println("\n\n**********************CUSTOMERS************************");
			for (int i = 0; i < persons.size(); i++) {
				if (persons.get(i) instanceof Customer) {
					output.println(((Customer) (persons.get(i))).toString());
					System.out.println(((Customer) (persons.get(i))).toString());
				}

			}
			System.out.println("\n\n**********************PEOPLE************************");
			output.println("\n\n**********************PEOPLE************************");
			for (int i = 0; i < persons.size(); i++) { //Non-worker Persons are printed after checking
				if (!(persons.get(i) instanceof Customer) && !(persons.get(i) instanceof Employee)) {
					System.out.println(persons.get(i).getLongPersonInfo());
					output.println(persons.get(i).getLongPersonInfo());
				}
			}
			// This way we can catch any error and print a message to the console according to that error
		} catch (IndexOutOfBoundsException e) {
			System.out.println("The value of any property must not be empty.");
		} catch (NumberFormatException e) {
			System.out.println("The value of any property must not be empty.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			output.close(); //We need to close the output file
		}

	}

	public static void managerEmployeeSorted(Manager manager) {
		//Here we add the objects to an arrayList in the order (Developer -> Sales Employee -> Regular Employee) and then send this list to the list of the manager in the same department.
		ArrayList<RegularEmployee> sortedRE=new ArrayList<RegularEmployee>(); //Sorted arrayList for Manager's regularEmployee list
		for(int i=0; i<manager.getRegularEmployees().size(); i++) {
			if(manager.getRegularEmployees().get(i) instanceof Developer) {
				sortedRE.add(manager.getRegularEmployees().get(i));
			} //We get the developers first
		}
		for(int i=0; i<manager.getRegularEmployees().size(); i++) {
			if(manager.getRegularEmployees().get(i) instanceof SalesEmployee) {
				sortedRE.add(manager.getRegularEmployees().get(i));
			}//We get the SalesEmployee second
		}
		for(int i=0; i<manager.getRegularEmployees().size(); i++) {
			if(manager.getRegularEmployees().get(i) instanceof RegularEmployee && !(manager.getRegularEmployees().get(i) instanceof Developer) && !(manager.getRegularEmployees().get(i) instanceof SalesEmployee)) {
				sortedRE.add(manager.getRegularEmployees().get(i));
			}//we get the remaining RegularEmployees
		}
		manager.setRegularEmployees(sortedRE); //We send the arrayList created using the set method of the Manager to this method.

	}

	public static Calendar calendar(String date) {
		//This is how we can set the date information received while generating Person
		//First, separate the received String according to "/" and create a list. Then we can set the Calendar object by pulling the elements from this list.
		String dateStr[] = date.split("/");
		int dateInt[] = new int[3];
		for (int i = 0; i < dateInt.length; i++) {
			dateInt[i] = Integer.parseInt(dateStr[i]);
		}
		Calendar calendar = new GregorianCalendar();
		calendar.set(dateInt[2], dateInt[1] - 1, dateInt[0]);
		return calendar; //The set calendar object is returned
	}

}
