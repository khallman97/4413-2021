package bean;

/**
 * Class used to create a bean for some analytic functions
 *
 */

public class AnalyticsBean {
	private String eventType;
	private int count;
	private String bid;
	private String title;
	
	/**
	 * Constructor for creating a new analytic bean
	 * @param eventType
	 * @param count
	 * @param bid
	 * @param title
	 */
	public AnalyticsBean(String eventType, int count, String bid, String title) {
		super();
		this.eventType = eventType;
		this.count = count;
		this.bid = bid;
		this.title=title;
	}


	public String getEventType() {
		return eventType;
	}


	public void setEventType(String eventType) {
		this.eventType = eventType;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public String getBid() {
		return bid;
	}


	public void setBid(String bid) {
		this.bid = bid;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
