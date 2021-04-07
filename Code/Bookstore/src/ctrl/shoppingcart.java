package ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookBean;
import model.BookModel;

/**
 * Servlet implementation class shoppingcart
 */
@WebServlet("/cart")
public class shoppingcart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public shoppingcart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		double total = 0;
		List<String> currentCart = null;
		try {
			currentCart = BookModel.getInstance().returnCart();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> attbCart = (List<String>) request.getSession().getAttribute("currentCart");
		
		
		//Removes item from cart 
		if(request.getParameter("remove") != null) {
			
			String removeBid = request.getParameter("remove");
			//System.out.print("here: " + removeBid);
			int removeIndex = -1;
			for(int i=0; i< attbCart.size();i++) {
				if(attbCart.get(i).equals(removeBid)) {
					removeIndex = i;
				}
			
			}
			if(removeIndex > -1) {
				attbCart.remove(removeIndex);
			}
		}
		
		
		List<BookBean> fullCartInfo = new ArrayList<BookBean>();
		
		
		
		//System.out.print(attbCart.get(0));
		
		for(int i=0; i< attbCart.size();i++) {
			try {
				BookBean currBook = BookModel.getInstance().retrieveBook(attbCart.get(i));
				fullCartInfo.add(currBook);
				total = total + currBook.getPrice();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.getSession().setAttribute("cart", fullCartInfo);
		request.getSession().setAttribute("total", total);
		String target = "/cart.jspx";
		request.getRequestDispatcher(target).forward(request, response);
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
