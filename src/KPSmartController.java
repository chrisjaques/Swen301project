import java.util.ArrayList;
import java.util.Arrays;

public class KPSmartController {
	
	private User currentUser = null;

	public KPSmartController(User currentUser) {
		this.currentUser = currentUser;
		
		// TODO: Do I take in a User? OR all the information of a User?
		
	}
	
	public enum Actions {
		Booking,
		CreateUser,
		BussinessFigures,
		UpdateRoute,
		DiscontinueRoute,
		Logout	
	}
	
	/**
	 * 	Adds a new route to the system.
	 * @param route - the route to be added to the system.
	 */
	public void addRoute(Route route) {
		// TODO: Where does the route come in from?
		boolean success = false;
		success = RouteService.insertOrUpdate(route);
		if (success) {
			// TODO: success GUI action
			System.out.println("new route added");
		} else {
			// TODO: failed GUI action
			System.out.println("route failed to add");
		}
	}
	
	public void createOrder(String origin, String destination, boolean priority) {
		// TODO: Takes in an Order.
		//Route route = DeliveryRoute.findRoute(origin,destination,priority) <- returns Route if it exists or null
		
	}
	
	/**
	 * Create a new user and add it to the database.
	 * 
	 * @param username
	 * @param password
	 * @param role
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
	 * @param route
	 */
	public void discontinueRoute(Route route) {
		// TODO: takes in an existing route and removes it from the system.
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
	public void loginUser(String username, String password) {
		
		User user = UserService.getUser(username);
		// Check if a user was found.
		if (user != null) {
			// Check if password is correct.
			if (password == user.getPassword()) {
				System.out.println("Successfully logged in!");
				setCurrentUser(user);
				ArrayList<Actions> actions = getActions();
				// TODO: Call GUI method to navigate to home page and pass in {actions}.
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
	
	/**
	 * Log the user out of the system and change the screen to the login screen.
	 */
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
	 * 	Process an event that occurs.
	 * @param event
	 */
	public void processEvent(String event) {
		// TODO: should take in an Event.
		// TODO: log it?
	}
	
	public ArrayList<Actions> getActions() {
		if (getCurrentUser().getRole() == User.UserType.Manager) {
			ArrayList<Actions> actions = (ArrayList<Actions>) Arrays.asList(Actions.values());
			return actions;
		} else {
			ArrayList<Actions> actions = new ArrayList<Actions>();
			actions.add(Actions.Booking);
			actions.add(Actions.UpdateRoute);
			actions.add(Actions.DiscontinueRoute);
			actions.add(Actions.Logout);			
			return actions;
		}
	}
	
	/**
	 * 	Update the current price.
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
