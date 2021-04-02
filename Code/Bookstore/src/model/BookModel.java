package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.naming.NamingException;

import bean.BookBean;
import bean.ReviewBean;
import bean.ShoppingCartBean;
import bean.UserBean;
import dao.BookDAO;
import dao.DBConnection;
import dao.OrderDAO;
import dao.POItemDAO;
import dao.ReviewDAO;
import dao.UserDAO;

public class BookModel {

	private DBConnection db_dao;
	private static BookModel instance;
	private BookDAO book;
	private ShoppingCartBean SCB;
	private UserDAO userDao;
	private ReviewDAO revDao;
	private OrderDAO orderDao; 
	private POItemDAO poItemDAO;

	public static BookModel getInstance() throws ClassNotFoundException {
		if (instance == null) {
			instance = new BookModel();
			instance.db_dao = new DBConnection();
			instance.book = new BookDAO();
			instance.SCB = new ShoppingCartBean();
			instance.userDao = new UserDAO();
			instance.revDao = new ReviewDAO();
			instance.orderDao = new OrderDAO();
			instance.poItemDAO = new POItemDAO();
			
		}
		return instance;

	}

	private BookModel() throws ClassNotFoundException {
		db_dao = new DBConnection();

	}

	public List<BookBean> retrieveBookByCategory(String category) throws Exception {
		if (category.equals("")) {
			return this.book.returnAllBooks();
		} else {
			return this.book.returnCategoryBooks(category);
		}
	}
	

	public List<BookBean> retrieveBookByTitle(String title) throws Exception {
		if (title.equals("")){
			return this.book.returnAllBooks();
		} else{
			return this.book.returnBooksByNamesLike(title);
		}
	}

	public BookBean retrieveBook(String bid) throws Exception {
		return this.book.returnBooksByBid(bid);
	}
	
	public List<ReviewBean> exportReview(String bid) throws Exception{
		return this.revDao.exportReviews(bid);
	}
	
	public Map<String, ReviewBean> retrieveReviews(String bid) throws Exception{
		return this.revDao.retrieveReviews(bid);
	}

	public String bookAdd(String bid, String title, double price, String category, String author, String picture_link) {
		return "hello";

	}

	public String bookDelete(String bid) {
		return "hello";
	}

	public void addToCart(String bid) {
		this.SCB.addBid(bid);
	}

	public String returnCartToString() {
		return SCB.returnString();
	}

	public List<String> returnCart() {
		return SCB.getBid();
	}

	public int returnCartCount() {
		return SCB.returnCount();
	}

	public int removeFromCart(String bid) {
		return SCB.removeFromCart(bid);
	}
	
	public int addReview(String bid, String review, int rating) throws SQLException, NamingException {
		return revDao.insert(bid, review, rating);
	}
	
	public int addUser(String username, String name, String addr, String password) throws SQLException {
		return userDao.addUser(username, name, addr, password);
	}

	public int loginUser(String username, String password) throws SQLException {
		return userDao.passwordCheck(username, password);
	}
	
	public UserBean getUser(String username) throws SQLException {
		return userDao.getUser(username);
	}
	
	public int addToOrder(String fname, String lname, String status, int addrId) throws SQLException {
		return orderDao.addOrder(fname, lname, status, addrId);
	}
	
	public void addToPOItem() throws SQLException {
		List<String> cartList = this.returnCart();
		for (String bid: cartList){
			BookBean b;
			try {
				b = this.retrieveBook(bid);
				poItemDAO.addPOItem(bid, (int) b.getPrice(), 1);
				this.removeFromCart(bid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public String exportJSONRev(String bid) throws Exception
	{
		JsonArrayBuilder doc = Json.createArrayBuilder();
		
		List<ReviewBean> reviews = new ArrayList<ReviewBean>();
		reviews = exportReview(bid);
		
		for (ReviewBean rr : reviews)
		{
			doc.add(Json.createObjectBuilder().add("bid", rr.getBid())
				.add("review",rr.getReview())
				.add("rating",rr.getRating()));
		}
				
		String serializedJson = doc.build().toString();
		
		return serializedJson;
	}
	
	
	public String exportJSON(String field, String value) throws Exception
	{
		JsonArrayBuilder doc = Json.createArrayBuilder();
		
		List<BookBean> books = new ArrayList<BookBean>();
		if (field.equals("category")){
			books =  retrieveBookByCategory(value);
		} else if (field.equals("title")){
			books = retrieveBookByTitle(value);
		} else if (field.equals("bid")){
			books.add(retrieveBook(value));
		}
		
		for (BookBean bb : books)
		{
			doc.add(Json.createObjectBuilder().add("bid", bb.getBid())
				.add("title",bb.getTitle())
				.add("price",bb.getPrice())
				.add("category",bb.getCategory())
				.add("author",bb.getAuthor())
				.add("picture_link",bb.getPicture_link()));
		}
				
		String serializedJson = doc.build().toString();
		
		return serializedJson;
	}

	//Model function to add admin
	public int addAdmin(String username, String name, String addr, String pass) throws SQLException {
		// TODO Auto-generated method stub
		return userDao.addAdmin(username, name, addr, pass);
	}
}
