package rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import bean.EventBean;
import model.BookModel;

@Path("event")
public class Event {
	
	// Add a event
	@POST
	@Path("/add/")
	@Produces("text/plain")
	public int addEvent(@QueryParam("bid") String bid, @QueryParam("eventType") String eventType) throws Exception {
		return BookModel.getInstance().addToEvent(bid, eventType);
	}
	
	// get a list of all events
	@GET
	@Path("/read/")
	@Produces("text/plain")
	public List<EventBean> getEventInfo() throws Exception {
		return BookModel.getInstance().getEvent();
	}
	
	// get a list of all events by bid
	@GET
	@Path("/readByBid/")
	@Produces("text/plain")
	public List<EventBean> getEventInfoByBid(@QueryParam("bid") String bid) throws Exception {
		return BookModel.getInstance().getEventsByBid(bid);
	}
	
	// get a list of all events by month and year
	@GET
	@Path("/readByDay/")
	@Produces("text/plain")
	public List<EventBean> getEventInfoByBid(@QueryParam("month") String month, @QueryParam("year") String year) throws Exception {
		return BookModel.getInstance().getEventsByDay(month, year);
	}
	
}
