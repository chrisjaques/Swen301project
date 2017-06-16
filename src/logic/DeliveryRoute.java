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

	/**
	 * Something like a pseudo constructor, constructs a DeliveryRoute between origin and destination, will return that delivery route if it exists otherwise null
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

		while(!(queue.peek().getOrigin().equals(origin) && queue.peek().getDestination().equals(destination)) && !queue.isEmpty()){ //keep polling the priorityQueue until the first route off is from origin to destination

			currentRoute = queue.poll();

			availableRoutes = RouteService.getRoutesByOrigin(currentRoute.getDestination());

			DeliveryRoute tempRoute;

			for(Route r: availableRoutes){
				//check if we are going somewhere we have been before
				boolean loop=false;

				for(Route r2: currentRoute.getDeliveryRoute()){
					if(r.getDestination().equals(r2.getOrigin())){
						loop = true;
					}
				}

				if(!loop){//if not somewhere we've been before

					//add the routes to our search
					if(r.getTransportType().equals(Route.TransportType.AIR)){ //always add air routes, they just go to the back of the queue for standard delivery
						tempRoute = new DeliveryRoute(currentRoute);
						tempRoute.addRoute(r);
						queue.add(tempRoute);

					} else if (!priority) { //add all land and sea routes, priority mail only uses air routes so dosn't add these
						tempRoute = new DeliveryRoute(currentRoute);
						tempRoute.addRoute(r);
						queue.add(tempRoute);
					}
				}
			}
		}

		return(queue.isEmpty()? null: queue.poll()); //if we ran out of routes to check return null, otherwise the first route in the queue should be the complete route
	}

	/**
	 * Comparator for prioritizing DeliveryRoutes, land and sea routes automatically go ahead of air routes because standard delivery only uses air routes when land or sea arn't possible
	 * the same Comparitor is used for air priority because it wont include any land or sea routes to compare
	 * @author whitewill1
	 *
	 */
	private static class RouteComparitor implements Comparator<DeliveryRoute>{
		@Override
		public int compare(DeliveryRoute r1, DeliveryRoute r2) {
			if(r1.getPriority()){

				if(r2.getPriority()){

					return (r1.getTotalPrice()<r2.getTotalPrice()? -1:1); //if they are both air routes just compare by cost
				}
				return 1; //if r1 is an air route and r2 isn't, put r2 ahead of r1
			}
			if(r2.getPriority()){
				return -1; //if r1 is a land or sea route and r2 isn't then r1 gets put ahead of r2
			}

			return (r1.getTotalPrice()<r2.getTotalPrice()? -1:1); //if neither are air routes order them by cost
		}
	}
}
