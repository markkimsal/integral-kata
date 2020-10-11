package kata;

/**
 *
 * @author mark
 */
public class User {
	/**
	 * UUID or ULID
	 */
	private String id;
	
	/**
	 * 
	 */
	private String username;

	public User(String id, String username) {
		this.id       = id;
		this.username = username;
	}

	public String getId() {
		return id;
	}
}
