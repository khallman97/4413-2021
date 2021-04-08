package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AddressBean;
import bean.BookBean;
import bean.UserBean;
import model.BookModel;

/**
 * Servlet implementation class payment
 */
@WebServlet("/payment")
public class payment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public payment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
			String username = request.getParameter("username");
			UserBean user = null;
			AddressBean addrB= null;
			try {
				user = BookModel.getInstance().getUser(username);
				addrB = BookModel.getInstance().getAddr(user.getAddr());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("addrB", addrB);
			
			String un = request.getParameter("username");
			String status = request.getParameter("status");
			String addr = request.getParameter("addr");
			
			@SuppressWarnings("unchecked")
			List<String> attbCart = (List<String>) request.getSession().getAttribute("currentCart");
			
			//check for nulls
			if(un != null && status != null && addr != null) {
				try {
					int adrId = Integer.parseInt(addr);
					System.out.println("\n" +addr);
					System.out.println(attbCart.get(0));
					int worked = BookModel.getInstance().addToOrder(un, status, adrId, attbCart);
					//System.out.println(worked);
					if(worked == 1) {
						attbCart = new ArrayList<String>();
						request.getSession().setAttribute("currentCart", attbCart);
						List<BookBean> fullCartInfo = new ArrayList<BookBean>();
						request.getSession().setAttribute("cart", fullCartInfo);
						request.getSession().setAttribute("total", 0);
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			int counter = 0;
			Object counterObj = getServletContext().getAttribute("rejectionCounter");
			if(counterObj != null) {
				counter = Integer.parseInt(counterObj.toString());
			}
			counter = (counter + 1) % 3;
			System.out.println("Counter is " + counter);
			getServletContext().setAttribute("rejectionCounter", counter);
			//Create order with all this info
			
			
			String target = "/payment.jspx";
			request.getRequestDispatcher(target).forward(request, response);	
		
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		doGet(request, response);
	}

}
