import java.util.ArrayList;
import java.util.Arrays;

public class SmartHome {
	// This is the file for the Github Repository.
	
	//a list of objects belonging to a house
	private ArrayList<SmartObject> smartObjectList = new ArrayList<SmartObject>();
	
	public SmartHome() {
		
	}
	public boolean addSmartObject(SmartObject smartObject) {
		//IP is generated according to the number of elements a house has
		String ip="10.0.0."+(smartObjectList.size()+100);
		System.out.println("--------------------------------------------------------------------------\r\n" + 
				"--------------------------------------------------------------------------\r\n" + 
				"Adding new SmartObject\r\n" + 
				"--------------------------------------------------------------------------");
		smartObject.connect(ip); //generated IP is sent to connect method
		smartObject.testObject(); //added object is tested
		System.out.println();
		return smartObjectList.add(smartObject);
		
		
	}
	public boolean removeSmartObject(SmartObject smartObject) {
		return smartObjectList.remove(smartObject);
	}
	public void controlLocation(boolean onCome) {
		System.out.println("---------------------------------------------------------------------------\r\n" + 
				"---------------------------------------------------------------------------\r\n" + 
				"LocationControl: OnCome\r\n" + 
				"---------------------------------------------------------------------------");
		for(int i=0; i<smartObjectList.size(); i++) {
			if(smartObjectList.get(i) instanceof LocationControl) {
				//If there is a location control object, the method is called according to the sent on come value.
				if(onCome) {
					((LocationControl)smartObjectList.get(i)).onCome();
				}else {
					((LocationControl)smartObjectList.get(i)).onLeave();;
				}
			}
		}
	}
	public void controlMotion(boolean hasMotion, boolean isDay) {
		//If there is a motion control object, methods are called according to the arguments.
		System.out.println("--------------------------------------------------------------------------\r\n" + 
				"--------------------------------------------------------------------------\r\n" + 
				"MotionControl: HasMotion, isDay\r\n" + 
				"--------------------------------------------------------------------------");
		for(int i=0; i<smartObjectList.size(); i++) {
			if(smartObjectList.get(i) instanceof MotionControl) {
				((MotionControl)smartObjectList.get(i)).controlMotion(hasMotion, isDay);
			}
		}
	}
	public void controlProgrammable() {
		System.out.println("--------------------------------------------------------------------------\r\n" + 
				"--------------------------------------------------------------------------\r\n" + 
				"Programmable: runProgram\r\n" + 
				"--------------------------------------------------------------------------");
		for(int i=0; i<smartObjectList.size(); i++) {
			if(smartObjectList.get(i) instanceof Programmable) {
				
				((Programmable)smartObjectList.get(i)).runProgram();
			}
		}
	}
	public void controlTimer(int seconds) {
		System.out.println("--------------------------------------------------------------------------\r\n" + 
				"--------------------------------------------------------------------------\r\n" + 
				"Programmable: "+seconds+" seconds\r\n" + 
				"--------------------------------------------------------------------------");
		for(int i=0; i<smartObjectList.size(); i++) {
			if(smartObjectList.get(i) instanceof Programmable) {
				//Timer is set according to seconds for programmable objects
				if(seconds >0) {
					((Programmable)smartObjectList.get(i)).setTimer(seconds);
				}else {
					((Programmable)smartObjectList.get(i)).cancelTimer();
				}
				
			}
		}
	}
	public void controlTimerRandomly() {
		System.out.println("--------------------------------------------------------------------------\r\n" + 
				"--------------------------------------------------------------------------\r\n" + 
				"Programmable: Timer = 0, 5 or 10 seconds randomly\r\n" + 
				"--------------------------------------------------------------------------");
		for(int i=0; i<smartObjectList.size(); i++) {
			if(smartObjectList.get(i) instanceof Programmable) {
				//Calls method randomly from random object based on randomly generated numbers
				int randomSeconds=(int)(Math.random()*3);
				
				if(randomSeconds ==2) {
					((Programmable)smartObjectList.get(i)).setTimer(10);
				}else if(randomSeconds ==1) {
					((Programmable)smartObjectList.get(i)).setTimer(5);
				}else {
					((Programmable)smartObjectList.get(i)).cancelTimer();
				}
				
			}
		}
	}
	public void sortCameras() {
		System.out.println("--------------------------------------------------------------------------\r\n" + 
				"--------------------------------------------------------------------------\r\n" + 
				"Sort Smart Cameras\r\n" + 
				"--------------------------------------------------------------------------");
		ArrayList<SmartCamera> smartCameras=new ArrayList<SmartCamera>();
		//sorting by battery life of cameras
		for(int i=0; i<smartObjectList.size(); i++) {
			if(smartObjectList.get(i) instanceof SmartCamera) {
				smartCameras.add((SmartCamera)smartObjectList.get(i));
			}
		}
		
		Object[] smartCamerasArray=smartCameras.toArray();  //arrayList is converted to array
		Arrays.sort(smartCamerasArray); //sort method is called from arrays
		for(int i=0; i<smartCamerasArray.length; i++) {
			System.out.println(smartCamerasArray[i].toString()); //The toString method of the objects in the array is called
		}
		
	}
	public ArrayList<SmartObject> getSmartObjectList() {
		return smartObjectList;
	}
	public void setSmartObjectList(ArrayList<SmartObject> smartObjectList) {
		this.smartObjectList = smartObjectList;
	}
}
