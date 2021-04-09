package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	
	private static Connection con;
	public DBConnection() {
		
	}
	
	/**
	 * Creates a connection for the cloud database on AWS and returns it 
	 * @return Connection
	 */
	@SuppressWarnings("finally")
	public static Connection returnCon() {
		String url="jdbc:mysql://bookstore-db-2021.cmzbrp6mecej.us-east-1.rds.amazonaws.com";
		String user="admin";
		String password="admin2021";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			System.out.println("Cannot create database connection");
			e.printStackTrace();
		} finally {
			return con;
		}
		
	}
	
	
}
