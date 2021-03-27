package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import model.BookModel;

@Path("user")
public class User {

	@GET
	@Path("/login/")
	@Produces("text/plain")
	public int loginUser(@QueryParam("username") String username, @QueryParam("password") String pass) throws Exception {
		
		return BookModel.getInstance().loginUser(username, pass);
		
	}
	
	@GET
	@Path("/add/")
	@Produces("text/plain")
	public int createUser(@QueryParam("username") String username, @QueryParam("name") String name, @QueryParam("addr") String addr, @QueryParam("password") String pass) throws Exception {

		return BookModel.getInstance().addUser(username, name, addr, pass);
	}
	
	
	//May need to limit this rest call
	@POST
	@Path("/createAdmin/")
	@Consumes("text/plain")
	@Produces("text/plain")
	public int createAdmin(@QueryParam("username") String username, @QueryParam("name") String name, @QueryParam("addr") String addr, @QueryParam("password") String pass) throws Exception {

		return BookModel.getInstance().addAdmin(username, name, addr,pass);
	}
	
	
}
