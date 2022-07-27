// This is the file for the Github Repository.
import java.util.Random;

public class Employee { // Employee class is defined 
	private int id; // private variables are defined	
	private String name;
	private String surname; 
	private int workHour; 
	private int speed; 
	private Item items[]; //generated items are kept in Items array
	private Payroll payroll; // Worker generated with Payroll can be sent to the payroll class.
	
	public Employee(int id, String name, String surname, int workHour, int speed) { // Employee's constructor is defined
		this.id=id; //Employee's variables are matched to the variables sent to the constructor. This is done using this.
		this.name=name;
		this.surname=surname;
		this.workHour=workHour;
		this.speed=speed;
	}
	
	public Item[] startShift() { // The startShift method assigns a random id to the produced items and sends these items into the items array.
		Random randomId= new Random();
		items=new Item[this.workHour*this.speed];
		for(int i=0; i<items.length; i++) { //The Item class is used for each member of the items one by one.
			items[i]=new Item(randomId.nextInt(100)+1); // Generates a random integer between 1 and 100
		}
		return items; 
	}
	
	public Payroll endShift() { //The endShift method allows the employee's information to be sent to the Payroll class.
		return payroll=new Payroll(this.workHour, this.workHour*this.speed);
	}
	
	public int getId() { // getId method is used for the Factory class
		return this.id; //returns the id of the employee
	}
	
	public String toString() {  // Returns information about the class as a string
		return "This is the employee with id "+id +" speed "+speed +". The work hour is "+workHour +" and the produced item count is "+ speed*workHour+".";
	}
}
