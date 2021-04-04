package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookBean;
import bean.ReviewBean;
import model.BookModel;

/**
 * Servlet implementation class bookItem
 */
@WebServlet({ "/Book", "/Book/*" })
public class bookItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	
	public bookItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		try {
			getServletContext().setAttribute("BookModel", BookModel.getInstance());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		String target = "/bookItem.jspx";

		
		String bid = "";
		if (request.getParameter("bid") != null && request.getParameter("bid") != "") {
			bid = request.getParameter("bid");
		}
		
		String newBID = request.getPathInfo().toString();
		String refinedBID = "";
		for (int i = 0; i <= newBID.length()-1; i++) {
			if (newBID.charAt(i) == '/') {
				//
			}
			else {
				refinedBID = refinedBID + newBID.charAt(i);
			}
		}
		
		List<ReviewBean> revList  = null;
		try {
			revList = BookModel.getInstance().exportReview(refinedBID);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getSession().setAttribute("reviewList", revList);
//		Enumeration<String> inputs = request.getAttributeNames();
		
		
		
		
//		BookModel model = (BookModel)getServletContext().getAttribute("BookModel");
		
//		try {
//			response.setContentType("application/json");
//			PrintWriter out = response.getWriter();
//			out.printf(reviewAsHTML(BookModel.getInstance(),bid));
//			out.flush();
//		
//		
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
		
		
//	}
		request.getRequestDispatcher(target).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public String reviewAsHTML(BookModel model, String bid) {
		 String html ="";
		 Map<String, ReviewBean> hmap=null;
		 try {
			 hmap=model.retrieveReviews(bid);
			 
		 }catch (Exception ex){
			 System.out.print(ex);
		 } 
		 System.out.print(hmap.toString());
		html="<table border=\"1\"> <thead> <td>Review </td> <td>Rating </td> </thead>";
		Iterator iterator = hmap.entrySet().iterator();
		while (iterator.hasNext()) {
			  Map.Entry me = (Map.Entry) iterator.next();
			  ReviewBean sb=(ReviewBean)me.getValue();			  
			  html+="<tr>"+
					  "<td>"+sb.getReview()+"</td>"+
					  "<td>"+sb.getRating()+"</td>"+ 
					  "</tr>";
		}
		html+="</thead></table>";
		return html;
	}

}
