package kata.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import kata.dao.TimelineMessage;

/**
 *
 * @author mark
 */
public class DbManager {
	static String url = "jdbc:sqlite::memory:";
	
	static Connection conn = null;
	
	public static Connection connect() {
		if (null != conn) {
			return conn;
		}
		try {
			conn = DriverManager.getConnection(url);
			setup(conn);
		} catch (SQLException e) {
			//TODO ...
		}

		return conn;
	}

	static void setup(Connection c) throws SQLException {
		Statement createTable = c.createStatement();
		boolean ret = false;
		//ret =createTable.execute("CREATE TABLE timeline (id TEXT, message TEXT, user_id TEXT, created_on INTEGER);");
		createTable.executeUpdate("CREATE TABLE timeline (id TEXT, message TEXT, user_id TEXT, created_on INTEGER);");
		while( createTable.getMoreResults()) {
			ResultSet rs = createTable.getResultSet();
		}
		if (!ret) {
			SQLWarning warnings = createTable.getWarnings();
			System.out.println(""+ret);
		}
		System.out.println(""+ret);
	}
}
