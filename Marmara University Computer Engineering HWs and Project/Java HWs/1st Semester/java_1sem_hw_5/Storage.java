// This is the file for the Github Repository.
public class Storage { // Storage class is defined
	private int capacity; // various variables are defined
	private Item[] items; 
	
	public Storage(int capacity) { // Storage's constructor is defined
		this.capacity=capacity; //Storage's variables are matched to the variables sent to the constructor. This is done using this.
		this.items = new Item[0]; //first defined as empty to create the Item class 
	}
	
	public void addItem(Item item) { // The addItem method adds the sent item to the end of the items array.
		Item[] temp =new Item[items.length+1]; // a temp array is defined as one more than the length of the items array
		System.arraycopy(items, 0, temp, 0, items.length); //Each item in the temp array is synchronized to each item in the items array using arraycopy
		
		temp[temp.length-1]=item; // The item sent to the method is synchronized to the last item of the temp array
		items=temp; // finally the items array's reference is set to temp's reference
	}
	
	public int getItemsLength() {
		return items.length; // Defined to be able to use the length of the items array
	}
}
