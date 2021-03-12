package bean;

public class BookBean {
	private String bid;
	private String title;
	private int price;
	private String category;
	private String Author;
	private String picture_link;

	public BookBean(String bid, String title, int price, String category, String author, String picture_link) {
		super();
		this.bid = bid;
		this.title = title;
		this.price = price;
		this.category = category;
		Author = author;
		this.picture_link = picture_link;
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

	public int getPrice() {
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
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getPicture_link() {
		return picture_link;
	}

	public void setPicture_link(String picture_link) {
		this.picture_link = picture_link;
	}
}
