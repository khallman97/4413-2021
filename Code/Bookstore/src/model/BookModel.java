package model;

import java.util.Map;

import bean.BookBean;
import dao.DBConnection;

public class BookModel {

	private DBConnection db_dao;
	private static BookModel instance;
	
	public static BookModel getInstance() throws ClassNotFoundException{
		if (instance==null) {
			instance =new BookModel();
			instance.db_dao= new DBConnection();
		}
		return instance;
		
	}
	
	private BookModel() throws ClassNotFoundException {
		db_dao = new DBConnection();
		
	}

	public Map<String, BookBean> retrieveBook(String bid, String title) throws Exception {
		return null;
			
	}
	
	public String bookAdd(String bid, String title, int price, String category, String author, String picture_link) {
		return "hello";
		
	}
	
	public String bookDelete (String bid) {
		return "hello";
	}
}
