package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.POBean;
import bean.UserBean;
import dao.DBConnection;

public class OrderDAO {

	Connection con;
	
	public OrderDAO() {
		DBConnection dbc = new DBConnection();
		this.con = dbc.returnCon();
	}
	
	public int addOrder(String fname, String lname, String status, int addrId) throws SQLException {
		String preparedStatement ="insert into BookStore2021.Orders (lname, fname, status, addressId) values(?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setString(1, lname);
		stmt.setString(2, fname);
		stmt.setString(3, status);
		stmt.setInt(4, addrId);
		return stmt.executeUpdate();
	}
	
	public int updateOrder(int id, String status) throws SQLException {
		String preparedStatement ="UPDATE BookStore2021.Orders SET status = ? WHERE id = ?";
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setString(1, status);
		stmt.setInt(2, id);
		return stmt.executeUpdate();
	}
	
	public POBean getOrder(int id) throws SQLException {
		String query = "select * from BookStore2021.Orders where id = "+id;
		Statement p = this.con.createStatement();
		ResultSet r = p.executeQuery(query);
		POBean order = null;
		while (r.next()){
			
			order = new POBean(r.getInt("id"),r.getString("lname"),r.getString("fname"), r.getString("status"), r.getInt("addressId"));
		}
		return order;
	}
	
}