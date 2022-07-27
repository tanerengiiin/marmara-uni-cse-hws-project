
public abstract class SmartObject {
	// This is the file for the Github Repository.
	private String alias, macId, IP;
	private boolean connectionStatus; 
	
	public SmartObject() {
		
	}
	public abstract boolean testObject();
	public abstract boolean shutDownObject();
	public boolean connect(String IP) {
		setIP(IP); //The IP parameter sent is set to the IP parameter of the object.
		setConnectionStatus(true); //connection status is true because the connection is established
		System.out.println(getAlias()+" connection established");
		return true;
	};
	public boolean disconnect() {
		setIP(null); //IP parameter is set to null because connection is lost
		setConnectionStatus(false); //connection status is false because the connection is terminated
		return true;
	};
	public void SmartObjectToString() {
		System.out.println("This is "+this.getClass().getName()+" device "+getAlias()+"\n\tMacId: "+getMacId()+"\n\tIP: "+getIP());
	};
	public boolean controlConnection() {
		//Checks if the object is connected
		if(getConnectionStatus()) {
			return true;
		}else {
			System.out.println("This device is not connected. SmartCamera -> "+getAlias());
			return false;
		}
	};
	
	
	public String getMacId() {
		return macId;
	}
	public void setMacId(String macId) {
		this.macId = macId;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public boolean getConnectionStatus() {
		return connectionStatus;
	}
	public void setConnectionStatus(boolean connectionStatus) {
		this.connectionStatus = connectionStatus;
	}
	
}
