package logic;
import java.sql.*;
import java.util.ArrayList;

public class RouteService {

	static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc";
	static final String DB_URL = "jdbc:sqlserver://swen301.database.windows.net:1433;database=Swen301;user=swen301@swen301;password={Cotton208};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

	/**
	 * Method to get all Routes from DB
	 */
	public static ArrayList<Route> getAll() {

		ArrayList<Route> allRoutes = new ArrayList<Route>();

		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			Statement stmt = conn.createStatement();
			ResultSet rs;

			rs = stmt
					.executeQuery("SELECT id, origin, destination, transporttype, price FROM route");
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

		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			PreparedStatement ps = conn
					.prepareStatement("UPDATE route SET price = ? WHERE origin = ? AND destination = ? AND transporttype = ?");

			ps.setDouble(1, route.getPrice());
			ps.setString(2, route.getOrigin());
			ps.setString(3, route.getDestination());
			ps.setString(4, route.getTransportType().toString());

			int rowschanged = ps.executeUpdate();

			if (rowschanged > 0)
				System.out.println("Successfully updated route");

			if (rowschanged == 0) {
				ps = conn
						.prepareStatement("INSERT INTO route (origin, destination, transporttype, price) VALUES (?, ?, ?, ?)");
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
		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			PreparedStatement ps = conn
					.prepareStatement("DELETE FROM route WHERE origin = ? AND destination = ? AND transporttype = ?");

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

		ArrayList<String> allOrigins = new ArrayList<String>();

		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			Statement stmt = conn.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery("SELECT DISTINCT origin FROM route");
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

		ArrayList<String> allDestinations = new ArrayList<String>();

		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			Statement stmt = conn.createStatement();
			ResultSet rs;

			rs = stmt
					.executeQuery("SELECT DISTINCT destination FROM route");
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

		ArrayList<Route> airRoutes = new ArrayList<Route>();

		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			ResultSet rs;

			PreparedStatement ps = conn
					.prepareStatement("SELECT id, origin, destination, transporttype, price FROM route WHERE transportType = ?");
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

		ArrayList<Route> routes = new ArrayList<Route>();

		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			ResultSet rs;

			PreparedStatement ps = conn
					.prepareStatement("SELECT id, origin, destination, transporttype, price FROM route WHERE origin = ?");
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

		ArrayList<Route> routes = new ArrayList<Route>();

		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			ResultSet rs;

			PreparedStatement ps = conn
					.prepareStatement("SELECT id, origin, destination, transporttype, price FROM route WHERE destination = ?");
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

		Route r = null;

		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			ResultSet rs;

			PreparedStatement ps = conn
					.prepareStatement("SELECT id, origin, destination, transporttype, price FROM route WHERE id = ?");
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
}
