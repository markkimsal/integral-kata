package kata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import kata.User;
import kata.db.DbManager;

/**
 * TODO: use some ORM
 * 
 * @author mark
 */
public class TimelineMessage {
	
	private String id;
	private String userId;
	private User user;
	private String msg;
	private Long createdAt;

	public void setUser(User user) {
		this.user = user;
		this.userId = user.getId();
	}

	public void setMessage(String message) {
		this.msg = message;
	}
	
	public String getId() {
		return id;
	}
	
	public String getMessage() {
		return msg;
	}

	public boolean save() {
		try {
			Connection c = DbManager.connect();
			if (this.id == null) {
				//TODO: use UUID or ULID library
				this.id = Long.toString(System.currentTimeMillis());
				//TODO: ensure no timezone / GMT
				this.createdAt = System.currentTimeMillis();
				//database manager insert
				PreparedStatement insert = c.prepareStatement(
						"INSERT INTO timeline (id, message, user_id, created_on) VALUES (?,?,?,?)");
				insert.setString(1, id);//, new String[]{userId});
				insert.setString(2, msg);
				insert.setString(3, userId);
				insert.setLong(4, createdAt);
				
				boolean result = insert.execute();
				//insert.execute("INSERT INTO timeline VALUES (?,?,?,?)", new String[]{this.id, this.userId, this.msg, this.createdAt.toString()});
				return true;
			}


			//database manager update
			Statement update = c.createStatement();
			update.execute("UPDATE timeline set message =? where id=?", new String[]{this.msg, this.id});

		} catch (SQLException e) {
			return false;
		}
		return true;

		//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	public static ArrayList<TimelineMessage> loadForUser(String userId) {
		ArrayList<TimelineMessage> ar = new ArrayList<>();
		try {
			Connection c = DbManager.connect();
			//database manager update
			//Statement select = c.createStatement();
			PreparedStatement select = c.prepareStatement("select * from timeline where user_id=? ORDER BY created_on DESC");
			select.setString(1, userId);//, new String[]{userId});
			ResultSet rs = select.executeQuery();
			while( rs.next() ) {
				TimelineMessage tm = new TimelineMessage();
				ar.add(tm.hydrate(rs));
			}
						
		} catch (SQLException e) {
			System.err.println("Caught exception loading timeline list " + e.getMessage());
			throw new RuntimeException(e.getMessage());

		}
		//database manager: load list of message where userId = userId;
		return ar;
	}
	
	private TimelineMessage hydrate(ResultSet rs) throws SQLException {
	
		this.id        = rs.getString("id");
		this.userId    = rs.getString("user_id");
		this.msg       = rs.getString("message");
		this.createdAt = rs.getLong("created_on");
		
		return this;
	}
}
