package logic;
import event_logging.SaveDataToXML;
import gui.HomeScreen;

import logic.Route.TransportType;

public class KPSmartController {

	// The user that is logged in to the system. If no user is logged in, currentUser = null.
	private User currentUser = null;

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
	 * @param transportType - the transport type of the route.
	 * @param price - price of the route.
	 */
	public void addRoute(String origin, String destination, Route.TransportType transportType, double price) {
		// TODO: Take in parameters for a route
		// TODO: create a route with the parameters.
		Route route = new Route(origin, destination, transportType, price);
		boolean success = RouteService.insertOrUpdate(route);
		if (success) {
			// TODO: success GUI action
			System.out.println("new route added");
			SaveDataToXML.saveToXML(route);
		} else {
			// TODO: failed GUI action
			System.out.println("route failed to add");
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
	 */
	public void createOrder(boolean priority, String volume, String origin, String destination, String weight) {
		Mail mail = new Mail(priority, volume, origin, destination, weight);
		SaveDataToXML.saveToXML(mail);
		System.out.println(mail);
		// TODO: Takes in an Order.
		//DeliveryRoute deliveryRoute = DeliveryRoute.findRoute(origin,destination,priority); //<- returns Route if it exists or null
		// TODO do something here. Need to talk to Will and Chris.


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
		} else {
			DeliveryRoute deliveryRoute = queue.poll();//TODO do something with the completed route, will need to log something here
		}

	}

	/**
	 * Create a new user and add it to the database.
	 *
	 * @param username - username of new account
	 * @param password - password of new account
	 * @param role - role of new account (Manager or Clerk)
	 */
	public void createUser(String username, String password, User.UserType role) {
		// TODO: some sort of validation
		// TODO: call user service to add it in the database.

		User newUser = new User(username, password, role); // Create User object
		boolean success = UserService.insertOrUpdate(newUser);
		if (success) {
			// TODO: call a GUI function?
			System.out.println("User has been created succesfully");
			SaveDataToXML.saveToXML(newUser);
		} else {
			System.out.println("ERROR: failed to create user.");
			// TODO: call a GUI function?
		}
	}

	/**
	 * 	Takes in a route and removes it from the database and system.
	 *
	 * @param route - the route to be deleted.
	 */
	public void discontinueRoute(Route route) {
		boolean success = RouteService.deleteRoute(route);
		if (success) {
			System.out.println("Route has been removed");
//			SaveDataToXML.discontinueRoute(route);
			// TODO: do something on GUI.
		} else {
			System.out.println("ERROR: Route failed to delete");
			// Theoretically should never reach this step.
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

	public String loginUser(String username, String password) {

		System.out.println(username);
		System.out.println(password);

		User user = UserService.getUser(username);
		// Check if a user was found.
		if (user != null) {
			// Check if password is correct.
			if (password.equals(user.getPassword())) {
				System.out.println("Successfully logged in!");
				setCurrentUser(user);
				HomeScreen homeScreen = new HomeScreen(this);
				homeScreen.openHomeScreen();
				return "Success";
			} else {
				// Password is incorrect.
				System.out.println("ERROR: Password does not match.");
				// TODO: Display error message on GUI.
				return "Password does not match.";
			}
		} else {
			System.out.println("User does not exist.");
			// TODO: Display error message on GUI.
			return "User does not exist.";
		}

	}

	/** Log the user out of the system and change the screen to the login screen. */
	public void logoutUser() {

		setCurrentUser(null);

		//TODO: Navigate GUI to login page.
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
	public void updatePrice(String origin, String destination, TransportType transportType ,double newPrice) {
		//double oldPrice = RouteService.get
		Route route = new Route(origin, destination, transportType, newPrice);
		boolean success = RouteService.insertOrUpdate(route);//TODO should probably log this change which would require getting the previous route before i overwrite it

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
	private void setCurrentUser(User user) {
		this.currentUser = user;
	}


}
