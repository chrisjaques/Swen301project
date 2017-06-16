package logic;

import logic.Route.TransportType;

public class Mail {
	private Route.TransportType priority;
	private double volume;
	private String origin;
	private String destination;
	private double weight;
	private long timestamp;
	private double price = 0;
	
	public Mail(Route.TransportType transportType, double volume, String origin, String destination,
			double weight) {
		this.setPriority(transportType);
		this.setVolume(volume);
		this.setOrigin(origin);
		this.setDestination(destination);
		this.setWeight(weight);
		this.setTimestamp(System.currentTimeMillis());
	}
	
	public void setPrice(double price){
		this.price=price;
	}
	
	public double getPrice(){
		return price;
	}

	public Route.TransportType getPriority() {
		return priority;
	}

	public void setPriority(TransportType transportType) {
		this.priority = transportType;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
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

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
}
