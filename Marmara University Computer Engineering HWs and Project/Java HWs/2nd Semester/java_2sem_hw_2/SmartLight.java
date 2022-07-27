import java.util.Calendar;

public class SmartLight extends SmartObject implements LocationControl, Programmable{
	// This is the file for the Github Repository.
	private boolean hasLightTurned; //parameters of smartobject
	private java.util.Calendar programTime;
	private boolean programAction;
	
	public SmartLight(String alias, String macId) {
		setAlias(alias);
		setMacId(macId);
	}
	
	public boolean testObject() { //Opening and closing test of the object is done
		if(controlConnection()) {
			System.out.println("Test is starting for SmartLight");
			SmartObjectToString();
			turnOnLight();
			turnOffLight();
			System.out.println("Test completed for SmartLight");
			return true;
		}else {
			return false;
		}
		
	}
	public boolean shutDownObject() {
		//If the object is open, it is closed directly.
		if(controlConnection()) {
			SmartObjectToString();
			if(isHasLightTurned()) {
				turnOffLight();	
			}
			return true;
		}else {
			return false;	
		}
		
	}
	public void turnOnLight() {
		if(controlConnection()) {
			if(!isHasLightTurned()) {
				java.util.Calendar currentTime=java.util.Calendar.getInstance();
				String time=currentTime.getTime().getHours()+":"+currentTime.getTime().getMinutes()+":"+currentTime.getTime().getSeconds();
				setHasLightTurned(true); //sets the parameter of the light to on
				System.out.println("Smart Light - "+getAlias()+" is turned on now (Current time: "+time+")");
			}else {
				System.out.println("Smart Light - "+getAlias()+" has been already turned on");
			}
		}
	}
	public void turnOffLight() {
		if(controlConnection()) {
			if(isHasLightTurned()) {
				java.util.Calendar currentTime=java.util.Calendar.getInstance();
				String time=currentTime.getTime().getHours()+":"+currentTime.getTime().getMinutes()+":"+currentTime.getTime().getSeconds();
				setHasLightTurned(false); //sets the parameter of the light to off
				System.out.println("Smart Light - Living Room Light is turned off now (Current time: "+time+")");
			}else {
				System.out.println("Smart Light - Living Room Light has been already turned off");
			}
		}
	}
	
	
	public void onLeave() {
		//Called to hang up when leaving home
		if(controlConnection()) {
			System.out.println("On Leave -> Smart Light - "+getAlias());
			turnOffLight();
		}
	}
	public void onCome() {
		//called to open on the way home
		if(controlConnection()) {
			System.out.println("On Come -> Smart Light - "+getAlias());
			turnOnLight();
		}
	}
	public void setTimer(int seconds) {
		//a timer is set based on the seconds sent
		if(controlConnection()) {
			java.util.Calendar currentTime=java.util.Calendar.getInstance();
			String time=currentTime.getTime().getHours()+":"+currentTime.getTime().getMinutes()+":"+currentTime.getTime().getSeconds();
			currentTime.add(Calendar.SECOND, seconds);
			setProgramTime(currentTime);
			if(isHasLightTurned()) {
				System.out.println("Smart Light - "+getAlias()+" will be turned off "+seconds+" seconds later! (Current time: "+time+")");
				setProgramAction(false);
			}else {
				System.out.println("Smart Light - "+getAlias()+" will be turned on "+seconds+" seconds later! (Current time: "+time+")");
				setProgramAction(true);
			}
		}
	}
	public void cancelTimer() {
		if(controlConnection()) {
			setProgramTime(null);
		}
	}
	public void runProgram() {
		//If the timer is set, it will be checked from the programAction and the necessary methods will be called when it is equal to the timer's duration.
		if(controlConnection()) {
			java.util.Calendar currentTime=java.util.Calendar.getInstance();
			String time=currentTime.getTime().getHours()+":"+currentTime.getTime().getMinutes()+":"+currentTime.getTime().getSeconds();
			if(getProgramTime()!=null) {
				if(getProgramTime().getTime().getHours()==currentTime.getTime().getHours() && getProgramTime().getTime().getMinutes()==currentTime.getTime().getMinutes() && getProgramTime().getTime().getSeconds()==currentTime.getTime().getSeconds()) {
					if(getProgramAction()) {
						System.out.println("runProgram -> Smart Light - "+getAlias()+"\nSmart Light - "+getAlias()+" is turned on now (Current time: "+time+")");
					}else {
						System.out.println("runProgram -> Smart Light - "+getAlias()+"\nSmart Light - "+getAlias()+" is turned off now (Current time: "+time+")");
					}
					setProgramTime(null);
				}	
			}
			
		}
	}
	public boolean isHasLightTurned() {
		return hasLightTurned;
	}
	public void setHasLightTurned(boolean hasLightTurned) {
		this.hasLightTurned = hasLightTurned;
	}
	public java.util.Calendar getProgramTime() {
		return programTime;
	}
	public void setProgramTime(java.util.Calendar programTime) {
		this.programTime = programTime;
	}
	public boolean getProgramAction() {
		return programAction;
	}
	public void setProgramAction(boolean programAction) {
		this.programAction = programAction;
	}
	

}
