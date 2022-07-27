import java.util.Calendar;

public class SmartPlug extends SmartObject implements Programmable{
	// This is the file for the Github Repository.
	private boolean status; 
	private java.util.Calendar programTime;
	private boolean programAction;
	
	public SmartPlug(String alias, String macId) {
		setAlias(alias);
		setMacId(macId);
	}

	@Override
	public boolean testObject() {
		if(controlConnection()) {
			System.out.println("Test is starting for SmartPlug");
			SmartObjectToString();
			turnOn();
			turnOff();
			System.out.println("Test completed for SmartPlug");
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean shutDownObject() {
		if(controlConnection()) {
			SmartObjectToString();
			if(getStatus()) {
				turnOff();
			}
			return true;
		}else {
			return false;
		}
	}
	public void turnOn() {
		if(controlConnection()) {
			if(getStatus()==false) {
				java.util.Calendar currentTime=java.util.Calendar.getInstance();
				String time=currentTime.getTime().getHours()+":"+currentTime.getTime().getMinutes()+":"+currentTime.getTime().getSeconds();
				setStatus(true);
				System.out.println("Smart Plug - "+getAlias()+" is turned on now (Current time: "+time+")");
			}else {
				System.out.println("Smart Plug - "+getAlias()+" has been already turned on");
			}
		}
	}
	public void turnOff() {
		if(controlConnection()) {
			if(getStatus()) {
				java.util.Calendar currentTime=java.util.Calendar.getInstance();
				String time=currentTime.getTime().getHours()+":"+currentTime.getTime().getMinutes()+":"+currentTime.getTime().getSeconds();
				setStatus(false);
				System.out.println("Smart Plug - "+getAlias()+" is turned off now (Current time: "+time+")");
			}else {
				System.out.println("Smart Plug - "+getAlias()+" has been already turned off");
			}
		}
	}
	
	public void setTimer(int seconds) {
		if(controlConnection()) {
			java.util.Calendar currentTime=java.util.Calendar.getInstance();
			String time=currentTime.getTime().getHours()+":"+currentTime.getTime().getMinutes()+":"+currentTime.getTime().getSeconds();
			currentTime.add(Calendar.SECOND, seconds);
			setProgramTime(currentTime);
			
			if(getStatus()) {
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
		if(controlConnection()) {
			java.util.Calendar currentTime=java.util.Calendar.getInstance();
			String time=currentTime.getTime().getHours()+":"+currentTime.getTime().getMinutes()+":"+currentTime.getTime().getSeconds();
			if(getProgramTime()!=null) {
				if(getProgramTime().getTime().getHours()==currentTime.getTime().getHours() && getProgramTime().getTime().getMinutes()==currentTime.getTime().getMinutes() && getProgramTime().getTime().getSeconds()==currentTime.getTime().getSeconds()) {
					if(getProgramAction()) {
						System.out.println("runProgram -> Smart Plug - "+getAlias()+"\nSmart Light - "+getAlias()+" is turned on now (Current time: "+time+")");
					}else {
						System.out.println("runProgram -> Smart Plug - "+getAlias()+"\nSmart Light - "+getAlias()+" is turned off now (Current time: "+time+")");
					}
					setProgramTime(null);
				}	
			}
			
		
		}
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
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
