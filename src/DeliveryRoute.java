import java.util.ArrayList;
import java.util.List;

public class DeliveryRoute {
	private List<Route> deliveryRoute;
	private String origin;
	private String destination;
	private double totalPrice = 0;

	/**
	 * create a deliveryRoute with a set origin
	 * @param origin
	 */
	public DeliveryRoute(String origin){
		this.origin = origin;
		this.deliveryRoute = new ArrayList<Route>();
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
	}

	/**
	 * add a new route to the end of the current deliveryRoute and update the destination and totalPrice accordingly
	 * @param r
	 */
	public void addRoute(Route r){
		totalPrice+=r.getPrice();
		destination = r.getDestination();
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
}
