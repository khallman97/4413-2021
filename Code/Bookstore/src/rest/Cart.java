package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import model.BookModel;

@Path("cart")
public class Cart {
	
	
	// Add a book to the cart
	@GET
	@Path("/add/")
	@Produces("text/plain")
	public String getProductInfo(@QueryParam("bid") String bid) throws Exception {
		//System.out.println("adding book to cart rest");
		
		BookModel.getInstance().addToCart(bid);
		return "added to cart " + bid;
	}
	
	// get all the books in the cart
	@GET
	@Path("/all/")
	@Produces("text/plain")
	public String getCartInfo(@QueryParam("bid") String bid) throws Exception {
		//System.out.println("adding book to cart rest");
		return BookModel.getInstance().returnCartToString();
		 
	}
	
	// remove an item from the cart
	@GET
	@Path("/remove/")
	@Produces("text/plain")
	public int removeCartItem(@QueryParam("bid") String bid) throws Exception {
		//System.out.println("removing bid from cart " + bid);
		
		return BookModel.getInstance().removeFromCart(bid);
		 
	}
	
}
