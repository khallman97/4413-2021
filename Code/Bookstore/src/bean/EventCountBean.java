package bean;

/**
 * Class for creating a new event count bean
 *
 */
public class EventCountBean {
	String title;
	String bid;
	String count;
	
	/**
	 * Constructor for creating a new event count bean
	 * @param title
	 * @param bid
	 * @param count
	 */
	public EventCountBean(String title, String bid, String count) {
		super();
		this.title = title;
		this.bid = bid;
		this.count = count;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
}
