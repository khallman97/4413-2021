package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AdminBean;
import bean.AnalyticsBean;
import bean.EventCountBean;
import model.BookModel;

/**
 * Servlet implementation class admin
 */
@WebServlet("/admin")
public class admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Display admin login page
		String target = "/admin.jspx";
		request.getRequestDispatcher(target).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		// Display analytics page when admin login
		List<AnalyticsBean> reviewList = null;
		List<EventCountBean> visitedList = null;
		List<AdminBean> userInfoList = null;
		List<AdminBean> userPurchases = null;
		List<AdminBean> userAvgCost = null;
		List<AdminBean> userAvgPurchaseCount = null;

		try {
			reviewList = BookModel.getInstance().getMostReviewed();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			visitedList = BookModel.getInstance().get10MostVisited();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			userInfoList = BookModel.getInstance().getUserInfo();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double avg = 0;
		double items = 0;
		
		
		
		try {
			avg = BookModel.getInstance().getAvgOverallSpent();
			//System.out.print("avg: " +avg);
			items = BookModel.getInstance().getAvgItems();
			//System.out.print("items: " +items);
			userPurchases = BookModel.getInstance().getUserPurchases();
			userAvgCost = BookModel.getInstance().getAvgPurchaseCustomer();
			userAvgPurchaseCount = BookModel.getInstance().getAvgCustomerPuchaseCount();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//System.out.print("reviewlist size" + reviewList.size());
		request.getSession().setAttribute("reviewed", reviewList);
		request.getSession().setAttribute("mostVisited", visitedList);
		request.getSession().setAttribute("userInfo", userInfoList);
		
		request.getSession().setAttribute("avgOverCost", avg);
		request.getSession().setAttribute("avgItems", items);
		request.getSession().setAttribute("userPurchases", userPurchases);
		request.getSession().setAttribute("userAvgCost", userAvgCost);
		request.getSession().setAttribute("userAvgPurchaseCount", userAvgPurchaseCount);

		
		String target = "/analytic.jspx";
		request.getRequestDispatcher(target).forward(request, response);
	}

}
