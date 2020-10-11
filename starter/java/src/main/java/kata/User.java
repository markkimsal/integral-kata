/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

	String getId() {
		return id;
	}
}
