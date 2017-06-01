
public class RouteFinder {


	public RouteFinder(){

	}

	/**
	 * finds a DeliveryRoute from origin to destination, and return whether successful
	 * @param origin
	 * @param destination
	 * @return
	 */
	public static boolean findRoute(String origin, String destination){

		//TODO path-finding...

		//at this point just return true if the DB contains the origin and destination
		return (RouteService.getOrigins().contains(origin) && RouteService.getDestinations().contains(destination)?true:false);


	}
}
