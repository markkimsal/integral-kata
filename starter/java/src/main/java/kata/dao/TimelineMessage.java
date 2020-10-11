/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kata.dao;

import java.util.ArrayList;
import kata.User;

/**
 * TODO: use some ORM
 * 
 * @author mark
 */
public class TimelineMessage {
	
	private String id;
	private User u;
	private String msg;
	private Long createdAt;

	public void setUser(User user) {
		this.u = user;
	}

	public void setMessage(String message) {
		this.msg = message;
	}
	
	public String getMessage() {
		return msg;
	}

	public void save() {
		if (this.id == null) {
			//TODO: use UUID or ULID library
			this.id = Long.toString(System.currentTimeMillis());
			//TODO: ensure no timezone / GMT
			this.createdAt = System.currentTimeMillis();
			
			//database manager insert
		}
		
		//database manager update
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	public static ArrayList<TimelineMessage> loadForUser(String userId) {
		ArrayList<TimelineMessage> ar = new ArrayList<>();
		//database manager: load list of message where userId = userId;
		return ar;
	}
}
