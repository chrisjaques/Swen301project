package logic;
import java.util.ArrayList;
import java.util.Arrays;

import gui.HomeScreen;
import gui.KPSmartFrame;
import gui.LoginScreen;

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
		System.out.println(mail);
		// TODO: Takes in an Order.
		DeliveryRoute deliveryRoute = DeliveryRoute.findRoute(origin,destination,priority); //<- returns Route if it exists or null
		// TODO do something here. Need to talk to Will and Chris.
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
	public void loginUser(String username, String password, KPSmartFrame frame) {
		
		System.out.println(username);
		System.out.println(password);
		
		User user = UserService.getUser(username);
		// Check if a user was found.
		if (user != null) {
			// Check if password is correct.
			if (password.equals(user.getPassword())) {
				System.out.println("Successfully logged in!");
				setCurrentUser(user);
				ArrayList<Actions> actions = getActions();
				// TODO: Call GUI method to navigate to home page and pass in {actions}.
				//KPSmartFrame.changeFocus("Initial Home Screen" 
				frame.changeFocus("Home Screen");
//				HomeScreen homeScreen = new HomeScreen(this);
//				homeScreen.openHomeScreen();
			} else {
				// Password is incorrect.
				System.out.println("ERROR: Password does not match.");
				// TODO: Display error message on GUI.
			}
		} else {
			System.out.println("User does not exist.");
			// TODO: Display error message on GUI.
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
	 * 	Log an event.
	 * 
	 * @param event - event to be logged.
	 */
	public void processEvent(String event) {
		// TODO: log it
	}
	
	/**
	 * Gets the actions that are available to the logged in user type.
	 * 
	 * @return - the list of actions available to the user.
	 */
	public ArrayList<Actions> getActions() {		
		ArrayList<Actions> actions = new ArrayList<Actions>();
		actions.add(Actions.BOOKING);
		actions.add(Actions.UPDATE_ROUTE);
		actions.add(Actions.DISCONTINUE_ROUTE);
		actions.add(Actions.LOGOUT);	
		if (getCurrentUser().getRole().equals(User.UserType.MANAGER)) {
			actions.add(Actions.CREATE_USER);
			actions.add(Actions.BUSINESS_FIGURES);
		}
		for (Actions a: actions) {
			System.out.println(a.getClass());
			System.out.println(a);
		}
		return actions;
	}
	
	/**
	 * 	Update the current price.
	 * 
	 * @param newPrice
	 */
	public void updatePrice(int newPrice) {
		// TODO: set the current price to the new price.
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
