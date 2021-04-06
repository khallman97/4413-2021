package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.AdminBean;
import bean.BookBean;

public class AdminDAO {
	Connection con;

	public AdminDAO() {
		DBConnection dbc = new DBConnection();
		this.con = dbc.returnCon();
	}

	public List<AdminBean> returnUserStats() throws SQLException {
		String query = "SELECT *\r\n" + "FROM BookStore2021.Orders\r\n"
				+ "INNER JOIN BookStore2021.POItem ON BookStore2021.Orders.id=BookStore2021.POItem.orderId\r\n"
				+ "INNER JOIN BookStore2021.Address ON BookStore2021.Orders.addressId=BookStore2021.Address.id;";
		PreparedStatement p = this.con.prepareStatement(query);
		ResultSet r = p.executeQuery(query);
		List<AdminBean> rv = new ArrayList<AdminBean>();
		while(r.next()){
			String user = r.getString("user_name");
			Double spent = r.getDouble("orderTotalCost");
			String zip = r.getString("zip");
			int quantity = r.getInt("quantity");
//			int visited = r.getInt("visit");
			rv.add(new AdminBean(user,spent,zip,quantity));
		}
		
		return rv;
	}
}
