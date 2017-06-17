package logic;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import event_logging.SaveDataToXML;

import gui.KPSmartFrame;

import logic.Route.TransportType;

public class KPSmartController {

	// The user that is logged in to the system. If no user is logged in, currentUser = null.
	private User currentUser = null;
	private KPSmartFrame kpSmartFrame;
	private final double weightCost = 0.25;

	/**
	 * Initialise the controller.
	 */
	public KPSmartController() {

	}

	/**
	 * List of enum actions which will be used to display the actions that are available for that user type.
	 *
	 */
	public enum Actions {
		BOOKING,
		CREATE_USER,
		BUSINESS_FIGURES,
		UPDATE_ROUTE,
		DISCONTINUE_ROUTE,
		LOGOUT
	}

	/**
	 * Adds a new route to the system.
	 *
	 * @param origin - origin of the Route.
	 * @param destination - the destination of the Route.
	 * @param priority - the transport type of the route.
	 * @param price - price of the route.
	 * 
	 * @return String - error or success message.
	 */
	public String addRoute(String origin, String destination, String priority, String price) {
		Route.TransportType transportType;
		if (priority.equals("Air")) {
			transportType = Route.TransportType.AIR;
		} else if (priority.equals("Land")) {
			transportType = Route.TransportType.LAND;
		} else if (priority.equals("Sea")) {
			transportType = Route.TransportType.SEA;
		} else {
			return "Please select a Priority.";
		}
		
		double doublePrice;
		
		// TODO: I have no idea how the double price field works in NewRoutePanel.java.
		if (price.matches("^(?:\\d+(?:\\.\\d{2})?)$")) {
			doublePrice = Double.parseDouble(price);
		} else {
			return "Price must be in the correct format.";
		}		
		
		Route route = new Route(origin, destination, transportType, doublePrice);
		boolean success = RouteService.insert(route);
		if (success) {
			System.out.println("new route added");
			SaveDataToXML.saveToXML(route);
			return "Success";
		} else {
			System.out.println("route failed to add");
			return "Route failed to add";
		}
	}

