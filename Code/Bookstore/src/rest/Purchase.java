package rest;

import model.*;

import javax.ws.rs.*;

@Path("purchase")
public class Purchase {
	
//	@GET
//	@Path("/addOrder/")
//	@Produces("text/plain")
//	public int loginUser(@QueryParam("username") String username, @QueryParam("status") String status, @QueryParam("addrId") String addrId) throws Exception {
//		//Take in string addr for now, will need to change to int once the the addr tables are set up
//		
//		//BookModel.getInstance().addToPOItem();
//		return BookModel.getInstance().addToOrder(username, status, 1);
//		
//	}
	
	@GET
	@Path("/newAddr/")
	@Produces("text/plain")
	public int createShipAddr(@QueryParam("street") String street, @QueryParam("province") String province, @QueryParam("country") String country,  @QueryParam("zip") String zip) throws Exception {
		//Take in string addr for now, will need to change to int once the the addr tables are set up
		System.out.print("addr created");
		//BookModel.getInstance().addToPOItem();
		return BookModel.getInstance().createAddr(street,province,country,zip);
		
	}
	
}
