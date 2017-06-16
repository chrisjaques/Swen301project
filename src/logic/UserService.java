package logic;
import java.sql.*;

public class UserService {

	// static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc";
	//static final String DB_URL = "jdbc:sqlserver://swen301project.database.windows.net:1433;database=swen301project;user=chris@swen301project;password={SWEN$301};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

	static final String DB_URL = "jdbc:derby://localhost:1527/swen301;user=swen301;password=swen301";
	private static Connection conn = null;

	/**
	 * Method purely for testing
	 */
	public static void main(String[] args) {
		User u = new User("Chris", "Chris", User.UserType.MANAGER);
		insertOrUpdate(u);
		print(u);
	}
	
	/**
	 * Method purely for local environment hack
	 */
	private static void createConnection()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(DB_URL); 
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
    }
	
	/**
	 * Add new user or update existing user
	 * @param User
	 */
	public static boolean insertOrUpdate(User user) {
		try {
			createConnection();
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE users SET password = ?, role = ? WHERE username = ?");

			ps.setString(1, user.getPassword());
			ps.setString(2, user.getRole().toString());
			ps.setString(3, user.getUsername());

			int rowschanged = ps.executeUpdate();

			if (rowschanged > 0)//logging code can be removed
				System.out.println("Successfully updated user: " + user.getUsername());

			if (rowschanged == 0) {
				ps = conn.prepareStatement(
						"INSERT INTO users (username, password, role) VALUES (?, ?, ?)");
				
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getRole().toString());

				rowschanged = ps.executeUpdate();

				if (rowschanged > 0)//logging code can be removed
					System.out.println("Sucessfully inserted user: " + user.getUsername());
			}
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			e.printStackTrace();
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * Get user information from DB from given user name
	 * @param un
	 */
	public static User getUser(String un) {
		createConnection();
		User u = null;

		try {
			ResultSet rs;

			PreparedStatement ps = conn.prepareStatement(
					"SELECT username, password, role FROM users WHERE username = ?");
			ps.setString(1, un);

			rs = ps.executeQuery();
			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String roleString = rs.getString("role");
				User.UserType role;

				switch (roleString) {
				case "MANAGER":
					role = User.UserType.MANAGER;
					break;
				case "CLERK":
					role = User.UserType.CLERK;
					break;
				default:
					throw new IllegalArgumentException("Invalid role type: " + roleString);
				}

				u = new User(username, password, role);
			}
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

		return u;
	}

	/**
	 * Method purely for testing
	 */
	public static void print(User user) {
		System.out.println("\nUsername: " + user.getUsername());
		System.out.println("Password: " + user.getPassword());
		System.out.println("Role: " + user.getRole().toString());
	}
}
