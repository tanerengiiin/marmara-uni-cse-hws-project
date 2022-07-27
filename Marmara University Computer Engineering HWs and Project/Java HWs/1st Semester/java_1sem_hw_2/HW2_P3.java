import java.util.Scanner; //In order to use the scanner, we need to import it.

public class HW2_P3 {
	// This is the file for the Github Repository.
	public static void main(String[] args) {
		
		// This program calculates the tax amount based on the entered year and income values.
		
		Scanner input = new Scanner(System.in); //The scanner must be defined in order to receive the data from the user.
		
		int year = input.nextInt(); //The year is taken
		double income = input.nextDouble(); //The income is taken
		
		/*The program only calculates for 2017, 2018, 2019, 2020 years, so we check the year by using if-else statements
		 and we check the income value if it is entered less than zero. */
		if(year<2017 || year>2020) {
			System.out.println("Undefined year value");
			System.exit(1);
		}else if(income<=0) {
			System.out.println("Income must be > 0");
			System.exit(1); 
		}
		
		double taxAmount=0; // Define taxAmount
		
		// An if statement is selected according to income and year and tax amount is calculated
		if(year==2020) {
			if(income<22000) { 
				taxAmount=income*0.15;
			}else if(income <49000) {
				taxAmount=3300+(income-22000)*0.2;
			}else if(income <180000) {
				taxAmount=8700+(income-49000)*0.27;
			}else if(income <600000) {
				taxAmount=44070+(income-180000)*0.35;
			}else if(income >= 600000) {
				taxAmount=191070+(income-600000)*0.4;
			}
		}else if(year ==2019) {
			if(income<18000) {
				taxAmount=income*0.15;
			}else if(income <40000) {
				taxAmount=2700+(income-18000)*0.2;
			}else if(income <148000) {
				taxAmount=7100+(income-40000)*0.27;
			}else if(income <500000) {
				taxAmount=36260+(income-148000)*0.35;
			}else if(income >= 600000) {
				taxAmount=159460+(income-500000)*0.4;
			}
		}else if(year ==2018) {
			if(income<14800) {
				taxAmount=income*0.15;
			}else if(income <34000) {
				taxAmount=2200+(income-14800)*0.2;
			}else if(income <120000) {
				taxAmount=6060+(income-34000)*0.27;
			}else if(income >=120000) {
				taxAmount=29280+(income-120000)*0.35;
			}
		}else if(year ==2017) {
			if(income<13000) {
				taxAmount=income*0.15;
			}else if(income <30000) {
				taxAmount=1950+(income-13000)*0.2;
			}else if(income <110000) {
				taxAmount=5350+(income-30000)*0.27;
			}else if(income >=110000) {
				taxAmount=26950+(income-110000)*0.35;
			}
		}
		
		double realTaxRate = (int)((taxAmount/income)*10000)/100.0; //Calculating real tax rate and income after tax.
		double incomeAfterTax = (int)((income-taxAmount)*100)/100.0; 
		
		taxAmount = (int)(taxAmount*100)/100.0; // We can get 2 digits after point with this method
	
		System.out.println("Income: "+income);
		System.out.println("Tax amount: "+taxAmount); //Printing the values that we found.
		System.out.println("Income after tax: "+incomeAfterTax);
		System.out.println("Real tax rate: "+realTaxRate+"%");
	}

}
