package rest;

import model.*;

import javax.ws.rs.*;

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
	
	@POST
	@Path("/create/")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String addBook(@QueryParam("bid") String bid, @QueryParam("title") String title,
			@QueryParam("price") double price, @QueryParam("category") String category,
			@QueryParam("author") String author, @QueryParam("pictureLink") String picture_link)
			throws ClassNotFoundException {
		return BookModel.getInstance().bookAdd(bid, title, price, category, author, picture_link);
	}

	@DELETE
	@Path("/delete/")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String delete(@QueryParam("bid") String bid) throws ClassNotFoundException {
		return BookModel.getInstance().bookDelete(bid);
	}
}