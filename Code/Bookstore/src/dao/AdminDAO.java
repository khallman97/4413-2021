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

public class AdminDAO {
	Connection con;

	public AdminDAO() {
		DBConnection dbc = new DBConnection();
		this.con = dbc.returnCon();
	}
	
	
	//Zip
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
//			int visited = r.getInt("visit");
			rv.add(new AdminBean(user,spent,zip,quantity));
		}
		
		return rv;
	}
	
	//User purchase 
	String q = "select BookStore2021.Orders.user_name as username, BookStore2021.Orders.orderTotalCost as cost \r\n" + 
			"from BookStore2021.Orders\r\n" + 
			"where BookStore2021.Orders.status = \"PROCESSED\"\r\n" + 
			"order by BookStore2021.Orders.user_name";
	
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
	
	
	//Avg cost per cust 
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
			//String user = r.getString("username");
			double spent = r.getDouble("cost");
			//String zip = r.getString("zip");
			
//			int visited = r.getInt("visit");
			rv.add(new AdminBean("****",spent,"",id));
		}
		
		return rv;
	}
	
	//Avg  order cost per cust
//	String q2 = "select BookStore2021.Orders.user_name as username, FORMAT(AVG(BookStore2021.Orders.orderTotalCost),2) as cost \r\n" + 
//			"from BookStore2021.Orders\r\n" + 
//			"where BookStore2021.Orders.status = \"PROCESSED\"\r\n" + 
//			"group by BookStore2021.Orders.user_name";
	
	
	//Avg overall cost
	String q4 = "select FORMAT(AVG(BookStore2021.Orders.orderTotalCost),2) as cost \r\n" + 
			"			from BookStore2021.Orders\r\n" + 
			"			where BookStore2021.Orders.status = \"PROCESSED\"";
	
	public double returnAvgOverallSpent() throws SQLException {
		String query = "select FORMAT(AVG(BookStore2021.Orders.orderTotalCost),2) as cost " + 
				"						from BookStore2021.Orders " + 
				"					where BookStore2021.Orders.status = 'PROCESSED' ";
		PreparedStatement p = this.con.prepareStatement(query);
		ResultSet r = p.executeQuery(query);
		double spent = 0;
		List<AdminBean> rv = new ArrayList<AdminBean>();
		while(r.next()){
			//String user = r.getString("username");
			spent = r.getDouble("cost");
			
			//String zip = r.getString("zip");
			
			
//			int visited = r.getInt("visit");
			//rv.add(new AdminBean("****",spent,"",id));
		}
		
		return spent;
	}
	
	
	
	//Avg items per purchase by customer 
	String q3 = "select BookStore2021.Orders.user_name as username, FORMAT(AVG(BookStore2021.Orders.poItems),2) as cost \r\n" + 
			"from BookStore2021.Orders\r\n" + 
			"where BookStore2021.Orders.status = \"PROCESSED\"\r\n" + 
			"group by BookStore2021.Orders.user_name";
	
	
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
	
	//overall avg items
//	select FORMAT(AVG(BookStore2021.Orders.poItems),2) as items 
//	from BookStore2021.Orders
//	where BookStore2021.Orders.status = "PROCESSED"
	
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
			
			//String zip = r.getString("zip");
			
			
//			int visited = r.getInt("visit");
			//rv.add(new AdminBean("****",spent,"",id));
		}
		
		return items;
	}
	
}
