package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import bean.AnalyticsBean;




public class TopDAO {

	Connection con;
	
	public TopDAO () {
		DBConnection dbc = new DBConnection();
		this.con = dbc.returnCon();

	}
	
	//Return all books
	public List<AnalyticsBean> getTopSold() throws SQLException{
		String query = "SELECT Book.bid, title, COUNT(title) as count"
				+ "FROM BookStore2021.Book"
				+ "INNER JOIN BookStore2021.POItem ON BookStore2021.Book.bid=BookStore2021.POItem.bid"
				+ "GROUP BY title"
				+ "ORDER BY COUNT(title) desc"
				+ "LIMIT 10";
		Statement p = this.con.createStatement();
		ResultSet r = p.executeQuery(query);
		List<AnalyticsBean> rv = new ArrayList<AnalyticsBean>();
		while (r.next()){
			String bid = r.getString("bid");
			String title = r.getString("title");
			int count = r.getInt("count");
			rv.add( new AnalyticsBean("SOLD",count, bid,title ));

		}
		
		return rv;
	}
	

	
	

	
}
