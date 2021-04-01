package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.DBConnection;

public class POItemDAO {

	Connection con;
	
	public POItemDAO() {
		DBConnection dbc = new DBConnection();
		this.con = dbc.returnCon();
	}
	
	public int addPOItem(String bid, int price, int quantity) throws SQLException {
		String preparedStatement ="insert into BookStore2021.POItem (bid, price, quantity) values(?,?,?)";
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setString(1, bid);
		stmt.setInt(2, price);
		stmt.setInt(3, quantity);
		return stmt.executeUpdate();
	}
	
}