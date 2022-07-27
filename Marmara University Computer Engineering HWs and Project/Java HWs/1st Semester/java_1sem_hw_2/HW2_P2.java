import java.util.Scanner; //In order to use the scanner, we need to import it.

public class HW2_P2 {
	// This is the file for the Github Repository.
	public static void main(String[] args) {
		
		// This program shows which day of the week it is according to the given date values.
		// formula for calculating the number of the day=> d = (f + (26*(m+1)/10) + y + (y/4) + (c/4) + 5*c) %7
		// d= day of the week(0,1,2,3,4,5,6)   m= month (1,2,3..11,12) (1 => 13 2=> 14)   f= day of the month 
		// y= year of the century        c= century
		Scanner input=new Scanner(System.in); //The scanner must be defined in order to receive the data from the user.
		
		//The date values are taken
        System.out.print("Enter year (e.g. 2012): ");
        int year=input.nextInt(); 
        System.out.print("Enter month (e.g. 1-12): ");
        int month=input.nextInt();
        System.out.print("Enter the day of the month (e.g. 1-31): ");
        int dayOfMonth=input.nextInt();
        
        //If the month is 1, this is calculated as 13
        if(month==1) {
            month=13;
            year--;
        }else if(month==2) { //If the month is 2, this is calculated as 14
            month=14;
            year--;
        }
        
        int century=year/100; // Calculate century
        int yearOfCentury=year%100; // Calculate the year of the century
        int dayOfWeek=(dayOfMonth+((26*(month+1))/10)+yearOfCentury+((yearOfCentury+century)/4)+5*century)%7;
        String nameOfDay=""; //String is defined to keep the name of the day
        
        switch(dayOfWeek) { //A value is assigned for nameOfDay based on the calculated number
            case(0):
            	nameOfDay="Saturday";
                break;
            case(1):
            	nameOfDay="Sunday";
                break;
            case(2):
            	nameOfDay="Monday";
                break;
            case(3):
            	nameOfDay="Tuesday";
                break;
            case(4):
            	nameOfDay="Wednesday";
                break;
            case(5):
            	nameOfDay="Thursday";
                break;
            case(6):
            	nameOfDay="Friday";
                break;
            
        }
        System.out.println("Day of the week is "+nameOfDay); 
	}

}
