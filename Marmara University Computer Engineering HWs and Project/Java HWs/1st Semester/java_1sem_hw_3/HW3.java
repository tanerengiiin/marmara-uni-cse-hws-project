import java.util.Scanner;

public class HW3 {
	public static void main(String[] args) {
		// This is the file for the Github Repository.
		// The purpose of this program is to show some shapes in the coordinate system according to the data received from the user.
		// The program is written in methods for convenience.
		// Lowercase letters are used for inputs. For rectangle and triangle (a,b), (c,d), (e,f) are accepted as points in the coordinate system.
		// For others, it represents the location of the numbers to be used in the equation.
		
		Scanner input=new Scanner(System.in); // Scanner is defined.
		
		while(true) { // The user is prompted to choose.
			//The actions that can be selected are shown and the user is expected to choose.
			System.out.print("1. Line\n2. Triangle\n3. Rectangle\n4. Parabola\n5. Circle\n6.Exit\n");
			int choice=input.nextInt();
			//An operation is executed according to the selection.
			if(choice==1) {
				System.out.print("Line formula is y = ax + b\nPlease enter the coefficients a and b: ");
				int a=input.nextInt();
				int b=input.nextInt();
				line(a,b); // The line method is called.
			}else if(choice==2) {
				System.out.print("For triangle, we need the coordinates of the points for three vertices.\nPlease enter the coordinates of 3 vertices a, b, c, d, e, f: ");
				int a=input.nextInt();
				int b=input.nextInt();
				int c=input.nextInt();
				int d=input.nextInt();
				int e=input.nextInt();
				int f=input.nextInt();
				triangle(a,b,c,d,e,f); //The triangle method is called.
			}else if(choice==3) {
				System.out.print("For rectangle, we need the coordinates of the points for three vertices.\nPlease enter the coordinates of 3 vertices a, b, c, d, e, f: ");
				int a=input.nextInt();
				int b=input.nextInt();
				int c=input.nextInt();
				int d=input.nextInt();
				int e=input.nextInt();
				int f=input.nextInt();
				rectangle(a,b,c,d,e,f); //The rectangle method is called.
			}else if(choice==4) {
				System.out.print("Parabola formula is y = ax^2 + bx + c\nPlease enter the coefficients a, b and c: ");
				int a=input.nextInt();
				int b=input.nextInt();
				int c=input.nextInt();
				parabola(a,b,c); //The parabola method is called.
			}else if(choice==5) {
				System.out.print("Circle formula is (x-a)^2 + (y-b)^2 = r^2\nPlease enter the coefficients a, b and r: ");
				int a=input.nextInt();
				int b=input.nextInt();
				int r=input.nextInt(); // r = radius
				circle(a,b,r); //The circle method is called.
			}else if(choice==6) {
				System.exit(1); // exit the program
			}else {
				System.out.println("Out of range."); //If the user has entered a value other than the specified values, it warns the user.
			}
		}
	}
	public static void rectangle( int a, int b, int c, int d,int e, int f) { // The rectangle method is defined.
		//According to the midpoint of the rectangle, the other point that cannot be given is found.
		int g=c+e-a; 
		int h=d+f-b;
		boolean isRectangle;
		//The conformity of the given points to the rectangular shape is checked.
		//If it is not suitable, the user is warned.
		if(a==c || a==e) {
			if(b==f || d==b) {
				isRectangle=true;
			}else {
				isRectangle=false;
				System.out.println("Rectangles do not form with these coordinates.");
			}
		}else if(b==d || b==f) {
			if(a==c || a==e) {
				isRectangle=true;
			}else {
				isRectangle=false;
				System.out.println("Rectangles do not form with these coordinates.");
			}
		}else {
			double m2=(f-b)/(e-a);
			double m1=(d-b)/(c-a);
			if(m1*m2==-1 ) {
				isRectangle=true;
			}else {
				isRectangle=false;
				System.out.println("Rectangles do not form with these coordinates.");
			}
		}
		
		while(isRectangle) {
			//The coordinate system is drawn within certain limits.
			for(int y=10; y>=-11; y--) {
				for(int x=-10; x<=11; x++) {
					//(x1,y1) , (x2,y2) => (y2-y1)*(x-x1)=(y-y1)*(x2-x1) -> equation of line
					if(y==0) {
						//Since the rectangle is drawn by the line equation, the line equations are limited within the given points.
						if(y<=Math.max(Math.max(b, d), Math.max(f,h)) && y>=Math.min(Math.min(b, d), Math.min(f,h))) {
							if(x<=Math.max(Math.max(a, c), Math.max(e,g)) && x>=Math.min(Math.min(a, c), Math.min(e,g))) {
								// Equations are written according to the formula in line 96.
								//* is printed for values ​​that satisfy the correct equation
								if((d-b)*(x-a)==(y-b)*(c-a)) {
									System.out.print("*");
								}else if((f-b)*(x-a)==(y-b)*(e-a)) {
									System.out.print("*");
								}else if((h-d)*(x-c)==(y-d)*(g-c)) {
									System.out.print("*");
								}else if((h-f)*(x-g)==(y-h)*(g-e)) { 
									System.out.print("*");
								}else if(x==0) {
									System.out.print("|"); //to plot the y-axis
								}else if(x==11) {
									System.out.print("x"); //to specify the x-axis
								}else {
									System.out.print("-"); //to plot the x-axis
								}
							}else if(x==0) {
								System.out.print("|");
							}else if(x==11) {
								System.out.print("x");
							}else {
								System.out.print("-");
							}
						}else if(x==0) {
							System.out.print("|");
						}else if(x==11) {
							System.out.print("x");
						}else {
							System.out.print("-");
						}
					}else if(y<=Math.max(Math.max(b, d), Math.max(f,h)) && y>=Math.min(Math.min(b, d), Math.min(f,h))) {
						if(x<=Math.max(Math.max(a, c), Math.max(e,g)) && x>=Math.min(Math.min(a, c), Math.min(e,g))) {
							if((d-b)*(x-a)==(y-b)*(c-a)) {
								System.out.print("*");
							}else if((f-b)*(x-a)==(y-b)*(e-a)) {
								System.out.print("*");
							}else if((h-d)*(x-c)==(y-d)*(g-c)) {
								System.out.print("*");
							}else if((h-f)*(x-g)==(y-h)*(g-e)) { 
								System.out.print("*");
							}else if(x==0) {
								System.out.print("|");
							}else if(x==11) {
								System.out.print("x");
							}else {
								System.out.print(" ");
							}
						}else if(x==0) {
							System.out.print("|");
						}else {
							System.out.print(" ");
						}
					}else if(x==0) {
						if(y==10) {
							System.out.print("y"); //to specify the x-axis
						}else {
							System.out.print("|");
						}
					}else {
						System.out.print(" "); //for spaces in the coordinate system
					}
				}
				System.out.println(""); //to go to the bottom line
			}
			isRectangle=false; //to exit the rectangular drawing loop
		}
		
	}
	public static void triangle(int a, int b, int c, int d, int e, int f) { // The triangle method is defined.
		//The coordinate system is drawn within certain limits.
		//(x1,y1) , (x2,y2) => (y2-y1)*(x-x1)=(y-y1)*(x2-x1) -> equation of line
		for(int y=10; y>=-11; y--) {
			for(int x=-10; x<=11; x++) {
				if(y==0) {
					//Since the triangle is drawn by the line equation, the line equations are limited within the given points.
					if(y<=Math.max(Math.max(b, d), f) && y>=Math.min(Math.min(b, d), f)) {
						if(x<=Math.max(Math.max(a, c), e) && x>=Math.min(Math.min(a, c), e)) {
							// Equations are written according to the formula in line 172.
							//* is printed for values ​​that satisfy the correct equation
							if((d-b)*(x-a)==(y-b)*(c-a)) {
								System.out.print("*");
							}else if((f-b)*(x-a)==(y-b)*(e-a)) {
								System.out.print("*");
							}else if((f-d)*(x-c)==(y-d)*(e-c)) {
								System.out.print("*");
							}else if(x==0) {
								System.out.print("|");  //to plot the y-axis
							}else if(x==11) {
								System.out.print("x"); //to specify the x-axis
							}else {
								System.out.print("-"); //to plot the x-axis
							}
						}else if(x==0) {
							System.out.print("|"); //to plot the y-axis
						}else if(x==11) {
							System.out.print("x"); //to specify the x-axis
						}else {
							System.out.print("-");
						}
					}else if(x==0) {
						System.out.print("|");
					}else if(x==11) {
						System.out.print("x");
					}else {
						System.out.print("-");
					}
				}else if(y<=Math.max(Math.max(b, d), f) && y>=Math.min(Math.min(b, d), f)) {
					if(x<=Math.max(Math.max(a, c), e) && x>=Math.min(Math.min(a, c), e)) {
						if((d-b)*(x-a)==(y-b)*(c-a)) {
							System.out.print("*");
						}else if((f-b)*(x-a)==(y-b)*(e-a)) {
							System.out.print("*");
						}else if((f-d)*(x-c)==(y-d)*(e-c)) {
							System.out.print("*");
						}else if(x==0) {
							System.out.print("|");
						}else if(x==11) {
							System.out.print("x");
						}else {
							System.out.print(" ");
						}
					}else if(x==0) {
						System.out.print("|");
					}else {
						System.out.print(" ");
					}
				}else if(x==0) {
					if(y==10) {
						System.out.print("y"); //to specify the y-axis
					}else {
						System.out.print("|");
					}
				}else {
					System.out.print(" "); //for spaces in the coordinate system
				}
			}
			System.out.println(""); //to go to the bottom line
		}
		System.out.println("\n\n"); 
	}
	public static void circle(int a,int b, int r) { // The circle method is defined.
		//The coordinate system is drawn within certain limits.
		for(int y=10; y>=-11; y--) {
			for (int x=-10; x<=11; x++) {
				if (y==0) {
					//* is set for points satisfying the circle equation.
					if(((x-a)*(x-a))+((y-b)*(y-b))==r*r) {
						System.out.print("*");
					}else if(x==0) {
						System.out.print("|"); //to plot the y-axis
					}else if(x==11) {
						System.out.print("x"); //to specify the x-axis
					}else {
						System.out.print("-"); //to plot the x-axis
					}
				}else if(((x-a)*(x-a))+((y-b)*(y-b))==r*r) {
					System.out.print("*");
				}else if(x==0) {
					if(y==10) {
						System.out.print("y"); //to specify the y-axis
					}else {
						System.out.print("|"); 
					}
				}else {
					System.out.print(" "); //for spaces in the coordinate system
				}
			}
			System.out.println(""); //to go to the bottom line
		}
		System.out.println("\n\n");
	}
	public static void parabola(int a,int b, int c) {  // The parabola method is defined.
		//The coordinate system is drawn within certain limits.
		for(int y=10; y>=-11; y--) {
			for (int x=-10; x<=11; x++) {
				if (y==0) {
					//* is set for points satisfying the parabola equation.
					if(a*x*x+b*x+c==0) {
						System.out.print("*");
					}else if(x==0) {
						System.out.print("|");
					}else if(x==11) {
						System.out.print("x"); //to specify the x-axis
					}else {
						System.out.print("-"); //to plot the x-axis
					}
				}else if((a*x*x+b*x+c)==y) {
					System.out.print("*");
				}else if(x==0) {
					if(y==10) {
						System.out.print("y"); //to specify the y-axis
					}else {
						System.out.print("|"); //to plot the y-axis
					}
				}else {
					System.out.print(" ");  //for spaces in the coordinate system
				}
			}
			System.out.println(""); //to go to the bottom line
		}
		System.out.println("\n\n");
	}
	public static void line(int a, int b) {  // The line method is defined.
		//The coordinate system is drawn within certain limits.
		for (int y = 10; y >= -11;y--) {
			for (int x = -10; x <= 11; x++) {
				if (y == 0) {
					//* is set for points satisfying the line equation.
					if ((a * x + b) == 0)
						 System.out.print("*");
					else if (x == 0)
						 System.out.print("|"); //to plot the y-axis
					else if (x == 11)
						 System.out.print("x");
					else
					    System.out.print("-"); //to plot the x-axis
				} 
				else if ((a * x + b) == y)
					System.out.print("*");
				else if (x == 0) {
					if (y == 10)
					    System.out.print("y"); //to specify the y-axis
					else
					    System.out.print("|");
				}
				else
				    System.out.print(" "); //for spaces in the coordinate system
			}
			System.out.println(""); //to go to the bottom line
		}
		System.out.println("\n\n");
	 }
}
