package model;

import java.util.List;
import java.util.Map;

import bean.BookBean;
import dao.BookDAO;
import dao.DBConnection;

public class BookModel {

	private DBConnection db_dao;
	private static BookModel instance;
	private BookDAO book;

	
	public static BookModel getInstance() throws ClassNotFoundException{
		if (instance==null) {
			instance =new BookModel();
			instance.db_dao= new DBConnection();
			instance.book = new BookDAO();
		}
		return instance;
		
	}

	
	
	private BookModel() throws ClassNotFoundException {
		db_dao = new DBConnection();
		
	}

	public List<BookBean> retrieveBookByCategory(String category) throws Exception {
		if (category.equals("")){
			return this.book.returnAllBooks();
		} else{
			return this.book.returnCategoryBooks(category);
		}
			
	}

	public BookBean retrieveBook(String bid) throws Exception {
		return this.book.returnBooksByBid(bid);
	}
	
	public String bookAdd(String bid, String title, double price, String category, String author, String picture_link) {
		return "hello";
		
	}
	
	public String bookDelete (String bid) {
		return "hello";
	}
}
