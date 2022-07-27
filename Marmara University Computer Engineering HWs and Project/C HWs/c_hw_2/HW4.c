#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <math.h>
#include <time.h>
#define SIZE 10 //SIZE is defined and size of all other arrays can be changed from here

// This is the file for the Github Repository.
// This program creates a metro system and draws lines into this metro system and adds stations to these lines. 
// It finds the shortest path for the person to go from one point to another and prints it on the screen.

struct MetroStation { //MetroStation struct is created
	char name[20]; // name of MetroStation
	double x; // x coordinate of MetroStation
	double y; // y coordinate of MetroStation
};
typedef struct MetroStation MetroStation; //To write just MetroStation instead of typing struct MetroStation 

struct MetroLine{ //MetroStation struct is created
	char color[20]; // name of MetroLine
	MetroStation MetroStations[SIZE]; // array holding stations on a line
};
typedef struct MetroLine MetroLine; //To write just MetroLine instead of typing struct MetroLine

struct MetroSystem{
	char name[20]; // name of MetroSystem
	MetroLine MetroLines[SIZE]; //array holding metro lines in a metro system
};
typedef struct MetroSystem MetroSystem; //To write just MetroSystem instead of typing struct MetroSystem

//functions are written as prototypes
int equals(MetroStation s1,MetroStation s2);
void addStation(MetroLine *l, MetroStation s);
int hasStation(MetroLine l, MetroStation s);
MetroStation getFirstStop(MetroLine l);
MetroStation getPreviousStop(MetroLine l, MetroStation s);
MetroStation getNextStop(MetroLine l,MetroStation s);
void addLine(MetroSystem *s,MetroLine l);
void printLine(MetroLine l);
void printPath(MetroStation sArr[]);
double getDistanceTravelled(MetroStation sArr[]);
MetroStation findNearestStation(MetroSystem sys, double x,double y);
void getNeighboringStations(MetroSystem, MetroStation,MetroStation[]);
void findPath(MetroStation start, MetroStation finish, MetroStation path[]);
void recursiveFindPath(MetroStation start, MetroStation finish, MetroStation partialPath[],MetroStation bestPath[]);
int contains (MetroStation arr[], MetroStation s);
void copyArray(MetroStation dest[],MetroStation src[]);
void addStationToArray(MetroStation arr[],MetroStation s);

//A metor system named istanbul with an empty array is defined
MetroSystem istanbul = {"istanbul", '\0'}; 

int main(){
	int i;
	double myX=1, myY=2; 
	double goalX=62, goalY=45; 
	
	// define 3 metro lines, 9 metro stations, and an empty myPath
	
	MetroLine red={'\0'}, blue={'\0'}, green={'\0'};
	MetroStation s1, s2, s3, s4, s5, s6, s7, s8, s9;
	MetroStation myPath[SIZE]={'\0'};
	
	strcpy(red.color, "red"); 
	strcpy(blue.color, "blue");
	strcpy(green.color, "green");

	
	strcpy(s1.name, "Haydarpasa"); 		s1.x=0; 	s1.y=0;
	strcpy(s2.name, "Sogutlucesme"); 	s2.x=10; 	s2.y=5;
	strcpy(s3.name, "Goztepe"); 		s3.x=20; 	s3.y=10;
	strcpy(s4.name, "Kozyatagi"); 		s4.x=30; 	s4.y=35;
	strcpy(s5.name, "Bostanci"); 		s5.x=45; 	s5.y=20;
	strcpy(s6.name, "Kartal"); 			s6.x=55; 	s6.y=20;
	strcpy(s7.name, "Samandira"); 		s7.x=60; 	s7.y=40;
	strcpy(s8.name, "Icmeler"); 		s8.x=70; 	s8.y=15;
	
	//Add several metro stations to the given metro lines.
	addStation(&red, s1); addStation(&red, s2); addStation(&red, s3); addStation(&red, s4); addStation(&red, s5); addStation(&red, s8);
	
	addStation(&blue, s2); addStation(&blue, s3); addStation(&blue, s4); addStation(&blue, s6); addStation(&blue, s7);
	
	addStation(&green, s2); addStation(&green, s3); addStation(&green, s5); addStation(&green, s6); addStation(&green, s8);
	
	// Add red, blue, green metro lines to the Istanbul metro system.
	addLine(&istanbul, red);
	addLine(&istanbul, blue);
	addLine(&istanbul, green);
	
	// print the content of the red, blue, green metro lines
	printLine(red);
	printLine(blue);
	printLine(green);
	
	// find the nearest stations to the current and target locations
	MetroStation nearMe = findNearestStation(istanbul, myX, myY);
	MetroStation nearGoal = findNearestStation(istanbul, goalX, goalY);
	
	printf("\n");
	
	printf("The best path from %s to %s is:\n", nearMe.name, nearGoal.name);
	
	// if the nearest current and target stations are the same, then print a message and exit.
	if(equals(nearMe, nearGoal)){
		printf("It is better to walk!\n");
		return 0;
	}
	// Calculate and print the myPath with the minimum distance travelled from start to target stations.
	findPath(nearMe, nearGoal, myPath);
	
	if(strlen(myPath[0].name) == 0)
		printf("There is no path on the metro!\n");
	else{
		printPath(myPath);
	}
	
	return 0;
}

