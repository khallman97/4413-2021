package listener;

import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

import ctrl.bookstore;
import model.BookModel;

/**
 * Application Lifecycle Listener implementation class mostVisited
 *
 */
@WebListener
public class viewListener implements ServletContextAttributeListener {

	/**
	 * Default constructor.
	 */
	public viewListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
	 */
	public void attributeAdded(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub
		try {
			handleEvent(event);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
	 */
	public void attributeRemoved(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub
		try {
			handleEvent(event);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
	 */
	public void attributeReplaced(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub
		try {
			handleEvent(event);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void handleEvent(ServletContextAttributeEvent event) throws ClassNotFoundException, SQLException {
		// check last visited book
		if (event.getName().equals("lastVisited")) {
			// adds a visit event to the database
			String bid = event.getServletContext().getAttribute("lastVisited").toString();
			BookModel.getInstance().addToEvent(bid, "VIEW");
		}
	}
}
