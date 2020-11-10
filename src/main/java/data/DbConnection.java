package data;

import java.sql.*;

import org.apache.commons.dbcp2.BasicDataSource;

public class DbConnection {
	private Connection con;
	private final String connectionUrl = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
	private final String user = "root";
	private final String pass = "admin";
	
	public DbConnection() {
		try {
			BasicDataSource db = new BasicDataSource();
			db.setUrl(connectionUrl);
			db.setUsername(user);
			db.setPassword(pass);
			db.setInitialSize(5);
			
			con = db.getConnection();
			System.out.println("Connection stablished");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getCon() {
		return con;
	}
	
	public void close(){
		try {
			if(con != null && !con.isClosed()){
				con.close();
				System.out.println("Connection closed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
