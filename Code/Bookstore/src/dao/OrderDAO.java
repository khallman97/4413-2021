package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.POBean;
import bean.UserBean;
import dao.DBConnection;
import model.BookModel;

public class OrderDAO {

	Connection con;
	
	public OrderDAO() {
		DBConnection dbc = new DBConnection();
		this.con = dbc.returnCon();
	}
	
	public int addOrder(String username, String status, int addrId, int POItems, int orderTotalCost) throws SQLException {
		String preparedStatement ="insert into BookStore2021.Orders  (user_name, status, addressId, poItems, orderTotalCost) values(?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(preparedStatement, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, username);
		stmt.setString(2, status);
		stmt.setInt(3, addrId);
		stmt.setInt(4, POItems);
		stmt.setInt(5, orderTotalCost);
		stmt.executeUpdate();
		
		ResultSet result = stmt.getGeneratedKeys();
		
	
		
		if(result.next() && result != null) {
			return result.getInt(1);
		} else {
			return 0;
		}
		
		
		
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