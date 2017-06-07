import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

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

	/**
	 * pseudo constructor to find a DeliveryRoute between origin and destination, will return that delivery route if it exists otherwise null
	 * @param origin
	 * @param destination
	 * @param priority
	 * @return
	 */
	public static DeliveryRoute findRoute(String origin, String destination, boolean priority){

		PriorityQueue<DeliveryRoute> queue = new PriorityQueue<DeliveryRoute>(new RouteComparitor());

		queue.add(new DeliveryRoute(origin));

		List<Route> availableRoutes = new ArrayList<>();

		DeliveryRoute currentRoute;

		//TODO get pathfinding working...

		while(!(queue.peek().getOrigin().equals(origin) && queue.peek().getDestination().equals(destination)) && !queue.isEmpty()){ //keep polling the priorityQueue until the first route off is from origin to destination

			//availableRoutes = RouteService.getRoutesByOrigin(currentRoute.getOrigin());//TODO method i need doesnt exist yet
			currentRoute = queue.poll();
			DeliveryRoute tempRoute;

			for(Route r: availableRoutes){
				//check if we are going somewhere we have been before
				boolean loop=false;

				for(Route r2: currentRoute.getDeliveryRoute()){
					if(r.getDestination().equals(r2.getOrigin())){
						loop = true;
					}
				}
				if(!loop){

					//add the routes to our search
					if(r.getTransportType().equals(Route.TransportType.AIR)){ //only add air routes if this is priority delivery
						if (priority){
							tempRoute = new DeliveryRoute(currentRoute);
							tempRoute.addRoute(r);
							queue.add(tempRoute);
						}

					} else {
						tempRoute = new DeliveryRoute(currentRoute); //add all land and sea routes regardless
						tempRoute.addRoute(r);
						queue.add(tempRoute);
					}
				}
			}

			currentRoute = queue.poll();
		}

		return(queue.isEmpty()? null: queue.poll()); //if we ran out of routes to check return null, otherwise the first route in the queue should be the complete route
	}

	/**
	 * Comparator for prioritizing deliveryRoutes by totalCost
	 * @author whitewill1
	 *
	 */
	private static class RouteComparitor implements Comparator<DeliveryRoute>{
		@Override
		public int compare(DeliveryRoute r1, DeliveryRoute r2) {
			return (r1.getTotalPrice()<r2.getTotalPrice()? -1:1);//TODO, may have this the wrong way around, need to test it
		}

	}
}
