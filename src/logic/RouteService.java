package logic;
import java.sql.*;
import java.util.ArrayList;

public class RouteService {

	// static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc";
	// static final String DB_URL =
	// "jdbc:sqlserver://swen301project.database.windows.net:1433;database=swen301project;user=chris@swen301project;password={SWEN$301};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

	static final String DB_URL = "jdbc:derby://localhost:1527/swen301;user=swen301;password=swen301";
	private static Connection conn = null;

	/**
	 * Method purely for testing
	 */
	public static void main(String[] args) {
		// print(getRoutesByOrigin("Wellington"));

		// print(getRoutesByDestination("Paris"));

		// print(getAirRoutes());

		Route r = new Route("Wellington", "Paris", Route.TransportType.AIR,
				12.50);
		System.out.println(insertOrUpdate(r));
		
		r = new Route("Wellington", "Paris", Route.TransportType.AIR,
				50);
		System.out.println(insertOrUpdate(r));

//		Route r = new Route("Wellington", "Paris", Route.TransportType.AIR, 12.50);
//		System.out.println(deleteRoute(r));

		// r = new Route("Wellington", "Pretoria",
		// Route.TransportType.AIR, 52.50);
		// insertOrUpdate(r);

		// System.out.println(getRouteByID(12).getPrice());//should be 52.5

		// print(getAll());

	}

