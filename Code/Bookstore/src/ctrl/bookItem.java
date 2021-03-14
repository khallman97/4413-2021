package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookBean;
import model.BookModel;

/**
 * Servlet implementation class bookItem
 */
@WebServlet({"/Book","/Book/*"})
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
   	public void init(ServletConfig config) throws ServletException{
   		super.init(config);
   		
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String target = "/bookItem.jspx";

		
		String bid = "";
		if (request.getParameter("bid") != null && request.getParameter("bid") != "") {
			bid = request.getParameter("bid");
		}
		
		BookModel model = (BookModel)getServletContext().getAttribute("BookModel");
		
		try {
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.printf(bookAsHTML(model,bid));
			out.flush();
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
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
	
	public String bookAsHTML(BookModel model, String bid){
		String html ="";
		BookBean bb = null;
		try {
			bb = model.retrieveBook(bid);
		} catch (Exception e){
			System.out.println(e);
		}
				
		
			html+="<div class=\"card\">" +
					"<img src=\"" + bb.getPicture_link() + "\">" +
					"<div id=\"span\" style=\"display:none;\">" + bb.getBid() + "</span>" +
					"<div class=\"container\">" +
					"<h4><b>" + bb.getTitle() + "</b></h4>" +
					"<h4><b>" + bb.getAuthor() + "</b></h4>" +
					"<h4><b>" + bb.getPrice() + "</b></h4>" +
					"<h4><b>" + bb.getCategory() + "</b></h4>" +
					"</div>" + 
					"</div>";


		return html;
	}

}
