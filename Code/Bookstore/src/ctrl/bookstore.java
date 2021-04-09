package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookBean;
import bean.POItemBean;
import model.BookModel;


/**
 * Servlet implementation class bookstore
 */
@WebServlet("/main")
public class bookstore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookstore() {
        super();
        // TODO Auto-generated constructor stub
        
    }
    List<String> cart;

    @Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		cart = new ArrayList<String>();
		try {
			getServletContext().setAttribute("BookModel", BookModel.getInstance());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			request.getSession().setAttribute("cartSize", BookModel.getInstance().returnCartCount());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Display main bookstore page
		String target = "/main.jspx";
		
		
		
		if (request.getParameter("addToCart") != null){
			String bid = "";
			if (request.getParameter("bid") != null) {
				bid = request.getParameter("bid");
			}
			double price = 0.0;
			if (request.getParameter("price") != null){
				price = Double.parseDouble(request.getParameter("price").toString());
			}

		}
		
		if(request.getParameter("cart") != null) {
			String bid = request.getParameter("cart");
			
			cart.add(bid);
			request.getSession().setAttribute("currentCart", cart);
		}
		
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
