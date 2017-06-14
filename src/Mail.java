public class Mail {
	private String priority;
	private String volume;
	private String origin;
	private String destination;
	private String weight;
	private String timestamp;
	
	public Mail(String priority, String volume, String origin, String destination,
			String weight, String timestamp) {
		this.setPriority(priority);
		this.setVolume(volume);
		this.setOrigin(origin);
		this.setDestination(destination);
		this.setWeight(weight);
		this.setTimestamp(timestamp);
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
}
