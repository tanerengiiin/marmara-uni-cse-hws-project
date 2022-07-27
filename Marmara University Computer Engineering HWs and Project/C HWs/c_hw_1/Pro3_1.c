#include <stdio.h>

// This is the file for the Github Repository.
// This program gives the total number of ears of the bunnies. There are 3 ears for double ones, while there are 2 ears for single ones.

int n; //A general value is kept that will not change to start at 0 and move forward.
int bunnyEars2(int numOfBunnies){
    if (numOfBunnies == 0){
        return 0; //0 is returned if the number of bunnies is 0
    } else if (numOfBunnies % 2 == 0) {
        //If it is an even number, add 3 with the rabbit numbers in the previous rows.
        return 3 + bunnyEars2(numOfBunnies-1);    
    }else{
        //if it is an odd number add 2 with the rabbit numbers in the previous rows
        return 2 + bunnyEars2(numOfBunnies-1);
    }
}
void printBunnyEars(int x){
    //Function created to print the number of ears of rabbits in rows from 0 to the entered number
    printf("bunnyEars2(%d) -> %d\n",n-x,bunnyEars2(n-x));
    if(x>0){ //the function is called again until the entered number
        printBunnyEars(x-1);
    }
}
int main() {
    printf("Please enter the number of lines (n=): ");
    scanf("%d",&n); //a number is taken from the user
    printBunnyEars(n); //the received number is sent to the printBunnyEars function
    return 0;
}
