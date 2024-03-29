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

import bean.AnalyticsBean;
import bean.ReviewBean;

public class ReviewDAO {
	
	Connection con;
	
	public ReviewDAO () {
		DBConnection dbc = new DBConnection();
		this.con = dbc.returnCon();

	}


	//Returns a map of reviews for a bid
	public Map<String, ReviewBean> retrieveReviews(String bidSearch) throws SQLException{
		System.out.println("Going to Info...");
		String query = "select * from BookStore2021.Review where bid = '" +bidSearch+"'";
		PreparedStatement p = this.con.prepareStatement(query);
		ResultSet r = p.executeQuery(query);
		Map<String, ReviewBean> rv = new HashMap<String, ReviewBean>();
		while (r.next()){
			String bid = r.getString("bid");
			String review = r.getString("review");
			int rating = r.getInt("rating");
			rv.put(bid, new ReviewBean(bid, review, rating));

		}
		System.out.println("<script>A");

		r.close();
		p.close();
		con.close();
		System.out.println(rv.toString());
		return rv;
	}
	
	//Returns a list of review beans for a bid
	public List<ReviewBean> exportReviews(String bidSearch) throws SQLException{		
		String query = "select * from BookStore2021.Review where bid = '" +bidSearch+"'";
		PreparedStatement p = this.con.prepareStatement(query);
		ResultSet r = p.executeQuery(query);
		List<ReviewBean> rv = new ArrayList<ReviewBean>();
		while (r.next()){
			String bid = r.getString("bid");
			String review = r.getString("review");
			int rating = r.getInt("rating");
			rv.add( new ReviewBean(bid, review, rating));

		}
		
		return rv;
	}
	
	
	//Insert review into db
	public int insert(String bid, String review, int rating) throws SQLException, NamingException {
		String newReview = review.replaceAll("<", "&lt;");
		String preparedStatement ="insert into BookStore2021.Review(bid, review, rating) values(?,?,?)";
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setString(1, bid);
		stmt.setString(2, newReview);
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
	
	//Gets most reviewed books 
	public List<AnalyticsBean> getMostReviewed() throws SQLException {
		String query = "SELECT COUNT(BookStore2021.Review.bid) as NumberOfReviews, BookStore2021.Review.bid,  BookStore2021.Book.title\n" + 
				"FROM BookStore2021.Review\n" + 
				"INNER JOIN  BookStore2021.Book\n" + 
				"ON BookStore2021.Book.bid = BookStore2021.Review.bid\n" + 
				"GROUP BY BookStore2021.Review.bid\n" + 
				"ORDER BY COUNT(BookStore2021.Review.bid) DESC\n" + 
				"LIMIT 10;";
		PreparedStatement p = this.con.prepareStatement(query);
		ResultSet r = p.executeQuery(query);
		List<AnalyticsBean> rv = new ArrayList<AnalyticsBean>();
		while (r.next()){
			String bid = r.getString("bid");
			int count = r.getInt("NumberOfReviews");
			String title = r.getString("title");
			rv.add( new AnalyticsBean("Review", count, bid, title));
			

		}
		
		return rv;
		
	}
	
}
