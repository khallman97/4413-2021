package listener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

import bean.AnalyticsBean;
import model.BookModel;

/**
 * Application Lifecycle Listener implementation class soldListener
 *
 */
@WebListener
public class soldListener implements ServletRequestListener {

    /**
     * Default constructor. 
     */
    List<AnalyticsBean> top = new ArrayList<AnalyticsBean>();

	
    public soldListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent arg0)  { 
         // TODO Auto-generated method stub
    	HttpServletRequest request = (HttpServletRequest) arg0.getServletRequest();
//        System.out.println("Request destroyed " + request.getPathInfo());

        if (request.getPathInfo() != null && request.getPathInfo().equals("/user/login")){
        	try {
				top = BookModel.getInstance().getTopSold();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //request.getSession().setAttribute("topSold", top);
            arg0.getServletContext().setAttribute("topSold", top);
        }
        
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
