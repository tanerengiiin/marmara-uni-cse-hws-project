import java.util.Scanner; //In order to use the scanner, we need to import it.

public class HW2_P1 {
	// This is the file for the Github Repository.
	public static void main(String[] args) {
		
		/* This program checks the first 9 digits of the entered ISBN number and prints the last digit.
			After the user's first 9 digits are multiplied, it is divided by 11.
			If the remainder is 10, the last digit of the ISBN is X.
			ISBN-10 Number=> ABCDEFKLMN
		 */
		Scanner input=new Scanner(System.in); //The scanner must be defined in order to receive the data from the user.
		System.out.print("Enter the first 9 digits of an ISBN as integer: ");
		int isbnNumber = input.nextInt(); //The ISBN number is taken
		
		//Use the remainder to find the first digit from the right and divide by 10 to decrease the digit to the right
		int M = isbnNumber%10; 
		isbnNumber /=10;
		int L = isbnNumber%10;
		isbnNumber /=10;
		int K = isbnNumber%10;
		isbnNumber /=10;
		int F = isbnNumber%10;
		isbnNumber /=10;
		int E = isbnNumber%10;   
		isbnNumber /=10;		  
		int D = isbnNumber%10;
		isbnNumber /=10;
		int C = isbnNumber%10;
		isbnNumber /=10;
		int B = isbnNumber%10;
		isbnNumber /=10;
		int A = isbnNumber%10;
		
		int checkSum=(A*1+B*2+C*3+D*4+E*5+F*6+K*7+L*8+M*9)%11; //Calculating the last digit 
		char N; //The last digit is assigned a char value
		
		if(checkSum==10) 
			N='X'; //If the remainder is 10, N equals X
		else
			N=(char)(checkSum+'0'); //Get the char value of the number
		
		System.out.println("The ISBN-10 number is "+A+B+C+D+E+F+K+L+M+N);
		
		
	}

}
