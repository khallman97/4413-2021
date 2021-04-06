package bean;


public class AdminBean {
	private String user;
	private double totalSpent;
	private String zip;
	private int quantity;
//	private int visited;

	public AdminBean(String user, double totalSpent, String zip, int quantity) {
		super();
		this.user = user;
		this.totalSpent = totalSpent;
		this.zip = zip;
		this.quantity = quantity;
//		this.visited = visited;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public double getTotalSpent() {
		return totalSpent;
	}

	public void setTotalSpent(double totalSpent) {
		this.totalSpent = totalSpent;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

//	public int getVisited() {
//		return visited;
//	}
//
//	public void setVisited(int visited) {
//		this.visited = visited;
//	}
	
	
}
