package ibm.gse.eda.basicjms.model;

public class ShippingContainer {
	
	protected String containerID;
	protected String type;
	protected String status;
	protected String brand;
	protected int capacity;
	
	public ShippingContainer(String cid,  String type, String brand, int capacity) {
		this.containerID = cid;
		this.type = type;
		this.brand = brand;
		this.capacity = capacity;
	}
	
	public ShippingContainer(String cid,  String type, String status, String brand, int capacity) {
		this.containerID = cid;
		this.type = type;
		this.brand = brand;
		this.capacity = capacity;
	}
	
	public ShippingContainer() {} // needed for json deserialization
	
	public String toString(){
		return "Container= " + getContainerID()
			+ " brand= " + getBrand()
			+ " type= " + getType()
		 	+ " capacity= " + getCapacity()
		 	+ " status= " + getStatus();
	}

	public String getContainerID() {
		return containerID;
	}

	public void setContainerID(String containerID) {
		this.containerID = containerID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public static String[] getSupportedContainerTypes(){
		String[] a =  { "Reefer", "Dry", "FlatRack", "OpenTop", "OpenSide", "Tunnel", "Tank", "Thermal"};
		return a;
	}
	
	
}
