import java.util.Scanner; //In order to use the scanner, we need to import it.

public class HW1_P2 {
	// This is the file for the Github Repository.
	public static void main(String[] args) {
		
		//This program converts the date given in days from the user to Year: <years>, Month: <months>,Day: <days> format.
		
		Scanner input=new Scanner(System.in); //The scanner must be defined in order to receive the data from the user.
		
		System.out.print("Number of days: "); 
		int totalDays=input.nextInt(); //The date given in days is taken from the user.
		
		int year=totalDays/365; //Calculating the year, by integer division
		int month=(totalDays-(year*365))/31; //Calculating the month, by integer division
		int day=(totalDays-(year*365))%31; //The date given as days is reduced to one year, then the remainder of this number from 31 gives us the number of days.

		System.out.println("Year: "+year+", Month: "+month+", Day: "+day); 
		
	}

}
