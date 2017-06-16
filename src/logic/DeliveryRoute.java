package logic;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class DeliveryRoute {
	private List<Route> deliveryRoute;
	private String origin;
	private String destination;
	private double totalPrice = 0;

	private boolean airPriority = true;//will be set to false if this route contains any land or Sea routes

	/**
	 * create a deliveryRoute with a set origin
	 * @param origin
	 */
	public DeliveryRoute(String origin){
		this.origin = origin;
		this.deliveryRoute = new ArrayList<Route>();
		this.destination = origin; //goes nowhere yet
	}

	/**
	 * copyConstructor
	 * @param r
	 */
	public DeliveryRoute(DeliveryRoute r){
		this.origin=r.getOrigin();
		this.destination=r.getDestination();
		this.totalPrice = r.getTotalPrice();
		this.deliveryRoute = new ArrayList<Route>(r.getDeliveryRoute());
		this.airPriority = r.getPriority();
	}

	/**
	 * add a new route to the end of the current deliveryRoute and update the destination and totalPrice accordingly
	 * @param r
	 */
	public void addRoute(Route r){
		totalPrice+=r.getPrice();
		destination = r.getDestination();
		if(r.getTransportType()!=Route.TransportType.AIR){
			airPriority = false;
		}
		deliveryRoute.add(r);
	}

	public double getTotalPrice(){
		return totalPrice;
	}
	public List<Route> getDeliveryRoute() {
		return deliveryRoute;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public boolean getPriority(){
		return airPriority;
	}
}
