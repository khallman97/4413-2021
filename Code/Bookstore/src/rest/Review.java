package rest;

import model.*;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.ws.rs.*;

@Path("review")
public class Review {

	// get a list of reviews by bid
	@GET
	@Path("/read/")
	@Produces("text/plain")
	public String getProductInfo(@QueryParam("bid") String bid) throws Exception {
		return BookModel.getInstance().exportJSONRev(bid);
	}
	
	// add a book review
	@GET
	@Path("/create/")
	@Produces("text/plain")
	public int addBook(@QueryParam("bid") String bid, @QueryParam("review") String review, @QueryParam("rating") int rating) throws Exception {
		System.out.print("Review added bid is: " + bid);
		return BookModel.getInstance().addReview(bid, review, rating);
	}
}
