package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import bean.BookBean;
import bean.UserBean;


public class UserDAO {
	
	Connection con;

	public UserDAO() {
		DBConnection dbc = new DBConnection();
		this.con = dbc.returnCon();
	}
	
	/* Address stuff */
	
	//Return addrID of added adress
	public int createAddr(String street, String province, String country, String zip ) throws SQLException {
		String query = "insert into BookStore2021.Address (street, province, country, zip) values(?,?,?,?);";
		PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, street);
		stmt.setString(2, province);
		stmt.setString(3, country);
		stmt.setString(4, zip);
		stmt.executeUpdate();
		ResultSet result = stmt.getGeneratedKeys();
		
		if(result.next() && result != null) {
			return result.getInt(1);
		} else {
			return 0;
		}
		//return stmt.getGeneratedKeys().getInt(1);
		
		
	}
	
	
	/* add a user return 1 if added, 0 if user name exisits already */
	/* this method does not create admin or partner users just dafault creation of regular user */
	public int addUser(String username, String name, String password, String street, String province, String country, String zip) throws SQLException {
		int addrId = createAddr(street, province,  country, zip);
		String query = "insert into BookStore2021.Users(user_name, name, addrId, password) values(?,?,?,?);";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, username);
		stmt.setString(2, name);
		stmt.setInt(3, addrId);
		stmt.setString(4, password);
		
		return stmt.executeUpdate();
		
	}
	
	/* add a user return 1 if added, 0 if user name exisits already */
	/* this method is used to create an admin  */
	public int addAdmin(String userName, String name, String addr, String password) throws SQLException {
		String query = "insert into BookStore2021.Users(user_name, name, addr, type, password) values(?,?,?,?,?);";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, userName);
		stmt.setString(2, name);
		stmt.setString(3, addr);
		stmt.setString(4, "admin");
		stmt.setString(5, password);
		
		return stmt.executeUpdate();
		
	}
	
	//Get a user based off user name
	public UserBean getUser(String userName) throws SQLException {
		String query = "select * from BookStore2021.Users where user_name = '" +userName+"'";
		Statement p = this.con.createStatement();
		ResultSet r = p.executeQuery(query);
		UserBean user = null;
		while (r.next()){
			
			user = new UserBean(r.getString("user_name"),r.getString("name"), r.getInt("addrId"), r.getString("type"), r.getString("password") );
		}
		
		return user;
	}
	
	//Check if password entered is users password userName = username of user and enteredpassword is password they used to try to log in
	public int passwordCheck(String userName, String enteredPassword) throws SQLException {
		UserBean user = getUser(userName);
		if(user.getPassword().equals(enteredPassword)){
			return 1;
		} else {
			return 0;
		}
	}

}
