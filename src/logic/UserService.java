package logic;

import java.sql.*;

public class UserService {

	static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc";
	static final String DB_URL = "jdbc:sqlserver://swen301.database.windows.net:1433;database=Swen301;user=swen301@swen301;password={Cotton208};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

	/**
	 * Add new user or update existing user
	 * 
	 * @param User
	 */
	public static boolean insertOrUpdate(User user) {
		
		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			PreparedStatement ps = conn
					.prepareStatement("UPDATE users SET password = ?, role = ? WHERE username = ?");

			ps.setString(1, user.getPassword());
			ps.setString(2, user.getRole().toString());
			ps.setString(3, user.getUsername());

			int rowschanged = ps.executeUpdate();

			if (rowschanged > 0)// logging code can be removed
				System.out.println("Successfully updated user: "
						+ user.getUsername());

			if (rowschanged == 0) {
				ps = conn
						.prepareStatement("INSERT INTO users (username, password, role) VALUES (?, ?, ?)");

				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getRole().toString());

				rowschanged = ps.executeUpdate();

				if (rowschanged > 0)// logging code can be removed
					System.out.println("Sucessfully inserted user: "
							+ user.getUsername());
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
	 * 
	 * @param un
	 */
	public static User getUser(String un) {
		
		User u = null;

		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			ResultSet rs;

			PreparedStatement ps = conn
					.prepareStatement("SELECT username, password, role FROM users WHERE username = ?");
			ps.setString(1, un);

			rs = ps.executeQuery();
			while (rs.next()) {
				String username = rs.getString("username").trim();
				String password = rs.getString("password").trim();
				String roleString = rs.getString("role").trim();
				User.UserType role;
			
				switch (roleString) {
				case "MANAGER":
					role = User.UserType.MANAGER;
					break;
				case "CLERK":
					role = User.UserType.CLERK;
					break;
				default:
					throw new IllegalArgumentException("Invalid role type: "
							+ roleString);
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
}
