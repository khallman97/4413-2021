package bean;

public class POItemBean {
	private int id;
	private String bid;
	private double price;
	private int quantity;

	public POItemBean(int id, String bid, double price) {
		super();
		this.id = id;
		this.bid = bid;
		this.price = price;
		this.quantity = 1;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
