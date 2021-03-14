package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import bean.BookBean;


public class BookDAO {

	Connection con;
	
	public BookDAO () {
		DBConnection dbc = new DBConnection();
		this.con = dbc.returnCon();

	}
	
	//Return all books
	public List<BookBean> returnAllBooks() throws SQLException{
		String query = "select * from BookStore2021.Book";
		PreparedStatement p = this.con.prepareStatement(query);
		ResultSet r = p.executeQuery(query);
		List<BookBean> rv = new ArrayList<BookBean>();
		while (r.next()){
			String bid = r.getString("bid");
			String title = r.getString("title");
			double price = r.getDouble("price");
			String category = r.getString("category");
			String author = r.getString("author");
			String picture_link = r.getString("picture_link");
			rv.add( new BookBean(bid, title, price, category, author, picture_link));

		}
		
		return rv;
	}
	
	//Retrun list of books where category is input
	public List<BookBean> returnCategoryBooks(String categorySearch) throws SQLException{
		String query = "select * from BookStore2021.Book where category like '%" +categorySearch +"%'";
		Statement p = this.con.createStatement();
		ResultSet r = p.executeQuery(query);
		List<BookBean> rv = new ArrayList<BookBean>();
		while (r.next()){
			String bid = r.getString("bid");
			String title = r.getString("title");
			double price = r.getDouble("price");
			String category = r.getString("category");
			String author = r.getString("author");
			String picture_link = r.getString("picture_link");
			rv.add( new BookBean(bid, title, price, category, author, picture_link));
		}
		return rv;
	}
	
	//return a book by its Id
	public BookBean returnBooksByBid(String bidSearch) throws SQLException{
		String query = "select * from BookStore2021.Book where bid = '" +bidSearch+"'";
		Statement p = con.createStatement();
		ResultSet r = p.executeQuery(query);
		BookBean bookbean = null; 
		while (r.next()){
			
			String bid = r.getString("bid");
			String title = r.getString("title");
			double price = r.getDouble("price");
			String category = r.getString("category");
			String author = r.getString("author");
			String picture_link = r.getString("picture_link");
			bookbean = new BookBean(bid, title, price, category, author, picture_link);
		}
		return bookbean;
	}
	
	//return book where title is like input value
	public List<BookBean> returnBooksByNamesLike(String titleLike) throws SQLException{
		String query = "select * from BookStore2021.Book where title like %" +titleLike + "%";
		Statement p = con.createStatement();
		ResultSet r = p.executeQuery(query);
		List<BookBean> rv = new ArrayList<BookBean>();
		while (r.next()){
			String bid = r.getString("bid");
			String title = r.getString("title");
			double price = r.getDouble("price");
			String category = r.getString("category");
			String author = r.getString("author");
			String picture_link = r.getString("picture_link");
			rv.add( new BookBean(bid, title, price, category, author, picture_link));
		}
		return rv;
	}
	
	
	//Insert book into db
	public int insert(String bid, String title, double price, String category, String  author, String picture_link) throws SQLException, NamingException {
		String preparedStatement ="insert into BookStore2021.Book values(?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setString(1, bid);
		stmt.setString(2, title);
		stmt.setDouble(3, price);
		stmt.setString(4, category);
		stmt.setString(5, author); 
		stmt.setString(6, picture_link); 

		return stmt.executeUpdate();
	}
	
	//Delete a book
	public int delete(String bid) throws SQLException, NamingException {
		 String preparedStatement ="delete from BookStore2021.Book where bid=?";
		 PreparedStatement stmt = con.prepareStatement(preparedStatement);
		 stmt.setString(1, bid);
		 return stmt.executeUpdate();
	}
	
	
}