	/**
	 * Creates a new order.
	 *
	 * @param priority - the priority of the order.
	 * @param volume - volume of the order.
	 * @param origin - where the order is made.
	 * @param destination - where the order needs to go.
	 * @param weight - weight of the order.
	 * @param timestamp - when the order is made.
	 * 
	 * @return String - error or success message.
	 */
	public String createOrder(String prioritySelected, String volume, String origin, String destination, String weight) {
		Route.TransportType transportType;
		boolean priority = false;
		
		// Convert priority/transportType to correct format.
		if (prioritySelected.equals("Air")) {
			transportType = Route.TransportType.AIR;
			priority = true;
		} else if (prioritySelected.equals("Land")) {
			transportType = Route.TransportType.LAND;
		} else if (prioritySelected.equals("Sea")) {
			transportType = Route.TransportType.SEA;
		} else {
			return "Please select a Priority.";
		}
		
		// TODO: Check weight is in correct format.
		double doubleWeight;
		if (weight.matches("^(?:\\d+(?:\\.\\d{2})?)$")) {
			doubleWeight = Double.parseDouble(weight);
		} else {
			return "Weight must be in the correct format.";
		}
		
		// TODO: Check volume is in correct format.
		double doubleVolume;
		if (volume.matches("^(?:\\d+(?:\\.\\d{2})?)$")) {
			doubleVolume = Double.parseDouble(volume);
		} else {
			return "Volume must be in the correct format.";
		}
		
		
		Mail mail = new Mail(transportType, doubleVolume, origin, destination, doubleWeight);
		SaveDataToXML.saveToXML(mail);


		PriorityQueue<DeliveryRoute> queue = new PriorityQueue<DeliveryRoute>(new RouteComparitor());

		queue.add(new DeliveryRoute(origin));

		List<Route> availableRoutes = new ArrayList<>();

		DeliveryRoute currentRoute;

		while(!(queue.peek().getOrigin().equals(origin) && queue.peek().getDestination().equals(destination)) && !queue.isEmpty()){ //keep peeking the priorityQueue until the first route is from origin to destination

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

		if(queue.isEmpty()){
			//TODO no route exists so do something
			return "Route does not exist";
		} else {
			DeliveryRoute deliveryRoute = queue.poll();//TODO do something with the completed route, will need to log something here
			mail.setPrice(deliveryRoute.getTotalPrice() + ((mail.getWeight() + mail.getVolume()) * weightCost));
			return "Success";
		}

	}

	/**
	 * Create a new user and add it to the database.
	 *
	 * @param username - username of new account
	 * @param password - password of new account
	 * @param role - role of new account (Manager or Clerk)
	 */
	public String createUser(String username, String password, String role) {
		User.UserType userType;
		if (role.equals("Clerk")) {
			userType = User.UserType.CLERK;
		} else if (role.equals("Manager")) {
			userType = User.UserType.MANAGER;
		} else {
			return "Please select a user type";
		}

		User newUser = new User(username, password, userType); // Create User object
		boolean success = UserService.insert(newUser);
		if (success) {
			// TODO: call a GUI function?
			System.out.println("User has been created succesfully");
			SaveDataToXML.saveToXML(newUser);
			return "Success";
		} else {
			if (UserService.getUser(username) != null){
				return "Username already exists, please specify a unique username";
			}
			System.out.println("ERROR: failed to create user.");
			// TODO: call a GUI function?
			return "Failed to create User";
		}
	}

	/**
	 * 	Takes in a route and removes it from the database and system.
	 *
	 * @param route - the route to be deleted.
	 */
	public String discontinueRoute(String origin, String destination, String prioritySelected) {
		Route.TransportType transportType;
		if (prioritySelected.equals("Air")) {
			transportType = Route.TransportType.AIR;
		} else if (prioritySelected.equals("Land")) {
			transportType = Route.TransportType.LAND;
		} else if (prioritySelected.equals("Sea")) {
			transportType = Route.TransportType.SEA;
		} else {
			return "Please select a Priority.";
		}
		
		// Price doesn't matter so temp value of 0 is added here.
		Route route = new Route(origin, destination, transportType, 0);
		
		boolean success = RouteService.deleteRoute(route);
		if (success) {
			System.out.println("Route has been removed");
//			SaveDataToXML.discontinueRoute(route);
			// TODO: do something on GUI.
			return "Success";
		} else {
			System.out.println("ERROR: Route failed to delete");
			// Theoretically should never reach this step.
			return "Failed to delete";
		}
	}


	/**
	 * Login the user and navigate the GUI to the home page on successful login.
	 *
	 * @param user - the username of the user logging in.
	 * @param password - the password of the user logging in.
	 *
	 * @return boolean - true or false indicating if the user logged in or not. This should be changed later.
	 */

	public String loginUser(String username, String password, KPSmartFrame frame) {
		this.kpSmartFrame = frame;

		User user = UserService.getUser(username);
		// Check if a user was found.
		if (user != null) {
			// Check if password is correct.
			if (password.equals(user.getPassword())) {
				setCurrentUser(user);
				kpSmartFrame.initialiseHomeScreen();
				kpSmartFrame.changeFocus("Home Screen");				
				return "Success";
			} else {
				// Password is incorrect.
				return "Password does not match. Please try again.";
			}
		} else {
			System.out.println("User does not exist.");
			return "User does not exist.";
		}

	}

	/** Log the user out of the system and change the screen to the login screen. */
	public void logoutUser() {

		setCurrentUser(null);
	}

	/**
	 * Display the business figures of the business.
	 */
	public void monitorBusinessFigures() {
		// TODO: probably open up the business figures window.
	}

	/**
	 * 	Update the current price.
	 *
	 * @param newPrice
	 */
	public String updatePrice(String origin, String destination, TransportType transportType ,String price) {
		double newPrice;
		
		if (price.matches("^(?:\\d+(?:\\.\\d{2})?)$")) {
			newPrice = Double.parseDouble(price);
		} else {
			return "Price must be in the correct format.";
		}
		
		Route route = new Route(origin, destination, transportType, newPrice);
		boolean success = RouteService.update(route);//TODO should probably log this change which would require getting the previous route before i overwrite it
		if (success) {
			return "Success";
		} else {
			return "Price failed to update";
		}
	}

	/**
	 * Get the current user (the user that is logged in to the system).
	 *
	 * @return String - currentUser
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Set the logged in user to the user.
	 *
	 * @param user - the user that is now logged in.
	 */
	public void setCurrentUser(User user) {
		this.currentUser = user;
	}

	public KPSmartFrame getKPSmartFrame() {
		return this.kpSmartFrame;
	}

	public void addRefrenceToFrame(KPSmartFrame kpSmartFrame) {
		this.kpSmartFrame = kpSmartFrame;		
	}


}
