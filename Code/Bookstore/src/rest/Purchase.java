package rest;

import model.*;

import javax.ws.rs.*;

@Path("purchase")
public class Purchase {
	
	@GET
	@Path("/addOrder/")
	@Produces("text/plain")
	public int loginUser(@QueryParam("fname") String fname, @QueryParam("lname") String lname, @QueryParam("status") String status, @QueryParam("addrId") String addrId) throws Exception {
		//Take in string addr for now, will need to change to int once the the addr tables are set up
		
		
		return BookModel.getInstance().addToOrder(fname, lname, status, 1);
		
	}
	
}
