package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import bean.ReviewBean;

public class ReviewDAO {
	
	Connection con;

	//select review by bid
	
	public Map<String, ReviewBean> retrieveReviews(String bidSearch) throws SQLException{
		String query = "select * from BookStore2021.Review where bid = '" +bidSearch+"'";
		Map<String, ReviewBean> rv = new HashMap<String, ReviewBean>();
		Statement p = con.createStatement();
		ResultSet r = p.executeQuery(query);
		ReviewBean reviewbean = null; 
		while (r.next()){
			
			String bid = r.getString("bid");
			String review = r.getString("review");
			int rating = r.getInt("rating");
			reviewbean = new ReviewBean(bid, review, rating);
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	
	//Insert review into db
	public int insert(String bid, String review, int rating) throws SQLException, NamingException {
		String preparedStatement ="insert into BookStore2021.Review values(?,?,?)";
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setString(1, bid);
		stmt.setString(2, review);
		stmt.setInt(3, rating);

		return stmt.executeUpdate();
	}
	
	//Delete a review from db
	public int delete(String bid) throws SQLException, NamingException {
		 String preparedStatement ="delete from BookStore2021.Review where bid=?";
		 PreparedStatement stmt = con.prepareStatement(preparedStatement);
		 stmt.setString(1, bid);
		 return stmt.executeUpdate();
	}
}
