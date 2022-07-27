import java.util.Scanner; //In order to use the scanner, we need to import it.

public class HW1_P3 {
	// This is the file for the Github Repository.
	public static void main(String[] args) {
		 
		/* 	
		 	This program calculates the compound monthly interest according to the data it receives from the user and displays it to the user.
		 	These formulas are used to calculate the compound monthly interest => f=p*(1+ (r/1200))^t    i=f-p     
			p=initialPrincipal r=annualInterestRate t=numberOfTimePeriods f=finalBalance i=totalCompoundInterest 
		*/
		
		Scanner input=new Scanner(System.in); //The scanner must be defined in order to receive the data from the user.
		
		System.out.print("Enter initial principal amount: ");
		double initialPrincipal=input.nextDouble(); //The amount of the initial principal is taken.
		
		System.out.print("Enter annual interest rate (e.g. 9.45): ");
		double annualInterestRate=input.nextDouble(); //The annual interest rate is taken.
		
		System.out.print("Enter number of time periods in months: ");
		int numberOfTimePeriods=input.nextInt(); //Number of time periods in months is taken as an int.
		
		double finalBalance=initialPrincipal*Math.pow(1+(annualInterestRate/1200), numberOfTimePeriods); //Calculating the final balance amount by using first formula in line 9.
		double totalCompoundInterest=finalBalance-initialPrincipal; //Calculating the earning money amount by using second formula in line 9.
		
		double monthlyInterestRate=(int)((annualInterestRate/12)*100)/100.0; //Dividing the annual interest rate by 12 to get the monthly interest rate.
		finalBalance=(int)(finalBalance*100)/100.0; //This method can be used to show two digits after the comma.
		totalCompoundInterest=(int)(totalCompoundInterest*100)/100.0;
		
		System.out.println("Initial principal amount: "+initialPrincipal);
		System.out.println("Monthly interest rate: "+monthlyInterestRate); 
		System.out.println("Total compound interest amount: "+totalCompoundInterest); 
		System.out.println("Final balance amount: "+finalBalance);
		
		
	}

}
