package bean;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartBean {

	private List<String> bid;

	public ShoppingCartBean() {
		super();
		this.bid = new ArrayList<String>();
	}

	public List<String> getBid() {
		return bid;
	}

	public void setBid(List<String> bid) {
		this.bid = bid;
	}
	
	public void addBid(String bid) {
		this.bid.add(bid);
	}
	
	public String returnString() {
		String res = "";
		for(int i = 0; i < bid.size(); i++) {
            res = res + bid.get(i) + " ";
        }
		return res;
	}
	
	public int returnCount() {
		return bid.size();
	}
	
}
