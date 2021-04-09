package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.AdminBean;
import bean.BookBean;
/**
 * Class for queries that are used on the admin page
 *
 */

public class AdminDAO {
	Connection con;

	public AdminDAO() {
		DBConnection dbc = new DBConnection();
		this.con = dbc.returnCon();
	}
	
	
	/**
	 * Returns user list of admin beans with combinded tables for poitems, address and order table
	 * @return List<AdminBean>
	 * @throws SQLException
	 */
	public List<AdminBean> returnUserStats() throws SQLException {
		String query = "SELECT * FROM BookStore2021.Orders "
				+ " INNER JOIN BookStore2021.POItem ON BookStore2021.Orders.id=BookStore2021.POItem.orderId "
				+ " INNER JOIN BookStore2021.Address ON BookStore2021.Orders.addressId=BookStore2021.Address.id;";
		PreparedStatement p = this.con.prepareStatement(query);
		ResultSet r = p.executeQuery(query);
		List<AdminBean> rv = new ArrayList<AdminBean>();
		while(r.next()){
			String user = r.getString("user_name");
			double spent = r.getDouble("orderTotalCost");
			String zip = r.getString("zip");
			int quantity = r.getInt("quantity");
			rv.add(new AdminBean(user,spent,zip,quantity));
		}
		
		return rv;
	}
	

	
	/**
	 * Returns user list of admin beans that returns stats on user pruchases
	 * @return List<AdminBean>
	 * @throws SQLException
	 */
	public List<AdminBean> returnUserPurchases() throws SQLException {
		String query = "Select BookStore2021.Orders.id as orderId, BookStore2021.Orders.user_name as username, BookStore2021.Orders.orderTotalCost as cost " + 
				"from BookStore2021.Orders " + 
				"where BookStore2021.Orders.status = 'PROCESSED' " + 
				"order by BookStore2021.Orders.user_name ";
		PreparedStatement p = this.con.prepareStatement(query);
		ResultSet r = p.executeQuery(query);
		List<AdminBean> rv = new ArrayList<AdminBean>();
		while(r.next()){
			int id = r.getInt("orderId");
			//String user = r.getString("username");
			double spent = r.getDouble("cost");
			//String zip = r.getString("zip");
			
//			int visited = r.getInt("visit");
			rv.add(new AdminBean("****",spent,"",id));
		}
		
		return rv;
	}
	
	/**
	 * Returns user list of admin beans that returns stats on user average customer purchases
	 * @return List<AdminBean>
	 * @throws SQLException
	 */
	public List<AdminBean> returnAvgPurchaseCustomer() throws SQLException {
		String query = "select BookStore2021.Orders.id as orderId, BookStore2021.Orders.user_name as username, FORMAT(AVG(BookStore2021.Orders.orderTotalCost),2) as cost " + 
				"			from BookStore2021.Orders " + 
				"			where BookStore2021.Orders.status = 'PROCESSED' " + 
				"			group by BookStore2021.Orders.user_name " + 
				" order by BookStore2021.Orders.user_name";
		PreparedStatement p = this.con.prepareStatement(query);
		ResultSet r = p.executeQuery(query);
		List<AdminBean> rv = new ArrayList<AdminBean>();
		while(r.next()){
			int id = r.getInt("orderId");
			double spent = r.getDouble("cost");
			rv.add(new AdminBean("****",spent,"",id));
		}
		
		return rv;
	}
	

	
	
	/**
	 * Returns the overall average spent by all customers
	 * @return double
	 * @throws SQLException
	 */
	public double returnAvgOverallSpent() throws SQLException {
		String query = "select FORMAT(AVG(BookStore2021.Orders.orderTotalCost),2) as cost " + 
				"						from BookStore2021.Orders " + 
				"					where BookStore2021.Orders.status = 'PROCESSED' ";
		PreparedStatement p = this.con.prepareStatement(query);
		ResultSet r = p.executeQuery(query);
		double spent = 0;
		List<AdminBean> rv = new ArrayList<AdminBean>();
		while(r.next()){
			spent = r.getDouble("cost");
		}
		
		return spent;
	}
	
	
	
	//Avg items per purchase by customer 
	String q3 = "select BookStore2021.Orders.user_name as username, FORMAT(AVG(BookStore2021.Orders.poItems),2) as cost \r\n" + 
			"from BookStore2021.Orders\r\n" + 
			"where BookStore2021.Orders.status = \"PROCESSED\"\r\n" + 
			"group by BookStore2021.Orders.user_name";
	
	/**
	 * Returns user list of admin beans that returns stats on user average amoutn of items a customer bought
	 * @return List<AdminBean>
	 * @throws SQLException
	 */
	public List<AdminBean> returnAvgCustomerPuchaseCount() throws SQLException {
		String query = "select BookStore2021.Orders.id as orderId, BookStore2021.Orders.user_name as username, FORMAT(AVG(BookStore2021.Orders.poItems),2) as items " + 
				"			from BookStore2021.Orders " + 
				"			where BookStore2021.Orders.status = 'PROCESSED' " + 
				"			group by BookStore2021.Orders.user_name ";
		PreparedStatement p = this.con.prepareStatement(query);
		ResultSet r = p.executeQuery(query);
		double spent = 0;
		List<AdminBean> rv = new ArrayList<AdminBean>();
		while(r.next()){
			//String user = r.getString("username");
			double items = r.getDouble("items");
			//String zip = r.getString("zip");
			int id = r.getInt("orderId");
			rv.add(new AdminBean("****",items,"",id));
		}
		
		return rv;
	}
	
	/**
	 * Returns the overall average of how many items a customer would purchase
	 * @return double
	 * @throws SQLException
	 */
	public double returnAvgItems() throws SQLException {
		String query = "select FORMAT(AVG(BookStore2021.Orders.poItems),2) as items " + 
				"						from BookStore2021.Orders" + 
				"						where BookStore2021.Orders.status = 'PROCESSED'";
		PreparedStatement p = this.con.prepareStatement(query);
		ResultSet r = p.executeQuery(query);
		double items = 0;
		List<AdminBean> rv = new ArrayList<AdminBean>();
		while(r.next()){
			//String user = r.getString("username");
			items = r.getDouble("items");

		}
		
		return items;
	}
	
}
