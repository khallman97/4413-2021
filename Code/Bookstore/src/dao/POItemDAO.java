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
	
	public int addPOItem(int orderId , String bid, int price, int quantity) throws SQLException {
		String preparedStatement ="insert into BookStore2021.POItem (orderId, bid, price, quantity) values(?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setInt(1,orderId);
		stmt.setString(2, bid);
		stmt.setInt(3, price);
		stmt.setInt(4, quantity);
		return stmt.executeUpdate();
	}
	
}