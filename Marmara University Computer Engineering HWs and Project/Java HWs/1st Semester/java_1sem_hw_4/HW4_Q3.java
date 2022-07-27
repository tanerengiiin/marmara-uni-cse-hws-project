import java.util.Scanner; // Scanner must be imported

public class HW4_Q3 {
	// This is the file for the Github Repository.
	// This program gives a diamond-shaped output according to the letter received from the user.
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in); // Scanner must be defined.
		System.out.print("Enter a Letter: ");
		String temp=input.next(); // An input is taken from the user
		char letter=temp.charAt(0); // To convert to char, the first digit of the input is taken
		if(temp.length()>1) { // If the entered input is more than 1 digit, it is invalid.
			System.out.println("Invalid Input !");
		}else if((letter>='A' && letter<='Z') || (letter>='a' && letter<='z')){ // The operation is valid if the input input is in the range of A and Z or a and z.
			temp=temp.toUpperCase(); // If the user enters lower case it is used to convert it to upper case.
			letter=temp.charAt(0); // To convert to char, the first digit of the input is taken
			if(letter=='A') { 
				System.out.println("A"); // If the input is A or a, directly A is printed.
			}else {
				printDiamond(constructDiamond(letter)); // If the input is not A or a, the operation is executed.
			}
		}else {
			System.out.println("Invalid Input !"); // If it does not provide the above values, it is still an invalid input.
		}
		
		
	}
	
	public static char[][] constructDiamond (char letter){ // this method returns an array based on the value it receives
		char[][] diamondShape=new char[(letter-'A')*2+1][(letter-'A')*2+1]; //for the length of row and col
		for(int i=0; i<(letter-'A')+1; i++) { // first rotate i to the top base of the diamond
			for(int j=0; j<(letter-'A')*2+1; j++) {
				if(i+j==letter-'A') {
					diamondShape[i][j]=(char)('A'+i); //If the condition is met, a letter is written to the specified place in the array.
				}else if(i+j==letter-'A'+2*i) {
					diamondShape[i][j]=(char)('A'+i);
				}else {
					diamondShape[i][j]='.'; //if the conditions are not met, the point is put.
				}
			}
		}
		
		for(int i=(letter-'A')-1; i>=0; i--) { //then i is rotated starting from the top base of the diamond
            for(int j=(letter-'A')*2; j>=0; j--) {
                if(i+j==letter-'A') {
                	diamondShape[(letter-'A')*2-i][j]=(char)('A'+i);
                }else if(i+j==letter-'A'+2*i) {
                	diamondShape[(letter-'A')*2-i][j]=(char)('A'+i);
                }else {
                	diamondShape[(letter-'A')*2-i][j]='.';
                }
            }
        }
		
		return diamondShape;
		
	}
	
	public static void printDiamond (char[][] diamond) { //this method prints all the row and col of the array it receives
		for(int i=0; i<diamond[0].length; i++) { //diamond[0] = column
			for(int j=0; j<diamond[1].length; j++) { // diamond[1] = row
				System.out.print(diamond[i][j]); //Prints from (0,0) to the end
			}
			System.out.println(); // to move to the next line after a line is finished
		}
	}
}
