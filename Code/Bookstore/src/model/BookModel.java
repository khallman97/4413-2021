package model;

import java.util.List;
import java.util.Map;

import bean.BookBean;
import dao.BookDAO;


public class BookModel {

	private static BookModel instance;
	private BookDAO book;
	
	private BookModel() throws ClassNotFoundException {
		// TODO Auto-generated constructor stub
		this.book = new BookDAO();
	}
	
	// will return ONE instance of the pattern with the DAO objects initialized
	public static BookModel getInstance() throws ClassNotFoundException{
		if (instance==null){
			instance = new BookModel();
			instance.book = new BookDAO();
		}
		return instance;
	}
	
	public List<BookBean> retrieveBookByCategory(String category) throws Exception {
		if (category.equals("")){
			return this.book.returnAllBooks();
		} else{
			return this.book.returnCategoryBooks(category);
		}
			
	}
	
	public Map<String, BookBean> retrieveBook(String bid, String title) throws Exception {
		return null;
			
	}
	
	public String bookAdd(String bid, String title, int price, String category, String author, String picture_link) {
		return null;
		
	}
	
	public String bookDelete (String bid) {
		return "hello";
	}
}
