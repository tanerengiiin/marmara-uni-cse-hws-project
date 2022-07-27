package application;

public class Cell { //a cell object is created
	// This is the file for the Github Repository.
	private int id; //a cell is given an id
	private String type; // type is given to a cell
	private String property; //a cell is given a property
	
	public Cell(int id, String type, String property) {
		this.id=id; //assignments are made
		this.type=type;
		this.property=property;
	}
	public String getProperty() {
		return property; //property is taken
	}
	public String getType() {
		return type; //type is taken
	}
}