int equals(MetroStation s1,MetroStation s2){
	//this function checks the names of the two stations sent if the names are equal then the function returns 1 otherwise it returns 0
	if(strcmp(s1.name,s2.name)==0){
		return 1;
	}
	return 0;
}

void addStation(MetroLine *l, MetroStation s){
	//With for, the index number is increased until it reaches the empty element in the array, and when the for loop is finished, a station is added to this index.
	int i;
	for(i=0; (*l).MetroStations[i].name[0]!='\0' && i<SIZE-1; i++);
	l->MetroStations[i]=s;
}

int hasStation(MetroLine l,MetroStation s){
	//It checks whether there is a station equal to the sent station by looking at all the elements of the array in the sent line.
	int i;
	int control=0;
	for(i=0; i<SIZE; i++){
		//To do this, the equals method is used.
		if(equals(l.MetroStations[i],s)){
			control++;
			break;
		}
	}
	return control;
}

MetroStation getFirstStop(MetroLine l){
	return l.MetroStations[0]; //Returns null if first element is empty
}

MetroStation getPreviousStop(MetroLine l, MetroStation s){
	//First of all, it finds the index of the sent station in the array in the sent line. It then returns the element one less than this index
	int i;
	MetroStation temp; // the name of a MetroStation is defined as null
	temp.name[0]='\0'; 
	for(i=0; i<SIZE; i++){
		if(equals(l.MetroStations[i],s)){
			break;
		}
	}
	if(i>0){
		temp=l.MetroStations[i-1];
	}
	return temp;
}

MetroStation getNextStop(MetroLine l,MetroStation s){
	//The index number is increased until it finds the sent station in the array on the line. If the index number is not equal to the last element, it is increased by one and the next element in the array is taken.
	int i; //index
	
	MetroStation temp; // the name of a MetroStation is defined as null
	temp.name[0]='\0'; 
	for(i=0; i<SIZE; i++){
		if(equals(l.MetroStations[i],s)){
			break; 
		}
	}
	if(i<SIZE-1){
		temp=l.MetroStations[i+1];
	}
	
	return temp; //an empty station is returned if the index number is equal to the last element
}

void addLine(MetroSystem *s, MetroLine l){
	//The for returns until the color of the line in the array inside the system is empty. If it is empty, for returns and the line sent to the index of this number of this array is sent.
	int i;
	for(i=0; (*s).MetroLines[i].color[0]!='\0' && i<SIZE-1; i++);
	(*s).MetroLines[i]=l;
}

void printLine(MetroLine l){
	printf("Metroline %s:\t",l.color);
	
	int i,j;
	for(i=0; i<SIZE && l.MetroStations[i].name[0]!='\0'; i++);
	//i is incremented until the empty element of the array, then the names of the stations in the array are written with commas until the last 1 element of i
	for(j=0; j<i-1; j++){
		printf("%s, ",l.MetroStations[j].name);
	}
	//then the penultimate element of i . is printed with
	printf("%s.\n",l.MetroStations[i-1].name);
}

void printPath(MetroStation MetroStations[]){
	int i;
	for(i=0; MetroStations[i].name[0]!='\0' && i<SIZE; i++){
		printf("\t%d. %s\n",(i+1),MetroStations[i].name);
	}
}

double getDistanceTravelled(MetroStation sArr[]){
	int i;
	//first find the total number of elements in the array
	for(i=0; sArr[i].name[0]!='\0' && i<SIZE; i++);
	
	if(i<2){ //0.0 is returned if there are less than 2 elements in the array
		return 0.0;
	}
	//if array has more than 2 elements
	double distance=0; //A distance of 0 is defined
	int j; //increment j up to the penultimate element
	for(j=0; j<i-1; j++){
		//The x and y values ??of the current station are subtracted from the x and y values ??of the next station and the absolute value is obtained
		int dx=fabs(sArr[j].x-sArr[j+1].x);
		int dy=fabs(sArr[j].y-sArr[j+1].y);
		distance+=sqrt(pow(dx,2)+pow(dy,2)); //Add the square root of the sum of the squares of these dx and dy values ??to the distance value.
	}//this is the formula between two points: square root(x^2+y^2)=d
	return distance;
}

