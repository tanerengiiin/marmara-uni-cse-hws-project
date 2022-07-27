#include <stdio.h>

// This is the file for the Github Repository.
// h = height (total number of rows)
// n = number of iterations
// r = current row  &  c = current column
// This program draws triangles according to the number of repetitions entered.


void printTriangle(int h, int n, int r, int c){
	//This method gets the total number of rows, the number of repetitions, the number of rows currently above it, and the number of columns of the triangles to be drawn.
	if(n!=0){ //if the number of repetitions is not 0
		if(r<=h/2){
			//if we have passed half of the triangle to be drawn, a triangle of underscores is created
			if(c<h/2 || c>3*h/2){
				//If the current column count approaches the middle, underscores are drawn.
				printf("_");
			}else{
				//but if it is not within these limits, the function is called again by changing its values.
				printTriangle(h/2,n-1,r,c-h/2);
			}
		}else{
			if(c>h){
				//if the current column is greater than the total number of rows, the function is called again by changing its values
				printTriangle(h/2,n-1,r-h/2,c-h);
			}else if(c<h){
				//if the current column is less than the total number of rows, the function is called again by changing its values
				printTriangle(h/2,n-1,r-h/2,c);
			}else{
				//if not within these limits, underscores are drawn
				printf("_");
			}
		}
	}else{ //if the number of repetitions is 0
		if(h-r >=c || c-h >=r){
			//The number of underlines decreases as the current line count increases. the number of 1s increases accordingly.
			printf("_");
		}else{
			printf("1");
		}
	}
	
	
}
void triangleArea(int h, int n,int r, int c){
	//functions are called recursively to work like a loop for the field to be created
	if(r<h+1){
		//our condition value to stop calling functions recursively. It is run until it exceeds the total number of rows.
		printTriangle(h,n,r,c); //First draw a triangle
		if(c==2*h-1){
			//If the current column is in the last column, it is moved to the bottom row, the current row value is increased by 1 and the current column is set to 1 again.
			printf("\n");
			triangleArea(h,n,r+1,1);
		}else{
			//If the last column is not reached, the current row is kept constant, and the current column number is increased one by one and the function is called again.
			triangleArea(h,n,r,c+1);
		}
	}
}


int main() {
	int n; //keeps the number of repetitions
	printf("Enter the number of iterations: "); 
	scanf("%d",&n); //Repetition count is taken from the user
    triangleArea(32,n,1,1); //Triangles are drawn according to the number of repetitions.
	return 0;
}
