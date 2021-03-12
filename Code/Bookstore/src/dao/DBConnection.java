package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	Connection con;
	
	public DBConnection() {
		String url="bookstore-db-2021.cmzbrp6mecej.us-east-1.rds.amazonaws.com";
		String user="admin";
		String password="admin2021";
		
		try {
			Connection con = DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection returnCon() {
		return con;
	}
	
	
}
