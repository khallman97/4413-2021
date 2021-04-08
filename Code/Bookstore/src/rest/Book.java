package rest;

import model.*;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.ws.rs.*;

import bean.UserBean;

@Path("book")
public class Book {
//	@GET
//	@Path("/read/")
//	@Produces("text/plain")
//	public String getProductInfo(@QueryParam("bid") String bid) throws Exception {
//		System.out.println(BookModel.getInstance().retrieveBook(bid).getInfo());
//		return BookModel.getInstance().retrieveBook(bid).getInfo();
//	}
	
	@GET
	@Path("/read/")
	@Produces("text/plain")
	public String getProductInfo(@QueryParam("field") String field, @QueryParam("value") String value) throws Exception {
		return BookModel.getInstance().exportJSON(field, value);
	}

//	@GET
//	@Path("/read/")
//	@Produces("text/plain")
//	public String getBookByCat(@QueryParam("category") String category) throws Exception {
//		return BookModel.getInstance().retrieveBookByCategory(category).toString();
//	}
	
	
	/* Rest method of creating a book, requires the user to be a partner or admin in order to send */
	@POST
	@Path("/create/")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String addBook(@QueryParam("bid") String bid, @QueryParam("title") String title,
			@QueryParam("price") double price, @QueryParam("category") String category,
			@QueryParam("author") String author, @QueryParam("pictureLink") String picture_link, @QueryParam("username") String username, @QueryParam("password") String pass)
			throws ClassNotFoundException, SQLException, NamingException {
		String res = "";
		int login = BookModel.getInstance().loginUser(username, pass);
		if(login == 1) {
			UserBean user =  BookModel.getInstance().getUser(username);
			//System.out.println(user.getType());
			if(user.getType().equals("partner") || (user.getType().equals("admin"))) {
				res = Integer.toString(BookModel.getInstance().bookAdd(bid, title, price, category, author, picture_link));
			} else {
				res = "You need to be partner in order to use this serivce";
			}
		}
		
		return res;
	}
	
	/*need to be admin or partner in order to use this */
	@POST
	@Path("/delete/")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String delete(@QueryParam("bid") String bid,  @QueryParam("username") String username, @QueryParam("password") String pass) throws ClassNotFoundException, SQLException, NamingException {
		String res = "default";
		int login = BookModel.getInstance().loginUser(username, pass);
		if(login == 1) {
			UserBean user =  BookModel.getInstance().getUser(username);
			System.out.println(user.getType());
			if(user.getType().equals("partner") || (user.getType().equals("admin"))) {
				res = Integer.toString( BookModel.getInstance().bookDelete(bid));
			} else {
				res = "You need to be partner in order to use this serivce";
			}
		}
		
		return res;
		
		//return BookModel.getInstance().bookDelete(bid);
	}
}