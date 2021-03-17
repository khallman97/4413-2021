package bean;

public class BookBean {
	private String bid;
	private String title;
	private double price;
	private String category;
	private String author;
	private String picture_link;

	public BookBean(String bid, String title, double price, String category, String author, String picture_link) {
		super();
		this.bid = bid;
		this.title = title;
		this.price = price;
		this.category = category;
		this.author = author;
		this.picture_link = picture_link;
	}

	//returns bid, title, price, category and author of the book in a string.
	public String getInfo() { 
		String info = bid + ", " + title + ", $" + price + ", " + category + ", " + author;
		return info;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAuthor() {
		if (author == null)
			return "";
		else
			return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPicture_link() {
		if (picture_link == null)
			return "";
		else
			return picture_link;
	}

	public void setPicture_link(String picture_link) {
		this.picture_link = picture_link;
	}
}
