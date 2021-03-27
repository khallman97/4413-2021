package bean;

public class ReviewBean {
	
	private String bid;
	private String review;
	private int rating;
	
	
	public String getBid() {
		return bid;
	}


	public void setBid(String bid) {
		this.bid = bid;
	}


	public String getReview() {
		return review;
	}


	public void setReview(String review) {
		this.review = review;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public ReviewBean(String bid, String review, int rating) {
		super();
		this.bid = bid;
		this.review = review;
		this.rating = rating;
	}
	

}
