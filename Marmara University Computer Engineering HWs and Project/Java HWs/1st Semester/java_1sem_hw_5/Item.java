// This is the file for the Github Repository.
public class Item { //Item class is created.
	private int id; // id is defined as private
	public static int numberOfItems=0; // numberOfItems is defined as private and static
	// static defined expressions do not change
	public Item(int id) { // Constructor is defined
		this.id=id; //Item's id is matched to the id sent to the constructor. This is done using this.
		numberOfItems++; // Static variable is incremented
	}
	
}
