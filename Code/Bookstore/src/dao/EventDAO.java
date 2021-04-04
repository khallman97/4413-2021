package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import bean.BookBean;
import bean.EventBean;

import java.time.LocalDateTime;

public class EventDAO {
	
	Connection con;

	public EventDAO() {
		DBConnection dbc = new DBConnection();
		this.con = dbc.returnCon();
	}
	
	//add event
	public int addEvent(String bid, String eventType) throws SQLException {
		String query = "insert into BookStore2021.Event(day, bid, eventtype) values(?,?,?);";
		PreparedStatement stmt = con.prepareStatement(query);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddyyyy");
	    LocalDateTime now = LocalDateTime.now();
	     
		
		stmt.setString(1, dtf.format(now));
		stmt.setString(2, bid);
		stmt.setString(3, eventType);
		
		
		return stmt.executeUpdate();
	}
	
	
	//getallevents
	public List<EventBean> getEvents() throws SQLException {
		String query = "select * from BookStore2021.Event";
		PreparedStatement p = this.con.prepareStatement(query);
		ResultSet r = p.executeQuery(query);
		List<EventBean> rv = new ArrayList<EventBean>();
		while (r.next()){
			String day = r.getString("day");
			String bid = r.getString("bid");
			String eventtype = r.getString("eventtype");
			rv.add( new EventBean(day, bid, eventtype));
		}
		return rv;
		
	}
	
	
	//get events by bid
	public List<EventBean> getEventsByBid(String bid) throws SQLException {
		String query = "select * from BookStore2021.Event where bid = '"+bid+"'";
		PreparedStatement p = this.con.prepareStatement(query);
		ResultSet r = p.executeQuery(query);
		List<EventBean> rv = new ArrayList<EventBean>();
		while (r.next()){
			String day = r.getString("day");
			String nbid = r.getString("bid");
			String eventtype = r.getString("eventtype");
			rv.add( new EventBean(day, nbid, eventtype));
		}
		return rv;
		
	}
	
	
	
	
	//input is in string but in the form of month = "12" year="2015 for example
	public List<EventBean> getEventsByDay(String month, String year) throws SQLException {
		String query = "select * from BookStore2021.Event where day like '"+month+"%"+year+"'";
		PreparedStatement p = this.con.prepareStatement(query);
		ResultSet r = p.executeQuery(query);
		List<EventBean> rv = new ArrayList<EventBean>();
		while (r.next()){
			String day = r.getString("day");
			String nbid = r.getString("bid");
			String eventtype = r.getString("eventtype");
			rv.add( new EventBean(day, nbid, eventtype));
		}
		return rv;
		
	}
}