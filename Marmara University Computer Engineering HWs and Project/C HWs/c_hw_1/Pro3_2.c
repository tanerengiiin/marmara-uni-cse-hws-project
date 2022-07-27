#include <stdio.h>

// This is the file for the Github Repository.
// The purpose of this program is to add the digits of the entered number according to the repetition number and make it a single digit at the end.
int sumDigit(int number){ //function that sums each digit
    if (number == 0){
           return 0; //If the number the function takes is 0, it returns 0 directly
    } //where the function is run repeatedly until the value it takes is 0.
    return (number % 10 + sumDigit(number / 10));
} 
//First take the remainder from the division by 10 to get the last digit
//then the number is divided by 10 and the last digit is discarded and sent back to the function.4

int superDigit(int number,int repetition){
    //the sum of the digits of the number is multiplied by the repeat number and the sum of the digits of the repeated number is found
    int repetitiveNum=repetition*sumDigit(number);
    if(repetitiveNum>9){
        //if the number found is greater than 9, it could not be reduced to a single digit and this number is sent back to the function
        return sumDigit(repetitiveNum);
    }
    return repetitiveNum; //one-digit number is returned
}
void printNum(int number,int repetition){
    //Since the repeated number is written side by side in the printing process, the repeating function is used here as well.
    printf("%d",number); //the number is printed
    if(repetition>1){
        //the function is called again if the number of repetitions is greater than 1
        printNum(number,repetition-1);
    }
}

int main() {
    int n; //keeps the number
    int k; //keeps the number of repetitions
    
    printf("Please enter a number (n=) : ");
    scanf("%d",&n); //get number from user
    printf("Please enter repetition factor (k=) : ");
    scanf("%d",&k); //Repetition count is taken from the user
    
    printf("Super digit of number ");
    printNum(n,k); //received numbers are sent to the function
    printf(" is %d.",superDigit(n,k));
    return 0;
}