MetroStation findNearestStation(MetroSystem sys, double x,double y){
	int i,j;
	
	MetroStation nearStation=sys.MetroLines[0].MetroStations[0]; //first element is taken
	//The color of the lines in the system increases until the i is empty and j increases until the station is empty.
	for(i=0; sys.MetroLines[i].color[0]!='\0' && i<SIZE-1; i++){
		for(j=0; sys.MetroLines[i].MetroStations[j].name[0]!='\0' && j<SIZE-1; j++){
			MetroStation ms=sys.MetroLines[i].MetroStations[j]; //stations sync to ms in order
			int xMin=fabs(nearStation.x-x);
			int yMin=fabs(nearStation.y-y);
			int xCur=fabs(ms.x-x);
			int yCur=fabs(ms.y-y);
			//The distance is calculated with the x and y of the current nearest station and the x and y of the next station, whichever distance is lower, is equated to that nearStation. 
			//This continues until the last element and the nearest station is found like this
			if(sqrt(pow(xMin,2)+pow(yMin,2))>sqrt(pow(xCur,2)+pow(yCur,2))){
				nearStation=ms;
			}
			
		}
	}
	return nearStation;
}

void getNeighboringStations(MetroSystem sys, MetroStation s,MetroStation neighboringStations[]){
	int i,j,count=0,max;
	
	for(i=0; sys.MetroLines[i].color[0]!='\0' && i<SIZE; i++){
		for(max=0; max<SIZE && sys.MetroLines[i].MetroStations[max].name[0]!='\0'; max++);
		
		for(j=0; j<max; j++){
			if(strcmp(s.name,sys.MetroLines[i].MetroStations[j].name)==0){
				if(j==0){//if the station is the first element, only the next element is added to the neighboringStations sent
					neighboringStations[count]=sys.MetroLines[i].MetroStations[j+1];
				}else if(j==(max-1)){//if the station is the last element, only the previous element is added to the neighboringStations sent
					neighboringStations[count]=sys.MetroLines[i].MetroStations[j-1];
				}else{
					//if the station is not the last or the first element, only the previous and next elements are added to the sent neighboringStations
					neighboringStations[count]=sys.MetroLines[i].MetroStations[j-1];
					count++;
					neighboringStations[count]=sys.MetroLines[i].MetroStations[j+1];
				}
				count++;
			}
		}
	}
}


int contains (MetroStation arr[], MetroStation s) { 
    int i;
    //It is checked whether there is a sent station in the sent array.
    for (i = 0; i <SIZE && arr[i].name[0] != '\0'; i++) {
        if (strcmp(arr[i].name, s.name)==0){
            return 1; //returns 1 if names are the same
        }  
    }
    return 0;
}
void copyArray(MetroStation dest[],MetroStation src[]){
	int i;
	for(i=0; i<SIZE; i++){
		//Such a method can be applied to clean the inside of the sent destination array
		dest[i].name[0]='\0';
	}
	for(i=0; src[i].name[0]!='\0' && i<SIZE; i++){
		strcpy(dest[i].name,src[i].name); //names are copied
		dest[i].x=src[i].x; //x is copied
		dest[i].y=src[i].y; //y is copied
	}
}
void findPath (MetroStation start, MetroStation finish, MetroStation path[]) { 
    MetroStation partialPath[SIZE] = {'\0'}; //an empty array is defined
    recursiveFindPath(start, finish, partialPath, path);
}

void recursiveFindPath(MetroStation start, MetroStation finish, MetroStation partialPath[],MetroStation bestPath[]){
	int i,j,addIndex;
	
	for(i=0; partialPath[i].name[0]!='\0' && i<SIZE; i++){
		if(strcmp(partialPath[i].name,start.name)==0){
			return; //if there is a station in the array equal to the start station, return immediately
		}
	}
	
	if(equals(start,finish)){ //if the start and finish stations are equal
		for(addIndex=0; partialPath[addIndex].name[0]!='\0' && addIndex<SIZE; addIndex++);
		partialPath[addIndex]=start; //Find the last element of partialPath and add the start station to the next element
		if(bestPath[0].name[0]=='\0'){
			copyArray(bestPath,partialPath); //If the first element of bestPath is empty, the elements of partialPath are copied to bestPath
		}
		
		if(getDistanceTravelled(bestPath)>getDistanceTravelled(partialPath)){
			//if length of bestPath is greater than partialPath partialPath is copied to bestPath because partialPath is shorter
			copyArray(bestPath,partialPath);
		}
		return;
	}else{
		MetroStation neighbors[SIZE]={'\0'}; //an empty array is defined
		getNeighboringStations(istanbul,start,neighbors); //Stations close to the start station are thrown into this empty array.
		for(j=0; j<SIZE && neighbors[j].name[0]!='\0';j++){
			//for each of the elements inside this neighbors array
			MetroStation duplicatePath[SIZE]={'\0'}; //an empty array is defined
			copyArray(duplicatePath,partialPath); //this empty array is copied with partial path
			if(!contains(duplicatePath,start)){
				for(addIndex=0; duplicatePath[addIndex].name[0]!='\0' && addIndex<SIZE; addIndex++);
				duplicatePath[addIndex]=start; //if the duplicatePath does not have a start station in it, the start station is appended to the end of the duplicatePath
			}
			//and this function is returned continuously until it finds bestPath
			recursiveFindPath(neighbors[j], finish, duplicatePath, bestPath);
			
		}
	}
	
}


