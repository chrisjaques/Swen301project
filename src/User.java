
public class User {
	private String username;
	private String password;
	private UserType role;
	
	public enum UserType {
		Manger, Clerk
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserType getRole() {
		return role;
	}
	public void setRole(UserType userType) {
		this.role = userType;
	}
	
	public User(String username, String password, UserType userType) {
		super();
		this.username = username;
		this.password = password;
		this.role = userType;
	}
}




