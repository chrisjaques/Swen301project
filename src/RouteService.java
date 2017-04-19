import java.sql.*;
import java.util.ArrayList;

public class RouteService {

	// static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc";
	static final String DB_URL = "jdbc:sqlserver://swen301project.database.windows.net:1433;database=swen301project;user=chris@swen301project;password={SWEN$301};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

	/**
	 * A JDBC SELECT (JDBC query) example program.
	 */

	public static void main(String[] args) {
		print(getAll());
		// print(getAirRoutes());
		// Route r = new Route(1235, "Wellington", "Paris",
		// Route.TransportType.AIR, 12.50);
		// insertOrUpdate(r);

		// Route r = new Route(12, "Wellington", "Pretoria",
		// Route.TransportType.AIR, 52.50);
		// insertOrUpdate(r);

	}

	public static ArrayList<Route> getAll() {

		ArrayList<Route> allRoutes = new ArrayList<Route>();

		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			Statement stmt = conn.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery("SELECT id, origin, destination, transporttype, price, uniqueid FROM routetable");
			while (rs.next()) {
				String origin = rs.getString("origin");
				String destination = rs.getString("destination");
				String transportTypeString = rs.getString("transporttype");
				double price = rs.getDouble("price");
				int uniqueid = rs.getInt("uniqueid");
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
					throw new IllegalArgumentException("Invalid transport type: " + transportTypeString);
				}

				Route r = new Route(uniqueid, origin, destination, transport, price);
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

	public static boolean insertOrUpdate(Route route) {
		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE routetable SET origin = ?, destination = ?, transporttype = ?, price = ? WHERE uniqueid = ?");

			ps.setString(1, route.getOrigin());
			ps.setString(2, route.getDestination());
			ps.setString(3, route.getTransportType().toString());
			ps.setDouble(4, route.getPrice());
			ps.setInt(5, route.getId());

			int rowschanged = ps.executeUpdate();

			if (rowschanged > 0)
				System.out.println("Successfully updated route with ID: " + route.getId());

			if (rowschanged == 0) {
				ps = conn.prepareStatement(
						"INSERT INTO routetable (origin, destination, transporttype, price, uniqueid) VALUES (?, ?, ?, ?, ?)");
				ps.setString(1, route.getOrigin());
				ps.setString(2, route.getDestination());
				ps.setString(3, route.getTransportType().toString());
				ps.setDouble(4, route.getPrice());
				ps.setInt(5, route.getId());

				rowschanged = ps.executeUpdate();

				if (rowschanged > 0)
					System.out.println("Sucessfully inserted route with ID: " + route.getId());
			}
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return true;
	}

	public static ArrayList<String> getOrigins() {
		ArrayList<String> allOrigins = new ArrayList<String>();

		try {
			Connection conn = DriverManager.getConnection(DB_URL);
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

	public static ArrayList<String> getDestinations() {
		ArrayList<String> allDestinations = new ArrayList<String>();

		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			Statement stmt = conn.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery("SELECT DISTINCT destination FROM routetable");
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

	public static ArrayList<Route> getAirRoutes() {

		ArrayList<Route> airRoutes = new ArrayList<Route>();

		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			ResultSet rs;

			PreparedStatement ps = conn.prepareStatement(
					"SELECT id, origin, destination, transporttype, price, uniqueid FROM routetable WHERE transportType = ?");
			ps.setString(1, "AIR");

			rs = ps.executeQuery();
			while (rs.next()) {
				String origin = rs.getString("origin");
				String destination = rs.getString("destination");
				String transportTypeString = rs.getString("transporttype");
				double price = rs.getDouble("price");
				int uniqueid = rs.getInt("uniqueid");
				Route.TransportType transport;

				switch (transportTypeString) {
				case "AIR":
					transport = Route.TransportType.AIR;
					break;
				default:
					throw new IllegalArgumentException("Invalid transport type: " + transportTypeString);
				}

				Route r = new Route(uniqueid, origin, destination, transport, price);
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

	public static ArrayList<Route> getLandRoutes() {

		ArrayList<Route> landRoutes = new ArrayList<Route>();

		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			ResultSet rs;

			PreparedStatement ps = conn.prepareStatement(
					"SELECT id, origin, destination, transporttype, price, uniqueid FROM routetable WHERE transportType = ?");
			ps.setString(1, "LAND");

			rs = ps.executeQuery();
			while (rs.next()) {
				String origin = rs.getString("origin");
				String destination = rs.getString("destination");
				String transportTypeString = rs.getString("transporttype");
				double price = rs.getDouble("price");
				int uniqueid = rs.getInt("uniqueid");
				Route.TransportType transport;

				switch (transportTypeString) {
				case "LAND":
					transport = Route.TransportType.LAND;
					break;
				default:
					throw new IllegalArgumentException("Invalid transport type: " + transportTypeString);
				}

				Route r = new Route(uniqueid, origin, destination, transport, price);
				landRoutes.add(r);
			}
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

		return landRoutes;
	}

	public static ArrayList<Route> getSeaRoutes() {

		ArrayList<Route> seaRoutes = new ArrayList<Route>();

		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			ResultSet rs;

			PreparedStatement ps = conn.prepareStatement(
					"SELECT id, origin, destination, transporttype, price, uniqueid FROM routetable WHERE transportType = ?");
			ps.setString(1, "SEA");

			rs = ps.executeQuery();
			while (rs.next()) {
				String origin = rs.getString("origin");
				String destination = rs.getString("destination");
				String transportTypeString = rs.getString("transporttype");
				double price = rs.getDouble("price");
				int uniqueid = rs.getInt("uniqueid");
				Route.TransportType transport;

				switch (transportTypeString) {
				case "SEA":
					transport = Route.TransportType.SEA;
					break;
				default:
					throw new IllegalArgumentException("Invalid transport type: " + transportTypeString);
				}

				Route r = new Route(uniqueid, origin, destination, transport, price);
				seaRoutes.add(r);
			}
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

		return seaRoutes;
	}

	public static void print(ArrayList<Route> routes) {
		for (Route r : routes) {
			System.out.println("\nID: " + r.getId());
			System.out.println("Origin: " + r.getOrigin());
			System.out.println("Destination: " + r.getDestination());
			System.out.println("TransportType: " + r.getTransportType().toString());
			System.out.println("Price: " + r.getPrice());
			System.out.println("UniqueID: " + r.getId());
		}

	}
}