	/**
	 * Method purely for local environment hack
	 */
	private static void createConnection() {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			conn = DriverManager.getConnection(DB_URL);
		} catch (Exception except) {
			except.printStackTrace();
		}
	}

	/**
	 * Method to get all Routes from DB
	 */
	public static ArrayList<Route> getAll() {

		createConnection();
		ArrayList<Route> allRoutes = new ArrayList<Route>();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs;

			rs = stmt
					.executeQuery("SELECT id, origin, destination, transporttype, price FROM routetable");
			while (rs.next()) {
				String origin = rs.getString("origin");
				String destination = rs.getString("destination");
				String transportTypeString = rs.getString("transporttype");
				double price = rs.getDouble("price");
				Route.TransportType transport;

				switch (transportTypeString) {
				case "AIR":
					transport = Route.TransportType.AIR;
					break;
				case "LAND":
					transport = Route.TransportType.LAND;
					break;
				case "SEA":
					transport = Route.TransportType.SEA;
					break;
				default:
					throw new IllegalArgumentException(
							"Invalid transport type: " + transportTypeString);
				}

				Route r = new Route(origin, destination, transport, price);
				allRoutes.add(r);
			}
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

		return allRoutes;
	}

	/**
	 * Add new route or update existing route
	 * 
	 * @param route
	 */
	public static boolean insertOrUpdate(Route route) {

		createConnection();
		try {
			PreparedStatement ps = conn
					.prepareStatement("UPDATE routetable SET price = ? WHERE origin = ? AND destination = ? AND transporttype = ?");

			ps.setDouble(1, route.getPrice());
			ps.setString(2, route.getOrigin());
			ps.setString(3, route.getDestination());
			ps.setString(4, route.getTransportType().toString());

			int rowschanged = ps.executeUpdate();

			if (rowschanged > 0)
				System.out.println("Successfully updated route");

			if (rowschanged == 0) {
				ps = conn
						.prepareStatement("INSERT INTO routetable (origin, destination, transporttype, price) VALUES (?, ?, ?, ?)");
				ps.setString(1, route.getOrigin());
				ps.setString(2, route.getDestination());
				ps.setString(3, route.getTransportType().toString());
				ps.setDouble(4, route.getPrice());

				rowschanged = ps.executeUpdate();

				if (rowschanged > 0)
					System.out.println("Sucessfully inserted route");
			}
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return true;
	}

	/**
	 * Delete route
	 * 
	 * @param route
	 */
	public static boolean deleteRoute(Route route) {

		int rowschanged = 0;
		createConnection();
		try {
			PreparedStatement ps = conn
					.prepareStatement("DELETE FROM routetable WHERE origin = ? AND destination = ? AND transporttype = ?");

			ps.setString(1, route.getOrigin());
			ps.setString(2, route.getDestination());
			ps.setString(3, route.getTransportType().toString());

			rowschanged = ps.executeUpdate();

			if (rowschanged > 0)
				System.out.println("Successfully deleted route");

			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		if (rowschanged > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to get all Origins from DB
	 */
	public static ArrayList<String> getOrigins() {

		createConnection();
		ArrayList<String> allOrigins = new ArrayList<String>();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery("SELECT DISTINCT origin FROM routetable");
			while (rs.next()) {
				String origin = rs.getString("origin");
				allOrigins.add(origin);
			}
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

		return allOrigins;
	}

	/**
	 * Method to get all Destinations from DB
	 */
	public static ArrayList<String> getDestinations() {

		createConnection();
		ArrayList<String> allDestinations = new ArrayList<String>();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs;

			rs = stmt
					.executeQuery("SELECT DISTINCT destination FROM routetable");
			while (rs.next()) {
				String destination = rs.getString("destination");
				allDestinations.add(destination);
			}
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

		return allDestinations;
	}

	/**
	 * Method to get all Routes of a certain TransportType from DB
	 * 
	 * @param t
	 */
	public static ArrayList<Route> getRoutes(Route.TransportType t) {

		createConnection();
		ArrayList<Route> airRoutes = new ArrayList<Route>();

		try {
			ResultSet rs;

			PreparedStatement ps = conn
					.prepareStatement("SELECT id, origin, destination, transporttype, price FROM routetable WHERE transportType = ?");
			ps.setString(1, t.toString());

			rs = ps.executeQuery();
			while (rs.next()) {
				String origin = rs.getString("origin");
				String destination = rs.getString("destination");
				String transportTypeString = rs.getString("transporttype");
				double price = rs.getDouble("price");
				Route.TransportType transport;

				switch (transportTypeString) {
				case "AIR":
					transport = Route.TransportType.AIR;
					break;
				case "LAND":
					transport = Route.TransportType.LAND;
					break;
				case "SEA":
					transport = Route.TransportType.SEA;
					break;
				default:
					throw new IllegalArgumentException(
							"Invalid transport type: " + transportTypeString);
				}

				Route r = new Route(origin, destination, transport, price);
				airRoutes.add(r);
			}
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

		return airRoutes;
	}

	/**
	 * Method to get all Routes from a given Origin from DB
	 * 
	 * @param orig
	 */
	public static ArrayList<Route> getRoutesByOrigin(String orig) {

		createConnection();
		ArrayList<Route> routes = new ArrayList<Route>();

		try {
			ResultSet rs;

			PreparedStatement ps = conn
					.prepareStatement("SELECT id, origin, destination, transporttype, price FROM routetable WHERE origin = ?");
			ps.setString(1, orig);

			rs = ps.executeQuery();
			while (rs.next()) {
				String origin = rs.getString("origin");
				String destination = rs.getString("destination");
				String transportTypeString = rs.getString("transporttype");
				double price = rs.getDouble("price");
				Route.TransportType transport;

				switch (transportTypeString) {
				case "AIR":
					transport = Route.TransportType.AIR;
					break;
				case "LAND":
					transport = Route.TransportType.LAND;
					break;
				case "SEA":
					transport = Route.TransportType.SEA;
					break;
				default:
					throw new IllegalArgumentException(
							"Invalid transport type: " + transportTypeString);
				}

				Route r = new Route(origin, destination, transport, price);
				routes.add(r);
			}
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

		return routes;
	}

	/**
	 * Method to get all Routes from a given Destination from DB
	 * 
	 * @param dest
	 */
	public static ArrayList<Route> getRoutesByDestination(String dest) {

		createConnection();
		ArrayList<Route> routes = new ArrayList<Route>();

		try {
			ResultSet rs;

			PreparedStatement ps = conn
					.prepareStatement("SELECT id, origin, destination, transporttype, price FROM routetable WHERE destination = ?");
			ps.setString(1, dest);

			rs = ps.executeQuery();
			while (rs.next()) {
				String origin = rs.getString("origin");
				String destination = rs.getString("destination");
				String transportTypeString = rs.getString("transporttype");
				double price = rs.getDouble("price");
				Route.TransportType transport;

				switch (transportTypeString) {
				case "AIR":
					transport = Route.TransportType.AIR;
					break;
				case "LAND":
					transport = Route.TransportType.LAND;
					break;
				case "SEA":
					transport = Route.TransportType.SEA;
					break;
				default:
					throw new IllegalArgumentException(
							"Invalid transport type: " + transportTypeString);
				}

				Route r = new Route(origin, destination, transport, price);
				routes.add(r);
			}
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

		return routes;
	}

	/**
	 * Method to get a single Route from a given ID from DB
	 * 
	 * @param id
	 */
	public static Route getRouteByID(int id) {

		createConnection();
		Route r = null;

		try {
			ResultSet rs;

			PreparedStatement ps = conn
					.prepareStatement("SELECT id, origin, destination, transporttype, price FROM routetable WHERE id = ?");
			ps.setInt(1, id);

			rs = ps.executeQuery();
			while (rs.next()) {
				String origin = rs.getString("origin");
				String destination = rs.getString("destination");
				String transportTypeString = rs.getString("transporttype");
				double price = rs.getDouble("price");
				Route.TransportType transport;

				switch (transportTypeString) {
				case "AIR":
					transport = Route.TransportType.AIR;
					break;
				case "LAND":
					transport = Route.TransportType.LAND;
					break;
				case "SEA":
					transport = Route.TransportType.SEA;
					break;
				default:
					throw new IllegalArgumentException(
							"Invalid transport type: " + transportTypeString);
				}

				r = new Route(origin, destination, transport, price);
			}
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

		return r;
	}

	/**
	 * Method purely for testing
	 */
	public static void print(ArrayList<Route> routes) {
		for (Route r : routes) {
			System.out.println("Origin: " + r.getOrigin());
			System.out.println("Destination: " + r.getDestination());
			System.out.println("TransportType: "
					+ r.getTransportType().toString());
			System.out.println("Price: " + r.getPrice());
		}

	}
}
