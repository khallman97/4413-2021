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

	public List<BookBean> retrieveBookByCategory(String category) throws Exception {
		if (category.equals("")) {
			return this.book.returnAllBooks();
		} else {
			return this.book.returnCategoryBooks(category);
		}
	}

	public List<BookBean> retrieveBookByTitle(String title) throws Exception {
		if (title.equals("")) {
			return this.book.returnAllBooks();
		} else {
			return this.book.returnBooksByNamesLike(title);
		}
	}

	public BookBean retrieveBook(String bid) throws Exception {
		return this.book.returnBooksByBid(bid);
	}

	public List<ReviewBean> exportReview(String bid) throws Exception {
		return this.revDao.exportReviews(bid);
	}

	public Map<String, ReviewBean> retrieveReviews(String bid) throws Exception {
		return this.revDao.retrieveReviews(bid);
	}

	public int bookAdd(String bid, String title, double price, String category, String author, String picture_link) throws SQLException, NamingException {
		return this.book.insert(bid, title, price, category, author, picture_link);

	}

	public int bookDelete(String bid) throws SQLException, NamingException {
		return this.book.delete(bid);
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

	public List<ReviewBean> exportReviews(String bid) throws SQLException, NamingException {
		return revDao.exportReviews(bid);
	}

	public List<AnalyticsBean> getMostReviewed() throws SQLException {
		return revDao.getMostReviewed();
	}

	public int addUser(String username, String name, String password, String street, String province, String country,
			String zip) throws SQLException {
		return userDao.addUser(username, name, password, street, province, country, zip);
	}

	public int loginUser(String username, String password) throws SQLException {
		return userDao.passwordCheck(username, password);
	}

	public UserBean getUser(String username) throws SQLException {
		return userDao.getUser(username);
	}
	
	public int createAddr(String street, String province, String country, String zip  ) throws SQLException {
		return userDao.createAddr(street, province, country, zip);
	}
	
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

	public int addToEvent(String bid, String eventType) throws SQLException {
		return eventDAO.addEvent(bid, eventType);
	}

	public List<EventBean> getEvent() throws SQLException, ParseException {
		return eventDAO.getEvents();
	}

	public List<EventBean> getEventsByBid(String bid) throws SQLException {
		return eventDAO.getEventsByBid(bid);
	}

	public List<EventBean> getEventsByDay(String month, String year) throws SQLException {
		return eventDAO.getEventsByDay(month, year);
	}

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

	public List<AnalyticsBean> getTopSold() throws SQLException {
		return poItemDAO.getTopSold();
	}

	public List<EventBean> get10MostVisited() throws SQLException {
		return eventDAO.getTop10EventsByType("VIEW");
	}

	public List<AdminBean> getUserInfo() throws SQLException {
		return adminDAO.returnUserStats();
	}
	
	
	public List<AdminBean> getUserPurchases() throws SQLException {
		return adminDAO.returnUserPurchases();
	}
	
	public List<AdminBean> getAvgPurchaseCustomer() throws SQLException {
		return adminDAO.returnAvgPurchaseCustomer();
	}
	
	public double getAvgOverallSpent() throws SQLException {
		return adminDAO.returnAvgOverallSpent();
	}
	
	public List<AdminBean> getAvgCustomerPuchaseCount() throws SQLException {
		return adminDAO.returnAvgCustomerPuchaseCount();
	}
	
	public double getAvgItems() throws SQLException {
		return adminDAO.returnAvgItems();
	}
}
