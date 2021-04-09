package model;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.naming.NamingException;

import bean.AddressBean;
import bean.AdminBean;
import bean.AnalyticsBean;
import bean.BookBean;
import bean.EventBean;
import bean.EventCountBean;
import bean.ReviewBean;
import bean.ShoppingCartBean;
import bean.UserBean;
import dao.AdminDAO;
import dao.BookDAO;
import dao.DBConnection;
import dao.EventDAO;
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
	private EventDAO eventDAO;
	private AdminDAO adminDAO;

	// Save a instance of each DAO in the model
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
			instance.eventDAO = new EventDAO();
			instance.adminDAO = new AdminDAO();
		}
		return instance;

	}

	private BookModel() throws ClassNotFoundException {
		db_dao = new DBConnection();

	}

	// Get a list of books by their category
	public List<BookBean> retrieveBookByCategory(String category) throws Exception {
		// if no category specified get all books
		if (category.equals("")) {
			return this.book.returnAllBooks();
		} 
		// if category specified return book with that category
		else {
			return this.book.returnCategoryBooks(category);
		}
	}

	// Get a list of all the books with a specific substring
	public List<BookBean> retrieveBookByTitle(String title) throws Exception {
		// if no title specified get all books
		if (title.equals("")) {
			return this.book.returnAllBooks();
		} 
		// if title specified return books matching the title
		else {
			return this.book.returnBooksByNamesLike(title);
		}
	}

	// Get the book with the specific bid 
	public BookBean retrieveBook(String bid) throws Exception {
		return this.book.returnBooksByBid(bid);
	}

	public List<ReviewBean> exportReview(String bid) throws Exception {
		return this.revDao.exportReviews(bid);
	}

	// get a list of reviews with the following bid
	public Map<String, ReviewBean> retrieveReviews(String bid) throws Exception {
		return this.revDao.retrieveReviews(bid);
	}

	// add a new book
	public int bookAdd(String bid, String title, double price, String category, String author, String picture_link) throws SQLException, NamingException {
		return this.book.insert(bid, title, price, category, author, picture_link);

	}

	// delete a book
	public int bookDelete(String bid) throws SQLException, NamingException {
		return this.book.delete(bid);
	}

	// add a book to the shopping cart
	public void addToCart(String bid) {
		this.SCB.addBid(bid);
	}

	
	public String returnCartToString() {
		return SCB.returnString();
	}

	// get the list of book bid from the cart
	public List<String> returnCart() {
		return SCB.getBid();
	}

	// get the number of books in the cart
	public int returnCartCount() {
		return SCB.returnCount();
	}

	// remove a book from the cart
	public int removeFromCart(String bid) {
		return SCB.removeFromCart(bid);
	}

	// add a book review
	public int addReview(String bid, String review, int rating) throws SQLException, NamingException {
		return revDao.insert(bid, review, rating);
	}

	public List<ReviewBean> exportReviews(String bid) throws SQLException, NamingException {
		return revDao.exportReviews(bid);
	}

	// get the list of most reviewed books
	public List<AnalyticsBean> getMostReviewed() throws SQLException {
		return revDao.getMostReviewed();
	}

	// add a new user
	public int addUser(String username, String name, String password, String street, String province, String country,
			String zip) throws SQLException {
		return userDao.addUser(username, name, password, street, province, country, zip);
	}

	// Log the user in if user and password are in database
	public int loginUser(String username, String password) throws SQLException {
		return userDao.passwordCheck(username, password);
	}

	// get the user with that username
	public UserBean getUser(String username) throws SQLException {
		return userDao.getUser(username);
	}
	
	// create a new address in the database
	public int createAddr(String street, String province, String country, String zip  ) throws SQLException {
		return userDao.createAddr(street, province, country, zip);
	}
	
	// get the address of that id
	public AddressBean getAddr(int id) throws SQLException {
		return userDao.retrieveAddr(id);
	}
	
	// Creates order and adds po items to order need to add string cart list
	public int addToOrder(String username, String status, int addrId, List<String> cartList) throws SQLException {
		//List<String> cartList = this.returnCart();
		int cartCount = this.returnCartCount();
		for (int i = 0; i < cartList.size(); i++) {
			System.out.print(cartList.get(i));
			
		}
		int cost = 0;
		// int total = this.ret
		for (int i = 0; i < cartList.size(); i++) {
			BookBean b;
			try {
				b = this.retrieveBook(cartList.get(i));
				cost = (int) (cost + b.getPrice());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return 0;
				// e.printStackTrace();
			}

		}
		int orderid = orderDao.addOrder(username, status, addrId, cartList.size(), cost);

		for (int i = 0; i < cartList.size(); i++) {
			BookBean b;

			try {
				b = this.retrieveBook(cartList.get(i));
				poItemDAO.addPOItem(orderid, b.getBid(), (int) b.getPrice(), 1);
				this.addToEvent( b.getBid(), "PURCHASE");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
		}
		this.SCB.clearCart(); // Clear cart after purchase
		return 1;
	}

	public void addToPOItem() throws SQLException {

	}

	// add a new event
	public int addToEvent(String bid, String eventType) throws SQLException {
		return eventDAO.addEvent(bid, eventType);
	}

	// get a list of all events
	public List<EventBean> getEvent() throws SQLException, ParseException {
		return eventDAO.getEvents();
	}

	// get a list of all events that match the bid
	public List<EventBean> getEventsByBid(String bid) throws SQLException {
		return eventDAO.getEventsByBid(bid);
	}

	// get a list of all events of that month and year
	public List<EventBean> getEventsByDay(String month, String year) throws SQLException {
		return eventDAO.getEventsByDay(month, year);
	}

	// return the list of reviews in JSON format
	public String exportJSONRev(String bid) throws Exception {
		JsonArrayBuilder doc = Json.createArrayBuilder();

		List<ReviewBean> reviews = new ArrayList<ReviewBean>();
		reviews = exportReview(bid);

		for (ReviewBean rr : reviews) {
			doc.add(Json.createObjectBuilder().add("bid", rr.getBid()).add("review", rr.getReview()).add("rating",
					rr.getRating()));
		}

		String serializedJson = doc.build().toString();

		return serializedJson;
	}

	// return the list of books in JSON format
	public String exportJSON(String field, String value) throws Exception {
		JsonArrayBuilder doc = Json.createArrayBuilder();

		List<BookBean> books = new ArrayList<BookBean>();
		if (field.equals("category")) {
			books = retrieveBookByCategory(value);
		} else if (field.equals("title")) {
			books = retrieveBookByTitle(value);
		} else if (field.equals("bid")) {
			books.add(retrieveBook(value));
		}

		for (BookBean bb : books) {
			doc.add(Json.createObjectBuilder().add("bid", bb.getBid()).add("title", bb.getTitle())
					.add("price", bb.getPrice()).add("category", bb.getCategory()).add("author", bb.getAuthor())
					.add("picture_link", bb.getPicture_link()));
		}

		String serializedJson = doc.build().toString();

		return serializedJson;
	}

	// Model function to add admin
	public int addAdmin(String username, String name, String addr, String pass) throws SQLException {
		// TODO Auto-generated method stub
		return userDao.addAdmin(username, name, addr, pass);
	}

	// get a list of the top 10 sold books
	public List<AnalyticsBean> getTopSold() throws SQLException {
		return poItemDAO.getTopSold();
	}

	// get a list of the top 10 most visited
	public List<EventCountBean> get10MostVisited() throws SQLException {
		return eventDAO.getTop10EventsByType("VIEW");
	}

	// get user info
	public List<AdminBean> getUserInfo() throws SQLException {
		return adminDAO.returnUserStats();
	}
	
	// get user purchases
	public List<AdminBean> getUserPurchases() throws SQLException {
		return adminDAO.returnUserPurchases();
	}
	
	// get average customer purchases
	public List<AdminBean> getAvgPurchaseCustomer() throws SQLException {
		return adminDAO.returnAvgPurchaseCustomer();
	}
	
	// get overall average amount spent
	public double getAvgOverallSpent() throws SQLException {
		return adminDAO.returnAvgOverallSpent();
	}
	
	// get average customer purchase amount
	public List<AdminBean> getAvgCustomerPuchaseCount() throws SQLException {
		return adminDAO.returnAvgCustomerPuchaseCount();
	}
	
	// get average items
	public double getAvgItems() throws SQLException {
		return adminDAO.returnAvgItems();
	}
}
