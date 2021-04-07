package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.AnalyticsBean;
import dao.DBConnection;
import model.BookModel;

public class POItemDAO {

	Connection con;
	
	public POItemDAO() {
		DBConnection dbc = new DBConnection();
		this.con = dbc.returnCon();
	}
	
	public int addPOItem(int orderId , String bid, int price, int quantity) throws SQLException, ClassNotFoundException {
		String preparedStatement ="insert into BookStore2021.POItem (orderId, bid, price, quantity) values(?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setInt(1,orderId);
		stmt.setString(2, bid);
		stmt.setInt(3, price);
		stmt.setInt(4, quantity);
		
		BookModel.getInstance().addToEvent(bid, "PURCHASE");
		
		return stmt.executeUpdate();
		
	}
	
	// Return Top 10 Sold
	public List<AnalyticsBean> getTopSold() throws SQLException{
		String query = "SELECT BookStore2021.Book.bid, BookStore2021.Book.title, COUNT(BookStore2021.Book.title) as count\n"
				+ "FROM BookStore2021.Book\n"
				+ "INNER JOIN BookStore2021.POItem ON BookStore2021.Book.bid=BookStore2021.POItem.bid\n"
				+ "GROUP BY BookStore2021.Book.title\n"
				+ "ORDER BY COUNT(BookStore2021.Book.title) desc\n"
				+ "LIMIT 10";
		Statement p = this.con.createStatement();
		ResultSet r = p.executeQuery(query);
		List<AnalyticsBean> rv = new ArrayList<AnalyticsBean>();
		while (r.next()){
			String bid = r.getString("bid");
			String title = r.getString("title");
			int count = r.getInt("count");
			rv.add( new AnalyticsBean("Sold", count, bid, title));

		}
		
		return rv;
	}
	
}