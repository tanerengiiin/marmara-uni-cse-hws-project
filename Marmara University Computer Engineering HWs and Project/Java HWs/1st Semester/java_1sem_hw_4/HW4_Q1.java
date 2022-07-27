import java.util.Scanner; // Scanner must be imported

public class HW4_Q1 {
	public static void main(String[] args) {
		// This is the file for the Github Repository.
		// This program calculates the invoice amount per share of flats according to the entered values.
		Scanner input= new Scanner(System.in); //Scanner must be defined
		int numberOfFlats=input.nextInt(); // the number of flats is taken from the user
		double[] areaOfFlats=new double[numberOfFlats];
		for(int i=0; i<numberOfFlats; i++) {
			areaOfFlats[i]= input.nextDouble(); //Entries equal to the number of flats are taken and written into the array sequentially.
		}
		double totalBill=input.nextDouble(); //total invoice amount is taken
		printBills(calculateTheInvoice(areaOfFlats, totalBill)); //methods are called
		
	}
	 public static double[] calculateTheInvoice (double[] flats, double totalBill) { //define a method that returns an array
		 double[] billOfFlats= new double[flats.length];
		 double totalArea=0;
		 for(int i=0;i<flats.length; i++) {
			 totalArea+=flats[i]; //add all the elements of the flats array to find the total area
		 }
		 
		 for(int i=0; i<flats.length; i++) {
			 //The invoice amount per share of each flat is calculated and written into the array.
			 billOfFlats[i]=((totalBill*0.3)/(flats.length))+((totalBill*0.7)*(flats[i]/totalArea));
		 }
		 return billOfFlats; //method returns this array
	 }
	 
	 public static void printBills (double[] bills) {
		 for(int i=0; i<bills.length; i++) { //the amount per flat is printed
			 System.out.println("Flat #"+(i+1)+": "+((int)(bills[i]*100))/100.0); //this method can be applied to show two digits after comma 
		 }
	 }
}
