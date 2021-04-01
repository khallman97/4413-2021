package bean;

public class EventBean {
	
	private String day;
	private String bid;
	private String eventType;
	
	public EventBean(String day, String bid, String eventType) {
		super();
		this.day = day;
		this.bid = bid;
		this.eventType = eventType;
	}
	
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
}
