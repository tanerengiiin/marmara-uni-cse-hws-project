import java.util.Scanner; //In order to use the scanner, we need to import it.

public class HW1_P1 {
	// This is the file for the Github Repository.
	public static void main(String[] args) {
		
		//This program displays the cost of the way to go to the user according to the data from the user.
		
		Scanner input=new Scanner(System.in); //The scanner must be defined in order to receive the data from the user.
		
		System.out.print("Enter the driving distance: ");
		double drivingDistance=input.nextDouble(); //The way of the user to go is taken. 
		
		System.out.print("Enter miles per gallon: ");
		double milesPerGallon=input.nextDouble(); //How many miles per gallon is taken.
		
		System.out.print("Enter price per gallon: ");
		double pricePerGallon=input.nextDouble(); //The price of a gallon of fuel is taken.
		
		double cost=(drivingDistance/milesPerGallon)*pricePerGallon; //The cost is calculated according to given data. 
		cost=(int)(cost*100)/100.0; //This method can be used to show two digits after the comma.
		System.out.println("The cost of driving is $"+cost);

	}

}
