package rest;

import model.*;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.ws.rs.*;

@Path("review")
public class Review {

	
//	@GET
//	@Path("/read/")
//	@Produces("text/plain")
//	public String getProductInfo(@QueryParam("bid") String bid) throws Exception {
//		return BookModel.getInstance().retrieveReview(bid);
//	}
	
	@POST
	@Path("/create/")
	@Consumes("text/plain")
	@Produces("text/plain")
	public int addBook(@QueryParam("bid") String bid, @QueryParam("review") String review,
			@QueryParam("rating") int rating)
			throws Exception {
		return BookModel.getInstance().addReview(bid, review, rating);
	}
}
