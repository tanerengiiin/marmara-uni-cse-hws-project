
public class SmartCamera extends SmartObject implements MotionControl, Comparable<SmartCamera>{
	// This is the file for the Github Repository.
	private boolean status; //whether the camera is on or off
	private int batteryLife; // camera battery life
	private boolean nightVision; // night vision feature of the camera
	
	public SmartCamera(String alias, String macId, boolean nightVision, int batteryLife) {
		//super constructor is called
		setAlias(alias); //sent properties are set
		setMacId(macId);
		setNightVision(nightVision);
		setBatteryLife(batteryLife);
	}

	public void recordOn(boolean isDay) {
		if(controlConnection()) {
			if(!status) {
				if(isDay) { //If it is daytime, the camera can shoot, but if it is night, if it has night vision, it can shoot.
					setStatus(true);
					System.out.println("Smart Camera - "+getAlias()+" is turned on now");
				}else {
					if(nightVision) {
						setStatus(true);
						System.out.println("Smart Camera - "+getAlias()+" is turned on  now");
					}else {
						System.out.println("Sorry! Smart Camera - "+getAlias()+" does not have nigh vision feature");
					}
				}
			}else { //if you're already open
				System.out.println("Smart Camera - "+getAlias()+" has been already turned on");
			}
		}
	}
	public void recordOff() {
		if(controlConnection()) {
			if(status) {
				setStatus(false); //If the camera is on, it will be turned off directly.
				System.out.println("Smart Camera - "+getAlias()+" is turned off now");
			}else {
				System.out.println("Smart Camera - "+getAlias()+" has been already turned off");
			}
		}
	}
	
	@Override
	public int compareTo(SmartCamera arg0) { //comparison method is edited
		if(batteryLife>arg0.getBatteryLife()) {
			return 1;
		}else if(batteryLife==arg0.getBatteryLife()) {
			return 0;
		}else {
			return -1;
		}
	}

	@Override
	public boolean controlMotion(boolean hasMotion, boolean isDay) {
		if(!hasMotion) {
			System.out.println("Motion not detected!");
			return false;
		}else {
			System.out.println("Motion detected!");
			if(isDay) { //In the same way, methods are called according to whether it is day or night.
				recordOn(isDay);
				return true;
			}else {
				if(nightVision) {
					recordOn(isDay);
					return true;
				}else {
					return false;
				}
			}
		}
	}


	@Override
	public boolean testObject() {
		if(controlConnection()) { //Turning off and on is done for testing the object.
			System.out.println("Test is starting for SmartCamera");
			SmartObjectToString();
			System.out.println("Test is starting for SmartCamera day time");
			recordOn(true);
			recordOff();
			System.out.println("Test is starting for SmartCamera night time");
			recordOn(false);
			recordOff();
			System.out.println("Test completed for SmartCamera");
			return true;
		}else {
			return false;	
		}
		
	}

	@Override
	public boolean shutDownObject() {
		if(controlConnection()) { //If it is not closed, it is closed directly.
			SmartObjectToString();	
			if(getStatus()) {
				recordOff();
			}
			return true;
		}else {
			return false;	
		}
	}
	
	
	public String toString() {
		//The toString method is edited. Checking if the camera is shooting
		return "SmartCamera -> "+getAlias()+"'s battery life is "+getBatteryLife()+" status is "+(getStatus() ? "recording" : "not recording");
		
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getBatteryLife() {
		return batteryLife;
	}

	public void setBatteryLife(int batteryLife) {
		this.batteryLife = batteryLife;
	}

	public boolean getNightVision() {
		return nightVision;
	}

	public void setNightVision(boolean nightVision) {
		this.nightVision = nightVision;
	}
	
}
