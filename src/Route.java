
public class Route {
	private int id;
	private String origin;
	private String destination;
	private TransportType transportType;
	private double price;
	
	public enum TransportType {
		AIR, SEA, LAND
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public TransportType getTransportType() {
		return transportType;
	}
	public void setTransportType(TransportType transportType) {
		this.transportType = transportType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Route(int id, String origin, String destination, TransportType transportType, double price) {
		super();
		this.id = id;
		this.origin = origin;
		this.destination = destination;
		this.transportType = transportType;
		this.price = price;
	}
	
}




